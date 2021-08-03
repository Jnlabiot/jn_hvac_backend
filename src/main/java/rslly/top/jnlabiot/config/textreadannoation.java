package rslly.top.jnlabiot.config;

import rslly.top.jnlabiot.controller.jnlab;

import java.lang.annotation.Annotation;
import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class textreadannoation{


    public  static void read(){
        Class<jnlab> j1=jnlab.class;
        Method[] m1=j1.getMethods();
        StringBuffer s1=new StringBuffer();
        for(Method m:m1){
            var a=m.getAnnotation(guest.class);
            if(a!=null){
                System.out.println(a.value());
                s1.append(a.value());

            }

        }
        System.out.println(s1);
    }

}
