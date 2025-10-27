package com.sekurity.room_escape.domain.uploadFile.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResp {
  private Long id;

  private String fileName;
  private String fileUrl;

}
