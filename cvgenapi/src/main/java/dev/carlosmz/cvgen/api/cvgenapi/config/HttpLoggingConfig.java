package dev.carlosmz.cvgen.api.cvgenapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class HttpLoggingConfig {

    @Bean
    CommonsRequestLoggingFilter requestLoggingFilter() {
        var f = new CommonsRequestLoggingFilter();
        f.setIncludeQueryString(true);
        f.setIncludePayload(true);
        f.setMaxPayloadLength(10_000);
        f.setIncludeHeaders(false);
        f.setAfterMessagePrefix("HTTP REQUEST : ");
        return f;
    }

}