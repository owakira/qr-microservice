package com.github.owakira.qrmicroservice.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QrResponseTest {

    @Test
    void fromDomain_ReturnsExpectedQrResponse() {
        var qr = new QR("test", "test.png");
        var response = QrResponse.fromDomain(qr);
        Assertions.assertEquals(qr.getText(), response.getText());
        Assertions.assertEquals(qr.getFilename(), response.getFilename());
    }
}
