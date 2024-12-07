package util;
/*
@author Bhimashankar Teli
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.Reporter;
import org.testng.annotations.Test;


public class PropertyFileSource {
    public Properties loadParameters(String configFile) {
        Properties prop = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(Paths.get(configFile));
            prop.load(inputStream);
        } catch (Exception e) {
            try {
                System.out.println("The config file is "+configFile);
                prop.load(PropertyFileSource.class.getResourceAsStream(configFile));
            } catch (IOException e1) {
                System.out.println("Exception while loading properties file:::" + configFile);
                Reporter.log("Exception while loading properties file:::" + configFile);
                e1.printStackTrace();
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.print("Exception in loadParameters(1P)::" + configFile);
                    Reporter.log("Exception in loadParameters(1P)::" + configFile);
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}

