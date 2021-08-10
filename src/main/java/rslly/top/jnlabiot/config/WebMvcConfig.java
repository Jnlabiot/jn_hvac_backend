package rslly.top.jnlabiot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Bean
    public InterceptorDemo interceptorDemo(){
        return new InterceptorDemo();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( interceptorDemo())
                .addPathPatterns("/api/v2/**")
                .excludePathPatterns("/error","/api/v2/login");
        registry.addInterceptor(new UserAthority())
                .addPathPatterns(textreadannoation.read())
                .excludePathPatterns("/error","/api/v2/login");

    }


}