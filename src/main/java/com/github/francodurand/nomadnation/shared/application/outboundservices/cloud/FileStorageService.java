package com.github.francodurand.nomadnation.shared.application.outboundservices.cloud;

public interface FileStorageService {
    String uploadFile(String fileName, String folder, byte[] file);

    void rewriteFile(String publicId, String folder, byte[] file);
}
