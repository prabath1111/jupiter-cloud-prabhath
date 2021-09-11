package com.jupiter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * The Config Handler Class
 * 
 * 
 * @author Prabath
 *
 */
public class ConfigHandler {

    private String propertyFileName;

    private Properties props;
    
	public ConfigHandler(String propertyFile) {
		
		initLocations(propertyFile);
	}
	

    private void initLocations(final String propertyFile) {

        propertyFileName = propertyFile;
    }
    
    public final String getRuntimeProperty(final String key) {

        FileInputStream fis = null;
        String value = "";

        try {
            fis = new FileInputStream(new File(propertyFileName));
            props = new Properties();
            props.load(fis);
            value = props.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public final void setRuntimeProperty(final String key, final String value) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(propertyFileName);
            props = new Properties();
            props.setProperty(key, value);
            props.store(fos, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public final Properties getPropertyObject() {
        return props;
    }
}
