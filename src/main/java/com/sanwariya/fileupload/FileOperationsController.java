package com.sanwariya.fileupload;

import com.sanwariya.fileupload.services.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@RestController
@RequestMapping("/api/files")
@Slf4j
public class FileOperationsController {

    @Value("${upload-dir}")
    private String uploadDir;
    @Value("${aws_access_key}")
    private String awsAccessKey;
    private final S3Service s3Service;

    public FileOperationsController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(uploadDir);
        try{
            this.s3Service.uploadFile(file);
            return ResponseEntity.ok().body("hello world");
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    "Something went wrong" + e.getMessage()
            );
        }

    }

    @GetMapping("/get")
    public ResponseEntity<String> get(){

        return ResponseEntity.ok("hello world get");
    }


}
