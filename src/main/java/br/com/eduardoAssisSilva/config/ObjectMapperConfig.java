package br.com.eduardoAssisSilva.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("PesonFilter",
                        SimpleBeanPropertyFilter.serializeAllExcept("last_name"));

        mapper.setFilterProvider(filters);
        return mapper;
    }
}
