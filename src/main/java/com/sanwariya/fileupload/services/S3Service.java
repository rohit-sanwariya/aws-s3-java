package com.sanwariya.fileupload.services;


import com.amazonaws.services.s3.AmazonS3;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Log4j2
public class S3Service {
    private AmazonS3 amazonS3;
    @Value("${aws_bucket_name}")
    private String bucketName;

    public S3Service(AmazonS3 amazonS3){
        this.amazonS3 = amazonS3;
    }

    public void uploadFile( MultipartFile file) throws IOException{
        PutObjectResult result = amazonS3.putObject(this.bucketName,file.getOriginalFilename(),file.getInputStream(),null);
        log.info(result.getMetadata());
    }
}
