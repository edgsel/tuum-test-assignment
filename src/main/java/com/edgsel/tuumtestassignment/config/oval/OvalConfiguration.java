package com.edgsel.tuumtestassignment.config.oval;

import net.sf.oval.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OvalConfiguration {

    @Bean
    public Validator validator() {
        return new Validator();
    }
}
