package rslly.top.jnlabiot.config;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface guest {
    String value() ;
}
