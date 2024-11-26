package com.example.prac.util;


import org.springframework.context.ApplicationContext;

public class BeanUtils {
    public static Object getBean(String beanId) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        if (applicationContext == null) {
            throw new NullPointerException("Spring Application init error!!!!");
        }

        return applicationContext.getBean(beanId);
    }
}
