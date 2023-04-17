package com.github.owakira.qrmicroservice.qrgenerator;

import lombok.RequiredArgsConstructor;
import net.glxn.qrgen.core.image.ImageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class GlxnQrGeneratorTest {
    private GlxnQrGenerator qrGenerator;

    @BeforeEach
    public void init() {
        qrGenerator = new GlxnQrGenerator(ImageType.PNG);
    }

    @Test
    public void generateQr_SizeMoreThan0() {
        var stream = qrGenerator.generateQr("https://test.com");

        Assertions.assertNotNull(stream);
        Assertions.assertTrue(stream.size() > 0);
    }
}
