package com.github.owakira.qrmicroservice.qrgenerator;

import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@RequiredArgsConstructor
@Component
public class GlxnQrGenerator implements QrGenerator {
    private final ImageType imageType;

    @Override
    public ByteArrayOutputStream generateQr(String text) {
        return QRCode.from(text).to(imageType).stream();
    }
}
