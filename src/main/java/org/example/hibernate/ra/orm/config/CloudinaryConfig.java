package org.example.hibernate.ra.orm.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    private static final String CLOUD_NAME = "dwqmfiprt";
    private static final String API_KEY = "547165255154353";
    private static final String API_SECRET = "vzEY6Tk0t_mKQw3V5yfDB0F1LPI";

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET));
    }
}