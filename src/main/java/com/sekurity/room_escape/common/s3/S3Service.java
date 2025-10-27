package com.sekurity.room_escape.common.s3;

import com.amazonaws.auth.Presigner;
import com.sekurity.room_escape.common.s3.dto.FileUploadReq;
import com.sekurity.room_escape.common.s3.dto.PreSignedUrlResp;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  private final S3Client s3Client;
  private final S3Presigner s3Presigner;

  public PreSignedUrlResp getPreSignedUrl(FileUploadReq req) {
    String key = UUID.randomUUID()+"."+req.getFileName();

    String encodedFileName = URLEncoder.encode(req.getFileName(), StandardCharsets.UTF_8).replace("+", "%20");

    String contentDisposition = String.format("attachment; filename=\"%s\"", encodedFileName);


    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .contentDisposition(contentDisposition)
        .build();

    PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
        .signatureDuration(Duration.ofMinutes(5))
        .putObjectRequest(putObjectRequest)
        .build();

    String uploadUrl = s3Presigner.presignPutObject(putObjectPresignRequest).url().toString();
    String accessUrl = "https://" + bucket + ".s3.amazonaws.com/" + key;

    return PreSignedUrlResp.builder()
        .fileName(req.getFileName())
        .uploadUrl(uploadUrl)
        .accessUrl(accessUrl)
        .build();
  }

  public Boolean fileExists(String accessUrl) {
    try {

      String key = extractKeyFromUrl(accessUrl);

      s3Client.headObject(HeadObjectRequest.builder()
          .bucket(bucket)
          .key(key)
          .build());
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void deleteByAccessUrl(String accessUrl) {

    if (accessUrl == null || !(accessUrl.contains("amazonaws.com") && accessUrl.contains(bucket))) {
      return;
    }

    String key = accessUrl.substring(accessUrl.lastIndexOf("/") + 1);

    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
        .bucket(bucket)
        .key(key)
        .build();

    s3Client.deleteObject(deleteObjectRequest);
  }
  
  private String extractKeyFromUrl(String accessUrl) {
    return accessUrl.substring(accessUrl.lastIndexOf("/") + 1);
  }

}
