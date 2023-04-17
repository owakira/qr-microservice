package com.github.owakira.qrmicroservice.service;

import com.github.owakira.qrmicroservice.exceptions.GenerateQrFailedException;
import com.github.owakira.qrmicroservice.models.GenerateQrDTO;
import com.github.owakira.qrmicroservice.qrgenerator.QrGenerator;
import com.github.owakira.qrmicroservice.repository.QrRepository;
import net.glxn.qrgen.core.image.ImageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class QrServiceImplTest {
    @Mock
    private QrGenerator qrGenerator;

    @Mock
    private QrRepository qrRepository;

    private QrServiceImpl qrService;

    private final static ImageType imageType = ImageType.PNG;
    private final static GenerateQrDTO dto = new GenerateQrDTO("test");
    private final static ByteArrayOutputStream stream = new ByteArrayOutputStream();


    @BeforeEach
    public void init() {
        qrService = new QrServiceImpl(qrGenerator, qrRepository, imageType);
    }

    @Test
    public void generateQr_ValidDTO_QRGeneratedSuccessfully() {
        Mockito.when(qrGenerator.generateQr(dto.getText())).thenReturn(stream);
        var qr = qrService.generateQr(dto);
        Mockito.verify(qrRepository)
                .save(Mockito.endsWith(imageType.name()), Mockito.any(ByteArrayInputStream.class));

        Assertions.assertEquals(dto.getText(), qr.getText());
        Assertions.assertTrue(qr.getFilename().endsWith(imageType.name()));
    }

    @Test
    public void generateQr_InvalidDTO_ThrowGenerateQrFailedException() {
        var stream = Mockito.mock(ByteArrayOutputStream.class);
        Mockito.when(stream.toByteArray()).thenAnswer(invocationOnMock -> {
            throw new IOException();
        });
        Mockito.when(qrGenerator.generateQr(dto.getText())).thenReturn(stream);
        Assertions.assertThrows(GenerateQrFailedException.class, () -> qrService.generateQr(dto));
    }
}
