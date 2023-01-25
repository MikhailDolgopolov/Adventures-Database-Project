package com.mikhaildolgopolov.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

// --Commented out by Inspection START (25.01.2023 15:26):
//public class ApplicationProperties {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);
//    private static final String FILE_NAME = "/application.properties";
//    private static ApplicationProperties INSTANCE;
//    private final Properties properties = new Properties();
//
//    private void init() {
//        try (InputStream is = getClass().getResourceAsStream(FILE_NAME)) {
//            if(Objects.nonNull(is))
//                properties.load(is);
//        } catch (IOException e) {
//            LOGGER.info(e.getMessage(), e);
//        }
//    }
//
//// --Commented out by Inspection START (25.01.2023 15:26):
////    public Properties getProperties() {
////            return properties;
////    }
//// --Commented out by Inspection STOP (25.01.2023 15:26)
//
//// --Commented out by Inspection START (25.01.2023 15:26):
////    public static ApplicationProperties getInstance() {
// --Commented out by Inspection STOP (25.01.2023 15:26)
//        if(Objects.isNull(INSTANCE)) {
//            INSTANCE = new ApplicationProperties();
//            INSTANCE.init();
//        }
//
//        return INSTANCE;
//    }
// --Commented out by Inspection STOP (25.01.2023 15:26)
}
