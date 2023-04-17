package com.github.owakira.qrmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QrResponse {
    private String text;
    private String filename;

    public static QrResponse fromDomain(QR qr) {
        return new QrResponse(qr.getText(), qr.getFilename());
    }
}
