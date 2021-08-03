package rslly.top.jnlabiot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class MyConfig {
    private String username;
    private String password;
    private String HOST;

    public MyConfig() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void readProperty() throws IOException {
        Properties properties = new Properties();

        properties = PropertiesLoaderUtils.loadAllProperties("myconfig/code.properties");
        this.setUsername(new String(properties.getProperty("mqtt.username").getBytes(StandardCharsets.UTF_8)));
        this.setPassword(new String(properties.getProperty("mqtt.password").getBytes(StandardCharsets.UTF_8)));
        this.setHOST(new String(properties.getProperty("mqtt.Host").getBytes(StandardCharsets.UTF_8)));
    }

}
