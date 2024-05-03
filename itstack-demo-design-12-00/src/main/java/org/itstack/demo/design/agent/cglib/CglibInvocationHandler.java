package org.itstack.demo.design.agent.cglib;


import net.sf.cglib.proxy.InvocationHandler;
import org.itstack.demo.design.agent.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CglibInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(method.getName());

        Select select = method.getAnnotation(Select.class);
        Annotation[] annotations = method.getDeclaredAnnotations();

        System.out.println("SQL：" + select.value().replace("#{uId}", objects[0].toString()));

        System.out.println("我被JDKProxy代理了...");

        return objects[0] + ",我被JDKProxy代理了...";
    }
}
