package com.github.francodurand.nomadnation.shared.infrastructure.cloud.cloudinary.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.github.francodurand.nomadnation.shared.infrastructure.cloud.cloudinary.CloudinaryStorageService;

import jakarta.annotation.PostConstruct;

@Service
public class FileStorageServiceImpl implements CloudinaryStorageService {
    private Cloudinary cloudinary;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        this.cloudinary = new Cloudinary(apiKey);
    }

    @Override
    public String uploadFile(String fileName, String folder, byte[] file) {
        var options = ObjectUtils.asMap(
                "public_id", fileName,
                "asset_folder", "nomadnation/" + folder,
                "format", "webp",
                "quality", "auto");

        try {
            var upload = cloudinary.uploader().upload(file, options);
            return upload.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to cloudinary " + e.getMessage());
        }
    }

    @Override
    public void rewriteFile(String publicId, String folder, byte[] file) {
        var options = ObjectUtils.asMap(
                "public_id", publicId,
                "asset_folder", "nomadnation/" + folder,
                "format", "webp",
                "quality", "auto");

        try {
            cloudinary.uploader().upload(file, options);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to Cloudinary", e);
        }
    }
}
