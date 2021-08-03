package rslly.top.jnlabiot.config;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rslly.top.jnlabiot.utility.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 未携带token一个40，token过期一个44
 */
public class InterceptorDemo implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            //System.out.println("no auth");
            //throw new RuntimeException("无token，请重新登录");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            JSONObject res = new JSONObject();
            res.put("success", false);
            res.put("message", 44);
            out = response.getWriter();
            out.append(res.toString());
            return false;

        }else {
            System.out.println(authHeader.substring(7,authHeader.length()));
            try {
                Claims map = JWTUtils.parseJWT(authHeader.substring(7,authHeader.length()));
                Object obj=map.get("username");
                if (!obj.equals("jnlab"))throw new Exception("token无效");

            }catch  (Exception e){
                e.printStackTrace();
                PrintWriter out2 = null;
                JSONObject res = new JSONObject();
                res.put("success", false);
                res.put("message", 44);
                out2 = response.getWriter();
                out2.append(res.toString());
                return false;
            }
        }
        return true;
    }
}
