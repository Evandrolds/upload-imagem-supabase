package com.image.supabase.controllers;

import com.image.supabase.entities.Image;
import com.image.supabase.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/imagens")
public class ImageController {

    @Autowired
    private ImageService imageService;
//    @Autowired
//    private RestTemplate template;

    @GetMapping
    public ResponseEntity<List<Image>> findAll(){
        return ResponseEntity.ok(imageService.getAllImagens());
    }
    @PostMapping("/save")
    public ResponseEntity<Image> inserirImage(@RequestBody Image img){
        return ResponseEntity.ok(imageService.insertImage(img));
    }

//    @PostMapping("/upload")
//    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
//        String url = "https://dqrgngdngvhvlwthyeju.supabase.co/rest/v1/tab_imagem";
//        imageService.uploadImage(file);
//        String response = template.getForObject(url,String.class);
//       return response;
//    }

    @GetMapping("/destino/{destinationPath}")
    public void download(@PathVariable("destinationPath") String destinationPath) throws IOException {
      Image img = new Image();
        img.download(destinationPath);
    }

}
