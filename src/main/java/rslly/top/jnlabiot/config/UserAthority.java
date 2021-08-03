package rslly.top.jnlabiot.config;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import rslly.top.jnlabiot.utility.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAthority implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
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
                Object obj=map.get("authority");
                if (!obj.equals("root"))throw new Exception("没有权限");

            }catch  (Exception e){
                e.printStackTrace();
                PrintWriter out2 = null;
                JSONObject res = new JSONObject();
                res.put("success", false);
                res.put("message", 55);
                out2 = response.getWriter();
                out2.append(res.toString());
                return false;
            }
        }
        return true;
    }
}
