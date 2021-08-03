package rslly.top.jnlabiot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorDemo())
                .addPathPatterns("/api/v2/**")
                .excludePathPatterns("/error","/api/v2/login");
        registry.addInterceptor(new UserAthority())
                .addPathPatterns("/api/v2/addNewTool")
                .excludePathPatterns("/error","/api/v2/login");

    }


}