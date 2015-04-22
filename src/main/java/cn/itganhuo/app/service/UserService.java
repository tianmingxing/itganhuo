/*
 * Copyright 2014-2024 the https://github.com/xiaoxing598/itganhuo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * This project consists of JAVA private school online learning community group Friends co-creator [QQ group 329232140].
 * 本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140];
 * See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams];
 * The author does not guarantee the quality of the project and its stability, reliability, and security does not bear any responsibility.
 * 作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任.
 */
package cn.itganhuo.app.service;

import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户业务处理接口
 *
 * @author 深圳-小兴，天津-天津-小朱
 * @version 0.0.1-SNAPSHOT
 */
public interface UserService {

    /**
     * 根据登录账号查询对应的唯一一条记录
     *
     * @param user 账号
     * @return 会员信息
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public RespMsg login(User user, HttpServletRequest request);

    /**
     * 会员注册
     * <ol>
     * <li>收集会员账号和密码保存到数据库</li>
     * </ol>
     *
     * @param user
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public int insert(User user);

    /**
     * 根据账户查找用户信息
     *
     * @param account 账号
     * @return 用户
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    public User loadByAccount(String account);

    /**
     * 根据主键查询一条用户信息
     *
     * @param id 主键
     * @return 用户
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public User loadById(int id);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    public int updateInfoByAccount(User user);

    /**
     * 修改用户密码
     *
     * @param user
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    public boolean updatePasswordByAccount(User user);

    /**
     * <h2>根据条件查询用户列表信息</h2>
     *
     * @param condition 查询条件
     * @return 经过筛选后的用户集合
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public Map<String, List<Object>> getUserList(Map<Object, Object> condition);

    /**
     * <h2>根据身份编号查询用户列表信息</h2>
     *
     * @param openId 身份编号
     * @return 经过筛选后的用户集合
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    public User loadbyOpenId(String openId);

    /**
     * 用户信息注册
     *
     * @param user     用户信息
     * @param request
     * @param response
     * @return
     */
    public RespMsg userRegister(User user, HttpServletRequest request, HttpServletResponse response);

    /**
     * 进入会员中心主页，本方法主要是查询首页上的数据。
     *
     * @return
     */
    public ModelAndView center(HttpServletRequest request, HttpServletResponse response);
}
