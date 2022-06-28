package brisa.lockmanager.configurations;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JsonConfig {


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer init() {

        return new Jackson2ObjectMapperBuilderCustomizer() {
            // ---------------------------------------------------------------------------------------------
            // * @see org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer#customize(org.springframework.http.converter.json.Jackson2ObjectMapperBuilder)
            // ---------------------------------------------------------------------------------------------
            @Override
            public void customize(final Jackson2ObjectMapperBuilder builder) {


                // TODO fixar em UTC-0

                builder.timeZone(TimeZone.getDefault());
            }
        };
    }
}
