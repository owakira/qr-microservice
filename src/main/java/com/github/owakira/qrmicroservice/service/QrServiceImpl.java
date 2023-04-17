package com.github.owakira.qrmicroservice.service;

import com.github.owakira.qrmicroservice.exceptions.GenerateQrFailedException;
import com.github.owakira.qrmicroservice.models.GenerateQrDTO;
import com.github.owakira.qrmicroservice.models.QR;
import com.github.owakira.qrmicroservice.qrgenerator.QrGenerator;
import com.github.owakira.qrmicroservice.repository.QrRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.glxn.qrgen.core.image.ImageType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Log4j2
public class QrServiceImpl implements QrService {
    private final QrGenerator qrGenerator;
    private final QrRepository qrRepository;
    private final ImageType imageType;

    @Override
    public QR generateQr(GenerateQrDTO dto) {
        log.info("Generate qr. text={}", dto.getText());
        var stream = qrGenerator.generateQr(dto.getText());

        try (var inputStream = new ByteArrayInputStream(stream.toByteArray())) {
            var filename = UUID.randomUUID().toString();
            var filenameWithType = String.format("%s.%s", filename, imageType);
            qrRepository.save(filenameWithType, inputStream);
            log.info("QR successfully generated. text={}, filename={}", dto.getText(), filenameWithType);

            return new QR(dto.getText(), filenameWithType);
        } catch (IOException e) {
            log.error(e);
            throw new GenerateQrFailedException(dto.getText());
        }
    }
}
