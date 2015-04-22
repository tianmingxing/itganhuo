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
import cn.itganhuo.app.common.pool.ThreadLocalManager;
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
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
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

    /**
     * 判断是否来自第三方的请求
     *
     * @param request
     * @param response
     * @return
     */
    private boolean qqSignin(HttpServletRequest request, HttpServletResponse response) {
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null, openID = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj == null || accessTokenObj.getAccessToken().equals("")) {
                //没有获取到响应参数，我们的网站被CSRF攻击了或者用户取消了授权，此时跳转到登录页面。
                return false;
            } else {
                // 获取accessToken
                accessToken = accessTokenObj.getAccessToken();
                // 获取token有效时间
                tokenExpireIn = accessTokenObj.getExpireIn();

                // 利用获取到的accessToken去获取当前用的openid
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                request.getSession().setAttribute(ConstantPool.ACCESS_TOKEN, accessToken);
                request.getSession().setAttribute(ConstantPool.TOKEN_EXPIREIN, String.valueOf(tokenExpireIn));
                request.getSession().setAttribute(ConstantPool.OPEN_ID, openID);
            }
        } catch (QQConnectException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    @Override
    public ModelAndView center(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        Subject current_user = SecurityUtils.getSubject();
        String account = (String) current_user.getPrincipal();
        if (StringUtil.hasText(account)) {
            User user = userDao.loadByAccount(account);
            if (user != null) {
                mav = this.enterCenter(user);
            } else {
                mav.setViewName("user/signin");
            }
        } else {
            //判断是否是从第三方过来的请求，返回true表示采用了QQ第三方登录。
            if (this.qqSignin(request, response)) {
                //开始为用户模拟登录
                String openID = (String) request.getSession().getAttribute("openid");
                User userInfo = this.loadbyOpenId(openID);

                if (userInfo != null) {
                    User loginUser = new User();
                    loginUser.setAccount(userInfo.getAccount());
                    loginUser.setPassword("");

                    ThreadLocalManager.getInstance().setValue(ConstantPool.LOGIN_TYPE);
                    RespMsg respMsg = this.login(loginUser, request);
                    ThreadLocalManager.getInstance().remove();

                    if ("0000".equals(respMsg.getStatus())) {
                        mav = this.enterCenter(userInfo);
                    }
                } else {
                    // 如果没有发现本地账号则进入信息绑定页面
                    mav.setViewName("user/bind");
                }

            } else {
                mav.setViewName("user/signin");
            }
        }
        return mav;
    }

    /**
     * 准确用户中心页面所需要的资料
     *
     * @param user
     * @return
     */
    private ModelAndView enterCenter(User user) {
        ModelAndView mav = new ModelAndView();
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
        return mav;
    }

}
