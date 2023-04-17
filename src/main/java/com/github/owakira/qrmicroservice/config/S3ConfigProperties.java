package com.github.owakira.qrmicroservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
@Data
public class S3ConfigProperties {
    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String region;
    private String bucketName;
}
