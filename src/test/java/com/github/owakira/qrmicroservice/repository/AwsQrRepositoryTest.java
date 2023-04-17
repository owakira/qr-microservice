package com.github.owakira.qrmicroservice.repository;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class AwsQrRepositoryTest {
    @Mock
    private AmazonS3 s3Client;

    @Mock
    private InputStream stream;

    private AwsQrRepository qrRepository;

    private final static String bucketName = "bucket";
    private final static String filename = "test.png";

    @BeforeEach
    public void init() {
        qrRepository = new AwsQrRepository(s3Client, bucketName);
    }

    @Test
    public void save_successfullySave() {
        qrRepository.save(filename, stream);
        Mockito.verify(s3Client).putObject(Mockito.eq(bucketName), Mockito.eq(filename), Mockito.eq(stream), Mockito.any());
    }

    @Test
    public void save_throwsException_RuntimeException() {
        Mockito.doThrow(new AmazonServiceException(""))
                .when(s3Client)
                .putObject(
                        Mockito.eq(bucketName),
                        Mockito.eq(filename),
                        Mockito.any(InputStream.class),
                        Mockito.any(ObjectMetadata.class)
                );
        Assertions.assertThrows(AmazonServiceException.class, () -> qrRepository.save(filename, stream));
    }
}
