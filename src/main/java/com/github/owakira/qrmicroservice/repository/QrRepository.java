package com.github.owakira.qrmicroservice.repository;

import java.io.InputStream;

// TODO: Add ObjectMetadata object
public interface QrRepository {
    void save(String filename, InputStream stream);
}
