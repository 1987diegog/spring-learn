package com.demente.ideas.learn;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:text.properties"),
        @PropertySource("classpath:data.properties")
})
public class TextPropertiesConfig {
}
