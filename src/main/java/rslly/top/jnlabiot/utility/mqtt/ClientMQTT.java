package rslly.top.jnlabiot.utility.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;
import rslly.top.jnlabiot.config.MyConfig;

import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class ClientMQTT {

    public static  String HOST ;
    public static final String TOPIC1 = "jn_message/#";
    private static final String clientid = UUID.randomUUID().toString();
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName ;    //非必须
    private String passWord ;  //非必须
    private ScheduledExecutorService scheduler;
    private static ClientMQTT instance = new ClientMQTT();
    private ClientMQTT(){}
    public static ClientMQTT getInstance(){
        return instance;
    }

    public void start() {
        try {
            MyConfig t1=new MyConfig();
            t1.readProperty();
            // host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(t1.getHOST(), clientid, new MemoryPersistence());
            // MQTT的连接设置
            options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置连接的用户名
            options.setUserName(t1.getUsername());
            // 设置连接的密码
            options.setPassword(t1.getPassword().toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置断开后重新连接
            options.setAutomaticReconnect(true);
            // 设置回调
            client.setCallback(new PushCallback());
            //MqttTopic topic = client.getTopic(TOPIC1);
            //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
            //遗嘱
            //options.setWill(topic, "close".getBytes(), 1, true);
            client.connect(options);
            //订阅消息
            int[] Qos = {2};//0：最多一次 、1：最少一次 、2：只有一次
            String[] topic1 = {TOPIC1};
            client.subscribe(topic1, Qos);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void reConnect() throws Exception {
             if(client!=null) {
                        client.connect(options);
                     }
          }
    public void disconnect() throws MqttException {
        client.close();
    }

    public void publish(String topic,String message) throws MqttPersistenceException, MqttException {
        client.publish(topic,message.getBytes(),1,false);
    }



}
