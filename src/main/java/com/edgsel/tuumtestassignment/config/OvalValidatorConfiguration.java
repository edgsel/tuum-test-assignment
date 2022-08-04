package com.edgsel.tuumtestassignment.config;

import net.sf.oval.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OvalValidatorConfiguration {

    @Bean
    public Validator validator() {
        return new Validator();
    }
}
