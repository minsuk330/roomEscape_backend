package com.sekurity.room_escape.common.s3.dto;

import lombok.Getter;

@Getter
public class FileUploadCompleteReq {

  private String fileName;
  private String accessUrl;
}
