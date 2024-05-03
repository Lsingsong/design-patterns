package org.itstack.demo.design.test;

import net.sf.cglib.proxy.Enhancer;
import org.itstack.demo.design.agent.IUserDao;
import org.itstack.demo.design.agent.cglib.CglibProxy;
import org.itstack.demo.design.agent.cglib.MyMethodInterceptor;
import org.itstack.demo.design.agent.jdk.JDKProxy;
import org.itstack.demo.design.agent.jdk.MyInvocationHandler;
import org.itstack.demo.design.agent.service.IUserService;
import org.itstack.demo.design.agent.service.MyService;
import org.itstack.demo.design.agent.service.MyServiceImpl;
import org.itstack.demo.design.agent.service.UserService;
import org.itstack.demo.design.agent.util.ClassLoaderUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_IUserDao() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring-config.xml");
        IUserDao userDao = beanFactory.getBean("userDao", IUserDao.class);
        String res = userDao.queryUserInfo("100001");
        logger.info("测试结果：{}", res);
    }


    @Test
    public void test_proxy_jdk1() throws Exception {
        MyService target = new MyServiceImpl();
        MyInvocationHandler handler = new MyInvocationHandler(target);
        ClassLoader classLoader = ClassLoaderUtils.getCurrentClassLoader();
        MyService proxy = (MyService) Proxy.newProxyInstance(
                classLoader,
                new Class<?>[]{MyService.class},
                handler);

        // 现在你可以通过代理对象来调用方法，InvocationHandler将决定是否放行
        proxy.publicApi();  // 将执行publicApi方法
        proxy.privateApi(); // 将抛出异常

        // 调用privateApi方法将不会执行目标类中的privateApi方法，而是返回"This is a fixed response from the proxy"
        String response = (String) proxy.privateApi();
        System.out.println(response); // 输出：This is a fixed response from the proxy
    }

    @Test
    public void test_proxy_jdk() throws Exception {

        IUserService proxy = (IUserService) JDKProxy.getProxy(ClassLoaderUtils.forName("org.itstack.demo.design.agent.service.IUserService"));
        String userName = proxy.queryUserNameById("10001");
        System.out.println(userName);

        String name = "ProxyUserService";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{IUserService.class});

        // 输出类字节码
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(name + ".class");
            System.out.println((new File("")).getAbsolutePath());
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test_proxy_cglib1() {
        MyServiceImpl target = new MyServiceImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyServiceImpl.class); // 设置目标类
        enhancer.setCallback(new MyMethodInterceptor()); // 设置回调（即MethodInterceptor）
        MyService proxy = (MyService) enhancer.create(); // 创建代理对象

        //MyService proxy = (MyService) Enhancer.create(target.getClass(), new MyMethodInterceptor());

        // 现在你可以通过代理对象来调用方法，MethodInterceptor将决定是否放行
        proxy.publicApi(); // 将执行publicApi方法
        proxy.privateApi(); // 将抛出异常

        // 调用privateApi方法将不会执行目标类中的privateApi方法，而是返回"This is a fixed response from the proxy"
        String response = (String) proxy.privateApi();
        System.out.println(response); // 输出：This is a fixed response from the proxy
    }

    @Test
    public void test_proxy_cglib() {
        CglibProxy cglibProxy = new CglibProxy();
        UserService userService = (UserService) cglibProxy.newInstall(new UserService());
        String userName = userService.queryUserNameById("10001");
        System.out.println(userName);
    }

}
