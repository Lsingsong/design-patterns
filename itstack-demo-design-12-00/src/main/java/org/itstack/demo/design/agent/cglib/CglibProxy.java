package org.itstack.demo.design.agent.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.itstack.demo.design.agent.Select;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    public Object newInstall(Object object) {
        return Enhancer.create(object.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName());

        Select select = method.getAnnotation(Select.class);
        Annotation[] annotations = method.getDeclaredAnnotations();

        System.out.println("SQL：" + select.value().replace("#{uId}", objects[0].toString()));

        System.out.println("我被CglibProxy代理了...");
        return "我被CglibProxy代理了...";
        //return methodProxy.invokeSuper(o, objects);
    }
}
