package rslly.top.jnlabiot.config;

import rslly.top.jnlabiot.controller.jnlab;

import java.lang.annotation.Annotation;
import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class textreadannoation{


    public  static List<String> read(){
        Class<jnlab> j1=jnlab.class;
        Method[] m1=j1.getMethods();
        List<String> l1=new ArrayList<String>();
        for(Method m:m1){
            var a=m.getAnnotation(guest.class);
            if(a!=null){

                l1.add("/api/v2"+a.value());

            }

        }
        System.out.println(l1);
        return l1;
    }

}
