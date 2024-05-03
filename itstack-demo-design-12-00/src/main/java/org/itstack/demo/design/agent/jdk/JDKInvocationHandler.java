package org.itstack.demo.design.agent.jdk;

import org.itstack.demo.design.agent.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName());

        Select select = method.getAnnotation(Select.class);
        Annotation[] annotations = method.getDeclaredAnnotations();

        System.out.println("SQL：" + select.value().replace("#{uId}", args[0].toString()));

        System.out.println("我被JDKProxy代理了...");


        return args[0] + ",--------> 我被JDKProxy代理了...";
    }
}
