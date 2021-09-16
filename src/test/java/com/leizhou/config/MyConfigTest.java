package com.leizhou.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = MyConfig.class)
@TestPropertySource("classpath:application.yml")
class MyConfigTest {
    @Autowired
    private MyConfig myConfig;

    @Test
    void shouldGetMyConfig(){
        System.out.println(myConfig.toString());
    }
}