package com.sekurity.room_escape.api.common.uploadFile.facade;

import com.sekurity.room_escape.common.exception.BusinessException;
import com.sekurity.room_escape.common.exception.ErrorCode;
import com.sekurity.room_escape.common.s3.S3Service;
import com.sekurity.room_escape.common.s3.dto.FileUploadCompleteReq;
import com.sekurity.room_escape.common.s3.dto.FileUploadReq;
import com.sekurity.room_escape.common.s3.dto.PreSignedUrlResp;
import com.sekurity.room_escape.domain.uploadFile.entity.UploadFile;
import com.sekurity.room_escape.domain.uploadFile.entity.dto.UploadFileResp;
import com.sekurity.room_escape.domain.uploadFile.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadFileFacade {

  private final S3Service s3Service;
  private final UploadFileService uploadFileService;

  public PreSignedUrlResp getPreSignedUrl(FileUploadReq req) {
    return s3Service.getPreSignedUrl(req);
  }

  public UploadFileResp uploadComplete(FileUploadCompleteReq req) {

    if (!s3Service.fileExists(req.getAccessUrl())) {
      throw new BusinessException(ErrorCode.ACCOUNT_NOT_FOUND);
    }
    

    UploadFile uploadFile = uploadFileService.save(req);

    return UploadFileResp.builder()
        .id(uploadFile.getId())
        .fileName(uploadFile.getFileName())
        .fileUrl(uploadFile.getFileUrl())
        .build();
  }

  public void delete(Long fileId) {
    UploadFile uploadFile = uploadFileService.getById(fileId);
    s3Service.deleteByAccessUrl(uploadFile.getFileUrl());
    uploadFileService.delete(fileId);
  }
}
