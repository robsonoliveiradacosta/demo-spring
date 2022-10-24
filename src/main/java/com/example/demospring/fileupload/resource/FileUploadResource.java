package com.example.demospring.fileupload.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadResource {

    Logger logger = LoggerFactory.getLogger(FileUploadResource.class);

    @PostMapping(path = "/fileupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> fileUpload(MultipartFile file) {
        logger.info(file.getOriginalFilename());
        logger.info(file.getContentType());
        logger.info(String.valueOf(file.getSize()));
        return ResponseEntity.ok().build();
    }
}
