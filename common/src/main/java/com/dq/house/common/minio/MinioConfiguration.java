package com.dq.house.common.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DQ1243
 */

@Configuration
@EnableConfigurationProperties(MinioProperties.class)  // 自动扫描 MinioProperties 类并将其注册为 Bean
@ConditionalOnProperty(name = "minio.endpoint")  // 只有当 minio.endpoint 存在时才加载这个配置类
//@ConfigurationPropertiesScan("com.dq.house.common.minio")
public class MinioConfiguration {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
