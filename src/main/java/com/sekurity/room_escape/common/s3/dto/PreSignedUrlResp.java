package com.sekurity.room_escape.common.s3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PreSignedUrlResp {
  private String fileName;
  private String accessUrl;
  private String uploadUrl;
}
