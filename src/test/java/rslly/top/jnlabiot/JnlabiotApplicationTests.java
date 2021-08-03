package rslly.top.jnlabiot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import rslly.top.jnlabiot.utility.JWTUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JnlabiotApplicationTests {

    @Test
    void contextLoads() throws Exception {
        Map map = new HashMap<String, Integer>();
        map.put("username", "3824");
        map.put("token", 1234);
        var s=JWTUtils.createJWT(map,"{\"user\":\"cwl\"}",1000*60*60*12);
        System.out.println(s);
    }

}
