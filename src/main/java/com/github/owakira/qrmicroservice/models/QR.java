package com.github.owakira.qrmicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QR {
    private String text;
    private String filename;
}
