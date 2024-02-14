package com.br.upload.clound.controllers;


import com.br.upload.clound.services.UploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadControler {
    private final UploadService service;

    public UploadControler(UploadService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload/width{width}/height/{height}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("width") Integer width,@RequestParam("height") Integer height) throws IOException, InterruptedException {
        return ResponseEntity.ok(service.uploadImage(file,width,height));
    }
}
