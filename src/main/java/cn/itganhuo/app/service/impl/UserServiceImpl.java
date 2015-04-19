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
package cn.itganhuo.app.service.impl;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.HttpUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.AttentionDao;
import cn.itganhuo.app.dao.UserDao;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.EmailUnauthorizedException;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.UserService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户业务处理实现类
 *
 * @author 深圳-小兴
 * @version 0.0.1-SNAPSHOT
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private AttentionDao attentionDao;

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.service.UserService#login(cn.itganhuo.model.User)
     */
    public RespMsg login(User user, HttpServletRequest request) {
        RespMsg respMsg = new RespMsg();
        Subject current_user = SecurityUtils.getSubject();
        // 判断当前用户是否已经登录过，避免重新为它登录。
        if (!current_user.isAuthenticated()) {
            try {
                // 组织登录参数
                UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
                token.setRememberMe(true);
                // 用户登录
                current_user.login(token);
                // 登录成功后将用户信息放到HTTP会话中
                User d_user = this.loadByAccount(user.getAccount());
                current_user.getSession().setAttribute(ConstantPool.USER_SHIRO_SESSION_ID, d_user);
                respMsg.setAppendInfo(d_user.getAccount());
                // 更新用户最近登录时间和登录IP
                User tmp = new User();
                tmp.setAccount(user.getAccount());
                tmp.setIsLock(0);
                tmp.setLastLoginDate(DateUtil.getNowDateTimeStr(null));
                tmp.setLastLoginIp(StringUtil.getNowHttpIp(request));
                this.updateInfoByAccount(tmp);
            } catch (UnknownAccountException e) {
                respMsg.setMessage(ConfigPool.getString("respMsg.login.UnknownAccount"));
                respMsg.setStatus("1000");
            } catch (IncorrectCredentialsException e) {
                respMsg.setMessage(ConfigPool.getString("respMsg.login.IncorrectCredentials"));
                respMsg.setStatus("1001");
            } catch (LockedAccountException e) {
                respMsg.setMessage(ConfigPool.getString("respMsg.login.LockedAccount"));
                respMsg.setStatus("1002");
            } catch (EmailUnauthorizedException e) {
                respMsg.setMessage(ConfigPool.getString("respMsg.login.EmailUnauthorized"));
                respMsg.setStatus("1003");
            } catch (AuthenticationException e) {
                respMsg.setMessage(ConfigPool.getString("respMsg.login.Authentication"));
                respMsg.setStatus("1004");
            }
        }
        return respMsg;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.service.UserService#insert(cn.itganhuo.model.User)
     */
    public int insert(User user) {
        return userDao.insert(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.app.service.UserService#loadByAccount(java.lang.String)
     */
    public User loadByAccount(String account) {
        User d_user = null;
        if (StringUtil.hasText(account)) {
            // 1、判断账号是否存在并且是否锁定状态
            d_user = userDao.loadByAccount(account);
            if (d_user == null) {
                throw new UnknownAccountException();
            }
            if (0 != d_user.getIsLock()) {
                throw new LockedAccountException();
            }
        }
        return d_user;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.app.service.UserService#loadById(java.lang.String)
     */
    public User loadById(int id) {
        if (id > 0)
            return userDao.loadById(id);
        else
            return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.app.service.UserService#updateInfoByAccount(cn.itganhuo.app.entity.User)
     */
    public int updateInfoByAccount(User user) {
        return userDao.updateInfoByAccount(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.app.service.UserService#updatePasswordByAccount(cn.itganhuo.app.entity.User)
     */
    public boolean updatePasswordByAccount(User user) {
        String algorithmName = "SHA-512";
        String salt1 = user.getAccount();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1.concat(salt2), hashIterations);
        user.setPassword(hash.toBase64());
        user.setSalt(salt2);
        return this.userDao.updatePasswordByAccount(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see cn.itganhuo.app.service.UserService#getUserList(java.util.Map)
     */
    public Map<String, List<Object>> getUserList(Map<Object, Object> condition) {
        List<Object> users = userDao.getUserList(condition);
        List<Object> total = new ArrayList<Object>();
        if (users != null && users.size() > 0) {
            total.add(userDao.countUserList(condition));
        }
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        map.put("total", total);
        map.put("rows", users);
        return map;
    }

    @Override
    public User loadbyOpenId(String openId) {
        return userDao.loadbyOpenId(openId);
    }

    @Transactional
    @Override
    public RespMsg userRegister(User user, HttpServletRequest request, HttpServletResponse response) {
        RespMsg respMsg = new RespMsg();
        // SQL特殊字符转义防御SQL注入
        String tmpAccount = StringEscapeUtils.escapeSql(user.getAccount());
        String tmpPassword = StringEscapeUtils.escapeSql(user.getPassword());
        user.setAccount(tmpAccount);
        user.setPassword(tmpPassword);
        // 判断用户名长度是否在区间值内
        if (user.getAccount().length() < 6 || user.getAccount().length() > 20) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
            return respMsg;
        }
        // 判断用户名是否含有特殊字符
        if (!StringUtil.ifContainsSpecialStr(user.getAccount())) {
            respMsg.setStatus("1001");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
            return respMsg;
        }
        // 判断密码长度是否在区间值内
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            respMsg.setStatus("2000");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
            return respMsg;
        }
        // 判断密码是否含有特殊字符
        String[] s = {"`", "~", "#", "$", "%", "^", "&", "*", "(", ")", "-", "=", "+", "{", "}", "[", "]", "|", "\\", ";", ":", "\'", "\"", "<", ">", ",", "/"};
        if (!StringUtil.ifContainsSpecialStr(user.getPassword(), s)) {
            respMsg.setStatus("2001");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
            return respMsg;
        }
        // 判断用户名、密码中是否含有中文字符
        if (user.getAccount().matches("[\u4e00-\u9fa5]+") || user.getPassword().matches("[\u4e00-\u9fa5]+")) {
            respMsg.setStatus("3000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.CanNotContainChineseStr"));
            return respMsg;
        }
        // 判断将要注册的账号是否已经存在
        User tmp_user = userDao.loadByAccount(user.getAccount());
        if (tmp_user != null) {
            respMsg.setStatus("1002");
            respMsg.setMessage(ConfigPool.getString("respMsg.login.UnknownAccount"));
            return respMsg;
        }
        // 对密码加密
        String algorithmName = "SHA-512";
        String salt1 = user.getAccount();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1.concat(salt2), hashIterations);
        // 再次组装用户数据
        user.setPassword(hash.toBase64());
        user.setSalt(salt2);
        user.setIsLock(0);
        user.setPostDate(DateUtil.getNowDateTimeStr(null));
        user.setType(1);
        // 保存用户
        userDao.insert(user);
        HttpUtil.setCookie(response, ConstantPool.USER_ACCOUNT_COOKIE_ID, user.getAccount());
        return respMsg;
    }

    @Override
    public RespMsg qqSignin(int type, User user, HttpServletRequest request, HttpServletResponse response) {
        RespMsg respMsg = new RespMsg();
        if (!StringUtil.hasText(user.getOpenid()) || !StringUtil.hasText(user.getAccessToken())) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.ManuallyRequestPagePrompts"));
            return respMsg;
        }

        //1、查询该OPENID是否存在，如果有则直接帮它自动登录。
        User userInfo = this.loadbyOpenId(user.getOpenid());
        if (userInfo == null) {
            if (1 == type) {
                respMsg.setStatus("0001");
            } else {
                //2、如果不存在则先保存当前的用户再进行登录
                respMsg = this.userRegister(user, request, response);
            }
        } else {
            User loginUser = new User();
            loginUser.setAccount(userInfo.getAccount());
            loginUser.setPassword(userInfo.getOpenid());
            respMsg.setAppendInfo(userInfo.getAccount());
            //如果查询到了绑定的要本地账号则自动登录
            respMsg = this.login(loginUser, request);
        }

        return respMsg;
    }

    @Override
    public ModelAndView center() {
        ModelAndView mav = new ModelAndView();
        Subject current_user = SecurityUtils.getSubject();
        String account = (String) current_user.getPrincipal();
        if (StringUtil.hasText(account)) {
            User user = userDao.loadByAccount(account);
            if (user != null) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", user.getId());
                param.put("offrow", 0);
                param.put("rows", 5);

                // 查询最近发布话题5篇文章
                List<Article> articles = articleService.getArticleByUserId(param);
                // 查询最近参与话题5篇文章
                List<Article> dynamicArticles = articleService.getDynamicArticleByUserId(param);

                //统计关注数量
                Map<String, String> param3 = new HashMap<String, String>();
                param3.put("userId", String.valueOf(user.getId()));
                param3.put("type", String.valueOf(1));
                int attentionNumber1 = attentionDao.countAttentionByCondition(param3);
                param3.put("type", String.valueOf(2));
                int attentionNumber2 = attentionDao.countAttentionByCondition(param3);

                //统计粉丝数量
                Map<String, String> param4 = new HashMap<String, String>();
                param4.put("byUserId", String.valueOf(user.getId()));
                param4.put("type", String.valueOf(1));
                int fansNumber1 = attentionDao.countAttentionByCondition(param4);
                param4.put("type", String.valueOf(2));
                int fansNumber2 = attentionDao.countAttentionByCondition(param4);

                //统计收藏数量
                Map<String, String> param2 = new HashMap<String, String>();
                param2.put("userId", String.valueOf(user.getId()));
                param2.put("type", String.valueOf(3));
                int collectionNumber = attentionDao.countAttentionByCondition(param2);

                mav.addObject("fansNumber", fansNumber1 + fansNumber2);
                mav.addObject("attentionNumber", attentionNumber1 + attentionNumber2);
                mav.addObject("collectionNumber", collectionNumber);
                mav.addObject("dynamicArticles", dynamicArticles);
                mav.addObject("articles", articles);
                mav.addObject("user", user);
                mav.setViewName("user/center");
            } else {
                mav.setViewName("user/signin");
            }
        } else {
            mav.setViewName("user/signin");
        }
        return mav;
    }

}
