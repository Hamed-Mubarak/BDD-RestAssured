package BDD.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    private Properties properties;
    private String configFilePath;

    public ConfigProperties(String configFilePath) throws IOException {
        this.configFilePath=configFilePath;
        setConfigProperties();
    }

    public void setConfigProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(configFilePath));
    }

    public String getConfigProp(String key){
        return properties.getProperty(key);
    }
}
