package com.herring.yelt;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.herring.yelt" })
@PropertySource("classpath:application.properties")
public class SpringConfig {
}
