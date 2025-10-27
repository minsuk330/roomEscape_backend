package com.sekurity.room_escape.domain.uploadFile.service;

import com.sekurity.room_escape.common.s3.dto.FileUploadCompleteReq;
import com.sekurity.room_escape.common.s3.dto.FileUploadReq;
import com.sekurity.room_escape.domain.uploadFile.entity.UploadFile;
import com.sekurity.room_escape.domain.uploadFile.repository.UploadFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UploadFileService {

  private final UploadFileRepository uploadFileRepository;

  @Transactional
  public UploadFile save(FileUploadCompleteReq req) {
    return uploadFileRepository.save(
        UploadFile.builder()
            .fileName(req.getFileName())
            .fileUrl(req.getAccessUrl())
            .build()
    );
  }

  public UploadFile getById(Long id) {
    return uploadFileRepository.findById(id).orElse(null);
  }

  @Transactional
  public void delete(Long id) {
    uploadFileRepository.deleteById(id);
  }
}
