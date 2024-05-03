package org.itstack.demo.design.agent.service;

public class MyServiceImpl implements MyService {
    @Override  
    public void publicApi() {  
        // 实现公共API逻辑
        System.out.println("实现公共API逻辑...publicApi");
    }  
  
    @Override  
    public String privateApi() {
        // 实现私有API逻辑
        System.out.println("实现私有API逻辑...privateApi");

        return "实现私有API逻辑...privateApi";
    }  
}