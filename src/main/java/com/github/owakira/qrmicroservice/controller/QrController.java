package com.github.owakira.qrmicroservice.controller;

import com.github.owakira.qrmicroservice.models.GenerateQrDTO;
import com.github.owakira.qrmicroservice.models.GenerateQrRequest;
import com.github.owakira.qrmicroservice.models.QrResponse;
import com.github.owakira.qrmicroservice.service.QrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qr")
@Log4j2
public class QrController {
    private final QrService qrService;

    @PostMapping
    public QrResponse generateQr(@RequestBody GenerateQrRequest request) {
        log.info("Generate qr request. text={}", request.getText());
        var qr = qrService.generateQr(new GenerateQrDTO(request.getText()));
        return QrResponse.fromDomain(qr);
    }
}
