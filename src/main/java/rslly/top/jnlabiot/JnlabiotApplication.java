package rslly.top.jnlabiot;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rslly.top.jnlabiot.config.textreadannoation;
import rslly.top.jnlabiot.utility.mqtt.ClientMQTT;

@SpringBootApplication
public class JnlabiotApplication {


    public static void main(String[] args) throws MqttException {

        SpringApplication.run(JnlabiotApplication.class, args);

        ClientMQTT client =ClientMQTT.getInstance();
        client.start();

        //client.publish("hello","1234");
        //client.publish("hello","1234");

    }

}
