package rslly.top.jnlabiot.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import rslly.top.jnlabiot.model.model;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainsiteErrorController implements ErrorController {


    private final String ERROR_PATH ="/error";

    /**
     * 出现错误，跳转到如下映射中
     * @return
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    

    @RequestMapping(value =ERROR_PATH)
    public model handleError(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (404 == code) {
            return new model(404,null,"未找到资源");
        } else if (403 == code) {
            return new model(403,null,"没有访问权限");
        } else if (401 == code) {
            return new model(401,null,"登录过期");
        } else {
            return new model(500,null,"服务器错误");
        }
    }

}
