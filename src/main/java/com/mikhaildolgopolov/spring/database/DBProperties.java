package com.mikhaildolgopolov.spring.database;

import com.mikhaildolgopolov.spring.ApplicationProperties;

import java.util.Objects;
import java.util.Properties;

public class DBProperties {
    private static final String DRIVER = "spring.datasource.driver-class-name";
    private static final String URL = "spring.datasource.url";
    private static final String USER = "spring.datasource.username";
    private static final String PASSWORD = "spring.datasource.password";

    private static DBProperties INSTANCE;

    private String driver;
    private String url;
    private String user;
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

    private DBProperties() {}

    private void init(Properties properties) {
        driver = properties.getProperty(DRIVER);
        url = properties.getProperty(URL);
        user = properties.getProperty(USER);
        password = properties.getProperty(PASSWORD);
    }



    public static DBProperties getProperties() {
        if(Objects.isNull(INSTANCE)) {
            INSTANCE = new DBProperties();
            INSTANCE.init(ApplicationProperties.getInstance().getProperties());
        }
        return INSTANCE;
    }
}
