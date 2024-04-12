package com.image.supabase.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Entity
@Data
@Table(name = "tab_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String imageUrl;
    private byte[] image;
    @Value("${urlImage}")
    private String supabaseUrl;
    public void download(String destinationPath) throws IOException {

        URL url = new URL(supabaseUrl);
            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(destinationPath));
            }
    }

}
