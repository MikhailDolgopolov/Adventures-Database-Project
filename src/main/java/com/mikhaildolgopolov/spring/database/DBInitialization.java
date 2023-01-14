package com.mikhaildolgopolov.spring.database;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DBInitialization {
    @PostConstruct
    public void init(){
        new DatabaseService().init();
    }
}
