package org.itstack.demo.design.agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在这里添加逻辑来决定是否放行方法调用
        // 例如，只放行publicApi方法
        if ("publicApi".equals(method.getName())) {
            System.out.println("我被JDKProxy代理了...publicApi");
            return method.invoke(target, args);
        } else if ("privateApi".equals(method.getName())) {
            // 对于privateApi方法，不调用目标类的方法，而是返回一个固定的字符串
            return "This is a fixed response from the proxy";
        } else {
            // 对于其他方法，可以默认调用目标类的方法，或者根据需要处理
            return method.invoke(target, args);
        }
    }
}