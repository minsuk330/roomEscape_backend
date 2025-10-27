package com.sekurity.room_escape.api.common.uploadFile.controller;

import com.sekurity.room_escape.api.common.uploadFile.facade.FileUploadResp;
import com.sekurity.room_escape.api.common.uploadFile.facade.UploadFileFacade;
import com.sekurity.room_escape.common.s3.dto.FileUploadCompleteReq;
import com.sekurity.room_escape.common.s3.dto.FileUploadReq;
import com.sekurity.room_escape.common.s3.dto.PreSignedUrlResp;
import com.sekurity.room_escape.domain.uploadFile.entity.dto.UploadFileResp;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("common/file-upload")
public class UploadFileController {

  private final UploadFileFacade uploadFileFacade;

  @PostMapping("/upload-url")
  @Operation(summary = "업로드 url 요청")
  public PreSignedUrlResp getPreSignedUrl(
      @RequestBody FileUploadReq req
  ) {
    return uploadFileFacade.getPreSignedUrl(req);
  }

  @PostMapping("/upload-complete")
  @Operation(summary = "파일 업로드 성공")
  public UploadFileResp uploadComplete(
      @RequestBody FileUploadCompleteReq req
  ) {
    return uploadFileFacade.uploadComplete(req);
  }

  @DeleteMapping("/{fileId}")
  @Operation(summary = "파일 삭제")
  public void delete(
    @PathVariable("fileId") Long fileId
  ) {
    uploadFileFacade.delete(fileId);
  }

}
