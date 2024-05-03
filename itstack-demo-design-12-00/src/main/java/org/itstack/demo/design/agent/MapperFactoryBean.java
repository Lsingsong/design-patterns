package org.itstack.demo.design.agent;

import net.sf.cglib.proxy.Enhancer;
import org.itstack.demo.design.agent.cglib.CglibProxy;
import org.itstack.demo.design.agent.jdk.JDKProxy;
import org.itstack.demo.design.agent.service.UserService;
import org.springframework.beans.factory.FactoryBean;

public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        T proxy = JDKProxy.getProxy(mapperInterface);


        CglibProxy cglibProxy = new CglibProxy();
        UserService userService = (UserService) cglibProxy.newInstall(new UserService());
        String userName = userService.queryUserNameById("10001");
        System.out.println(userName);

        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
