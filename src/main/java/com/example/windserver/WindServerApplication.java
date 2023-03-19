package com.example.windserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WindServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WindServerApplication.class, args);
        log.info("启动成功");
    }
}
