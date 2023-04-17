package com.github.owakira.qrmicroservice.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@RequiredArgsConstructor
@Repository
@Log4j2
public class AwsQrRepository implements QrRepository {
    private final AmazonS3 s3Client;
    private final String bucketName;

    @Override
    public void save(String filename, InputStream stream) {
        log.info("Save file to s3. filename={}", filename);
        s3Client.putObject(bucketName, filename, stream, new ObjectMetadata());
        log.info("File successfully saved to s3. filename={}", filename);
    }
}
