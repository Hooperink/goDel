package godel.by.utils;


import godel.by.exception.UtilityException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBC_PropertiesReader {


    public static Map<String, String> getProperties (String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            Map <String, String> propertiesMap = new HashMap<>();
            Properties properties = new Properties();
            properties.load(fis);
            String driverName = (String) properties.get("JDBC_DRIVER");
            String dbURL = (String) properties.get("JDBC_DB_URL");
            String user = (String) properties.get("JDBC_USER");
            String password = (String) properties.get("JDBC_PASS");
            propertiesMap.put("JDBC_DRIVER", driverName);
            propertiesMap.put("JDBC_DB_URL", dbURL);
            propertiesMap.put("JDBC_USER", user);
            propertiesMap.put("JDBC_PASS", password);
            return propertiesMap;
        } catch (IOException e) {
            throw new UtilityException(e);
        }
    }
}
