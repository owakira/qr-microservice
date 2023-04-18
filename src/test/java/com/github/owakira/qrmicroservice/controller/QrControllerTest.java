package com.github.owakira.qrmicroservice.controller;

import com.github.owakira.qrmicroservice.models.GenerateQrDTO;
import com.github.owakira.qrmicroservice.models.GenerateQrRequest;
import com.github.owakira.qrmicroservice.models.QR;
import com.github.owakira.qrmicroservice.service.QrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QrControllerTest {
    @Mock
    private QrService qrService;

    @InjectMocks
    private QrController qrController;

    @Test
    public void generateQr_RequestBodyIsValid_QrResponse() {
        var request = new GenerateQrRequest();
        request.setText("test");

        var qr = new QR(request.getText(), "test.png");
        Mockito.when(qrService.generateQr(new GenerateQrDTO(request.getText()))).thenReturn(qr);

        var response = qrController.generateQr(request);
        Assertions.assertEquals(qr.getText() + "1", response.getText());
        Assertions.assertEquals(qr.getFilename(), response.getFilename());
    }
}
