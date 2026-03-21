package com.dq.house.web.admin.service.impl;

import com.dq.house.common.minio.MinioProperties;
import com.dq.house.web.admin.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;

    /**
     * 上传文件
     *
     * @param file
     */
    @Override
    public String upLoad(MultipartFile file) throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException,
            InvalidResponseException, XmlParserException, InternalException {
        String fileName = getFileName(file); // 先生成一次，保证上传和访问一致
        boolean b = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(getBucketName()).build());
        if (!b) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(getBucketName()).build());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(getBucketName()).config(getPolicy()).build());
        }
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(getBucketName())
                        .object(fileName) // 用 fileName
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        return getUrl(fileName);
    }

    // 获取url
    private String getUrl(String fileName) {
        return String.join("/", getEndpoint(), getBucketName(), fileName);
    }

    // 获取文件名
    private String getFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return date + "/" + uuid + "-" + originalFilename;
    }

    // 获取Endpoint
    private String getEndpoint() {
        return minioProperties.getEndpoint();
    }

    // 获取桶名
    private String getBucketName() {
        return minioProperties.getBucketName();
    }

    // 获取桶的访问策略
    private String getPolicy() {
        return """
                {
                  "Version": "2012-10-17",
                  "Statement": [
                    {
                      "Effect": "Allow",
                      "Principal": "*",
                      "Action": "s3.GetObject",
                      "Resource": "arn:aws:s3:::%s/*"
                    }
                }
                """.formatted(minioProperties.getBucketName());
    }
}