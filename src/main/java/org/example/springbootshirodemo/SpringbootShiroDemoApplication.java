package org.example.springbootshirodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EntityScan(basePackages = "me.fany.shiro.entity")
@ComponentScan(basePackages = "me.fany.shiro")
@EnableJpaRepositories(basePackages = "me.fany.shiro.dao")
public class SpringbootShiroDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroDemoApplication.class, args);
    }

}
