package com.leizhou;

import com.leizhou.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StartUp  {

    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
    }


}


