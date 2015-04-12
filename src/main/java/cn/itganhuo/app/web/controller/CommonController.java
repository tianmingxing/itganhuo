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
package cn.itganhuo.app.web.controller;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.Label;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.InternalException;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.LabelService;
import cn.itganhuo.app.service.UserService;

/**
 * <h2>公共访问路径控制类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 *
 * @author 深圳-小兴
 * @version 0.0.1-SNAPSHOT
 */
@Controller
public class CommonController {

    private static final Logger log = LogManager.getLogger(CommonController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ArticleService articleService;

    /**
     * 返回头部公共页面
     *
     * @return 转发到头部页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public String refurlHeader() {
        return "common/header";
    }

    /**
     * 返回底部公共页面
     *
     * @return 转发到底部页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/footer", method = RequestMethod.GET)
    public String refurlFooter() {
        return "common/footer";
    }

    /**
     * 用户登出系统
     *
     * @return 用户登出之后跳转到首页
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject current_user = SecurityUtils.getSubject();
        current_user.logout();
        return "redirect:/articles";
    }

    /**
     * 未授权操作跳转提示页面
     * <ol>
     * <li>在请求未授权路径时会转发到这个信息提示页面。</li>
     * </ol>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/unauthorized")
    public String refurlUnauthorized() {
        return "common/unauthorized";
    }

    /**
     * 进入开发团队介绍页面
     *
     * @return 转发到项目开发团队介绍页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public String refurlTeam(Model model, HttpServletRequest request) {
        model.addAttribute("path", request.getContextPath());
        return "team";
    }

    /**
     * 用户邮箱认证链接处理
     * <ol>
     * <li>
     * 对于请求过来的地址解析两个参数： 一是用户ID，通过它可以查询本次是否超时验证和重复验证； <br>
     * 二是验证码，用来和数据库中存放的验证比进行对比，一致时解锁账号跳转到认证成功页面，否则不做业务处理直接跳转到认证失败页面。</li>
     * </ol>
     *
     * @return
     * @author 深圳-小兴(504487927) 2014-9-8
     * @since itganhuo1.0
     */
    @Transactional
    @RequestMapping(value = "/auth_email/{id}/{code}", method = RequestMethod.GET)
    public ModelAndView authEmail(@PathVariable(value = "id") String id, @PathVariable(value = "code") String code) {
        ModelAndView mav = new ModelAndView();
        RespMsg respMsg = new RespMsg();
        // 验证请求地址
        if (StringUtil.hasText(id) && StringUtil.hasText(code)) {
            User user = userService.loadById(Integer.valueOf(id.trim()));
            // 判断系统中是否存在这个账号
            if (user != null && user.getId() > 0) {
                // 邮箱地址是否已经认证过
                if (1 != user.getIsValidateEmail()) {
                    try {
                        Date validate_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user.getEmailValidateDate());
                        Date now_date = DateUtil.getAfterOrBeforDay(Integer.valueOf(ConfigPool.getString("parameter.emailLinkValidCertification")));
                        // 比较当前时间是否超过有效期
                        if (now_date.after(validate_date)) {
                            // 比较验证码是否正确
                            if (code.trim().equals(user.getEmailValidateCode().trim())) {
                                // 验证成功开始修改用户认证状态并解除锁定
                                User um = new User();
                                um.setAccount(user.getAccount());
                                um.setIsValidateEmail(1);
                                um.setIsLock(0);
                                userService.updateInfoByAccount(um);
                                // 向客户端返回认证结果信息
                                respMsg.setStatus("0000");
                                respMsg.setMessage(MessageFormat.format(ConfigPool.getString("respMsg.email.EmailAuthSuccessful"), user.getAccount()));
                            } else {
                                respMsg.setStatus("1004");
                                respMsg.setMessage(ConfigPool.getString("respMsg.email.CertificationSignatureAddressErrors"));
                            }
                        } else {
                            respMsg.setStatus("1003");
                            respMsg.setMessage(ConfigPool.getString("respMsg.email.LinkSddressFailure"));
                        }
                    } catch (ParseException e) {
                        throw new InternalException(log, "Date conversion error, the date may be the source character is not legitimate.", e);
                    }
                } else {
                    respMsg.setStatus("1002");
                    respMsg.setMessage(ConfigPool.getString("respMsg.email.EmailHasBeenCertified"));
                }
            } else {
                respMsg.setStatus("1001");
                respMsg.setMessage(ConfigPool.getString("respMsg.email.UnknownAccount"));
            }
        } else {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.email.RequestAddressWrong"));
        }
        mav.setViewName("common/email_auth_result");
        mav.addObject("respMsg", respMsg);
        return mav;
    }

    /**
     * 进入开发日志页面
     *
     * @return 转发到项目升级日志页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/devblog", method = RequestMethod.GET)
    public String refurlDevblog(Model model, HttpServletRequest request) {
        model.addAttribute("path", request.getContextPath());
        return "devblog";
    }

    /**
     * 进入关于我们页面
     *
     * @return 转发到关于我们页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String refurlAbout(Model model, HttpServletRequest request) {
        model.addAttribute("path", request.getContextPath());
        return "about";
    }

    /**
     * 进入邀请朋友页面
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 北京-李春雪
     */
    @RequestMapping(value = "/invitation", method = RequestMethod.GET)
    public String invated(Model model, HttpServletRequest request) {
        model.addAttribute("path", request.getContextPath());
        return "invitation";
    }

    /**
     * 进入首页
     *
     * @return 转发到首页
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String refurlIndex(Model model, HttpServletRequest request) {
        // 1、查询标签列表
        List<Label> ls = labelService.getLabelByCondition(null);
        // 2、根据标签查询其所对应的文章
        if (ls != null && ls.size() > 0) {
            for (int i = 0; i < ls.size(); i++) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("labelId", ls.get(i).getId());
                param.put("rows", 5);
                List<Article> articles = articleService.getArticleByLabelId(param);
                ls.get(i).setArticles(articles);
            }
        }
        model.addAttribute("labels", ls);
        model.addAttribute("path", request.getContextPath());
        model.addAttribute("servletPath", request.getServletPath());
        return "index";
    }

    /**
     * 进入标签页面
     *
     * @return 转发到标签页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/labels", method = RequestMethod.GET)
    public String refurlLabels(Model model, HttpServletRequest request) {
        Subject current_user = SecurityUtils.getSubject();
        String account = (String) current_user.getPrincipal();
        if (StringUtil.hasText(account)) {
            User user = userService.loadByAccount(account);
            // 1、查询标签列表
            List<Map<String, String>> lists = labelService.getLabelByCondition2(user.getId());
            model.addAttribute("lists", lists);
        } else {
            // 2、查询标签列表
            List<Label> ls = labelService.getLabelByCondition(null);
            model.addAttribute("ls", ls);
        }
        model.addAttribute("path", request.getContextPath());
        model.addAttribute("servletPath", request.getServletPath());
        return "labels";
    }
}

