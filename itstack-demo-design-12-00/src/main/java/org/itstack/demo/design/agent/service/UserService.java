package org.itstack.demo.design.agent.service;

import org.itstack.demo.design.agent.Select;

/**
 * 微信公众号：bugstack虫洞栈 | 欢迎关注学习专题案例
 * 论坛：http://bugstack.cn
 * Create by 付政委 on @2019
 */
public class UserService implements IUserService {

    @Select("select userName from user where id = #{uId}")
    public String queryUserNameById(String userId) {
        return "hi user " + userId;
    }

}
