package com.github.owakira.qrmicroservice.qrgenerator;

import java.io.ByteArrayOutputStream;

public interface QrGenerator {
    ByteArrayOutputStream generateQr(String text);
}
