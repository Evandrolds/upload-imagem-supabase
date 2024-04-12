package com.image.supabase.services;

import com.image.supabase.entities.Image;
import com.image.supabase.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageService {

//    @Value("${supabase.urlToken}")
//    private String urlToken;
//    @Value("${supabase.key}")
//    private String supabaseKey;
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> getAllImagens(){
        return imageRepository.findAll();
    }
    public Image insertImage(Image img){
        return imageRepository.save(img);
    }

//    public ResponseEntity<byte[]> downloadImage(){
//        return ResponseEntity.ok(image2Repository.getUrlConvertByte(this.supabaseUrl));
//    }


}
