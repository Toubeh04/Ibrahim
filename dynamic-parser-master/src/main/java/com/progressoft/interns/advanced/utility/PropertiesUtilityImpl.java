package com.progressoft.interns.advanced.utility;

import com.progressoft.interns.advanced.exception.ParserException;
import com.progressoft.interns.advanced.exception.PropertiesException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtilityImpl implements PropertiesUtility {
    private final Properties prop = new Properties();

    @Override
    public void loadProperties(String filePath) {
        if (filePath == null) {
            throw new PropertiesException("File Path Cannot be null");
        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new PropertiesException("Properties file not found: "+filePath);
        } catch (IOException e) {
            throw new PropertiesException("Error while loading properties from file: "+filePath);
        }
    }

    @Override
    public String getPropertyValue(String propertyName) {
        if (prop.containsKey(propertyName)){
            return prop.getProperty(propertyName);
        }else {
            throw new PropertiesException("Property with key: (" + propertyName + ") is not found");
        }
    }

    @Override
    public Object getClassFromProperty(String propertyName) {
        try {
            String className = getPropertyValue(propertyName);
            Class<?> clazz = Class.forName(className);
            return clazz.getConstructor().newInstance();

        } catch (Exception e) {
            throw new ParserException("Error getting instance of class: " + propertyName);
        }
    }

}
