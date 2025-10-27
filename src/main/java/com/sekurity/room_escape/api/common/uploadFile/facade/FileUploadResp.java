package com.sekurity.room_escape.api.common.uploadFile.facade;

import com.sekurity.room_escape.common.s3.dto.PreSignedUrlResp;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FileUploadResp {

  private PreSignedUrlResp preSignedUrl;
}