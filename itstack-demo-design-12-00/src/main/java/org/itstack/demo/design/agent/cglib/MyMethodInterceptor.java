package org.itstack.demo.design.agent.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 在这里添加逻辑来决定是否放行方法调用
        // 例如，只放行publicApi方法
        if ("publicApi".equals(method.getName())) {
            System.out.println("我被CglibProxy代理了...publicApi");
            return proxy.invokeSuper(obj, args); // 使用invokeSuper调用父类（即目标类）的方法
        } else if ("privateApi".equals(method.getName())) {
            // 对于privateApi方法，不调用目标类的方法，而是返回一个固定的字符串
            return "This is a fixed response from the proxy";
        } else {
            // 对于其他方法，可以默认调用目标类的方法，或者根据需要处理
            return proxy.invokeSuper(obj, args);
        }
    }
}