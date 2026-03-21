package com.dq.house.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DQ1243
 */
//prefix 表示：
//        Spring Boot
//        会自动将配置文件（
//        如 application.
//        yml 或
//        application.properties）
//        中以 minio.开头的属性，映射到该类的字段上。
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
