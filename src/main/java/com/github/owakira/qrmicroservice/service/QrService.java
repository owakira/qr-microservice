package com.github.owakira.qrmicroservice.service;

import com.github.owakira.qrmicroservice.models.GenerateQrDTO;
import com.github.owakira.qrmicroservice.models.QR;

public interface QrService {
    QR generateQr(GenerateQrDTO dto);
}
