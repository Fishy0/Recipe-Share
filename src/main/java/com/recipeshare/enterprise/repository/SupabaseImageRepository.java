package com.recipeshare.enterprise.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Repository
public class SupabaseImageRepository {

    private final WebClient webClient;

    // these are stored in application.properties
    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    public SupabaseImageRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String uploadRecipeImage(MultipartFile file, int recipeId) throws IOException {
        String bucketName = "recipe-images";
        String fileName = "recipe_" + recipeId + "_" + file.getOriginalFilename();
        String url = supabaseUrl + "/storage/v1/object/" + bucketName + "/" + fileName;

        webClient.put()
                .uri(url)
                .header("apikey", supabaseKey)
                .header("Authorization", "Bearer " + supabaseKey)
                .header("Content-Type", file.getContentType())
                .bodyValue(file.getBytes())
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return supabaseUrl + "/storage/v1/object/public/" + bucketName + "/" + fileName;
    }
}
