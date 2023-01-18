package com.mikhaildolgopolov.spring.database;

import com.mikhaildolgopolov.spring.ApplicationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Component
public class DBProperties {
    private static final String DRIVER = "spring.datasource.driver-class-name";
    private static final String URL = "spring.datasource.url";
    private static final String USER = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";


    @Value("${"+DRIVER+"}")
    private String driver;
    @Value("${"+URL+"}")
    private String url;
    @Value("${"+USER+"}")
    private String user;
    @Value("${"+PASSWORD+"}")
    private String password;

    public String getDriver() {return driver;}
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }


}
