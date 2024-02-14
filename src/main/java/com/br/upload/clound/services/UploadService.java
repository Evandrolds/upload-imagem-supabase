package com.br.upload.clound.services;

import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Data
public class UploadService {

    @Value("${supabase.api.url}")
    private String supabaseApiUrl;

    @Value("${supabase.api.key}")
    private String supabaseApiKey;

    @Value("${supabase.api.secret}")
    private String supabaseApiSecret;

    @Value("${bucket.name}")
    private String bucketName;

    public String uploadImage(MultipartFile imageFile,int with, int height) throws IOException {

        // Obter os bytes do arquivo de imagem
        byte[] imageData = resizeImage(imageFile.getBytes(),with,height);

        // Configurar a requisição
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(URI.create(supabaseApiUrl + bucketName + supabaseApiKey));

        // Configurar os cabeçalhos
        request.setHeader("Content-Type", "Imagem login");
        request.setHeader("Authorization", "Bearer " + supabaseApiSecret);

        // Configurar o corpo da requisição
        HttpEntity entity = new ByteArrayEntity(imageData);
        request.setEntity(entity);

        // Enviar a requisição
        HttpResponse response = httpClient.execute(request);

        // Verificar a resposta
        if (response.getStatusLine().getStatusCode() == 200) {
            return "Imagem enviada com sucesso!";
        } else {
            return "Falha ao enviar imagem. Código de status: " + response.getStatusLine().getStatusCode();
        }
    }

    public byte[] resizeImage(byte[] sourceBytes, int width, int height) throws IOException {
        // Carregando a imagem vinda do sourceBytes
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(sourceBytes));

        // Aplicando o redimensionamento na imagem
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Convertendo a imagem redimensionada para BufferedImage
        BufferedImage bufferedResizedImage = converteImagem(resizedImage);

        // Escrevendo a imagem redimensionada em um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedResizedImage, "jpeg", outputStream);

        // Retornando os bytes da imagem redimensionada
        return outputStream.toByteArray();
    }

    // Método para converter a imagem para BufferedImage
    private BufferedImage converteImagem(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Criando o buffer da imagem com o fundo transparente
        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.SCALE_SMOOTH);

        // Criando os gráficos da imagem
        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
    }


}
