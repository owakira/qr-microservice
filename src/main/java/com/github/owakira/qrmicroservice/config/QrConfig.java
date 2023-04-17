package com.github.owakira.qrmicroservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.core.image.ImageType;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(S3ConfigProperties.class)
@RequiredArgsConstructor
public class QrConfig {
    private final S3ConfigProperties s3ConfigProperties;

    @Bean
    public ImageType imageType() {
        return ImageType.PNG;
    }

    @Bean
    public AmazonS3 s3Client() {
        var creds = new BasicAWSCredentials(
                s3ConfigProperties.getAccessKey(),
                s3ConfigProperties.getSecretKey()
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        s3ConfigProperties.getEndpoint(),
                        s3ConfigProperties.getRegion()
                ))
                .build();
    }
}
