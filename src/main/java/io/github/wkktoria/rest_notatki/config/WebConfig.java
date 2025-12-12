package io.github.wkktoria.rest_notatki.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ApiVersionParser;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(final ApiVersionConfigurer configurer) {
        configurer
                .useMediaTypeParameter(MediaType.APPLICATION_JSON, "version")
                .addSupportedVersions("1.0")
                .setDefaultVersion("1.0")
                .setVersionParser(new ApiVersionParser() {
                    @Override
                    public Comparable parseVersion(final String version) {
                        return version;
                    }
                });
    }
}
