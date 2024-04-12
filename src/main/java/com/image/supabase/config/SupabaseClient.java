package com.image.supabase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

//@Configuration
public class SupabaseClient {
    @Value("${supabase.url}")
    private String supabaseUrl;
    @Value("${supabase.urlToken}")
    private String urlToken;

    @Value("${supabase.key}")
    private String supabaseKey;

    public SupabaseClient(String supabaseUrl, String supabaseKey) {
    }

    @Bean
    public SupabaseClient supabaseClient() {
        return new SupabaseClient(this.supabaseUrl, this.supabaseKey);
    }

}
