package com.sekurity.room_escape.domain.uploadFile.repository;

import com.sekurity.room_escape.domain.uploadFile.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

}
