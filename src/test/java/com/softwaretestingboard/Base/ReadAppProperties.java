package com.softwaretestingboard.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*Has a static block where application url is extracted from application.properties
and initialized to a static final variable*/
public class ReadAppProperties {
    public static final String URL;
    public static final int TIMEOUT;

    static {
        System.out.println("Reading application.properties for url");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/test/resources/application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = prop.getProperty("application.url");
        TIMEOUT = 3;
    }
}
