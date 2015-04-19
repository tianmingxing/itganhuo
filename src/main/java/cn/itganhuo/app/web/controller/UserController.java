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

import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.*;
import cn.itganhuo.app.exception.InternalException;
import cn.itganhuo.app.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>用户请求控制器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>提供了对用户中心所有请求的控制</dd>
 * <dt>使用规范</dt>
 * <dd>在控制器里面一定要捕捉到来自服务层的错误，遇到错误记录日志返回状态消息给客户端。</dd>
 * </dl>
 *
 * @author 深圳-小兴，北京-李春雪
 * @version 0.0.2-SNAPSHOT
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private LabelService labelService;


    /**
     * 进入登录页面
     *
     * @return 返回登录页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String refurlSignin() {
        Subject current_user = SecurityUtils.getSubject();
        current_user.logout();
        return "user/signin";
    }

    /**
     * 进入注册页面
     *
     * @return 返回注册页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String refurlRegister() {
        return "user/register";
    }

    /**
     * <h2>处理注册请求</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>
     * <ol>
     * <li>数据校验：判断用户和密码格式是否合法；</li>
     * <li>加密用户密码并添加这个用户，成功后跳转到登录页面。</li>
     * <li>账号添加成功后此时处于锁定状态，用户可以登录会员中心后台，但如果想写文章则必须认证邮箱地址。</li>
     * </ol>
     * </dd>
     * <dt>使用规范</dt>
     * <dd>这种注册方案是平台自己的，如果采用第三方注册登录请参见另外的业务处理方法。</dd>
     * </dl>
     *
     * @param user    用户信息
     * @param request HTTP请求
     * @return 返回处理状态信息
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴，北京-李春雪
     */
    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    RespMsg register(User user, @RequestParam String securityCode, HttpServletRequest request, HttpServletResponse response) {
        RespMsg respMsg = new RespMsg();
        //校验验证码：之所以采用手动校验是因为在集成shiro过滤器时发现诸多不便，同时手动验证灵活性大且应用方便直观。
        String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (captcha == null || !captcha.equalsIgnoreCase(securityCode)) {
            respMsg.setMessage(ConfigPool.getString("respMsg.common.SecurityCodeError"));
            respMsg.setStatus("1005");
            return respMsg;
        } else {
            userService.userRegister(user, request, response);
            respMsg.setAppendInfo(user.getAccount());
        }
        return respMsg;
    }

    /**
     * 通过shiro完成登录功能
     *
     * @param request
     * @return 返回登录状态码
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg signin(User user, @RequestParam String securityCode, HttpServletRequest request) {
        RespMsg respMsg = new RespMsg();
        //校验验证码：之所以采用手动校验是因为在集成shiro过滤器时发现诸多不便
        String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (captcha != null && captcha.equalsIgnoreCase(securityCode)) {
            respMsg = userService.login(user, request);
        } else {
            respMsg.setMessage(ConfigPool.getString("respMsg.common.SecurityCodeError"));
            respMsg.setStatus("1005");
        }
        return respMsg;
    }

    /**
     * 通过第三方QQ完成登录功能
     *
     * @return 返回登录状态码
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/qqSignin", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg qqSignin(User user, @RequestParam String securityCode, @RequestParam int type, @RequestParam String openId, @RequestParam String accessToken, HttpServletRequest request, HttpServletResponse response) {
        RespMsg respMsg = new RespMsg();
        user.setOpenid(openId);
        user.setPassword(openId);
        user.setAccessToken(accessToken);

        if (2 == type) {
            //校验验证码：之所以采用手动校验是因为在集成shiro过滤器时发现诸多不便
            String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if (captcha == null && !captcha.equalsIgnoreCase(securityCode)) {
                respMsg.setMessage(ConfigPool.getString("respMsg.common.SecurityCodeError"));
                respMsg.setStatus("1005");
                return respMsg;
            }
        }
        respMsg = userService.qqSignin(type, user, request, response);
        if ("0000".equals(respMsg.getStatus())) {
            User loginUser = new User();
            loginUser.setAccount(user.getAccount());
            loginUser.setPassword(user.getOpenid());
            respMsg.setAppendInfo(user.getAccount());
            respMsg = userService.login(loginUser, request);
        }
        return respMsg;
    }

    /**
     * 进入用户中心页面
     *
     * @return 转发到用户中心页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public ModelAndView center() {
        ModelAndView mav = new ModelAndView();
        Subject current_user = SecurityUtils.getSubject();
        String account = (String) current_user.getPrincipal();
        if (StringUtil.hasText(account)) {
            User user = userService.loadByAccount(account);
            if (user != null) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", user.getId());
                param.put("offrow", 0);
                param.put("rows", 5);

                // 查询最近发布话题5篇文章
                List<Article> articles = articleService.getArticleByUserId(param);
                // 查询最近参与话题5篇文章
                List<Article> dynamicArticles = articleService.getDynamicArticleByUserId(param);

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

    /**
     * 进入用户中心-文章列表页面
     *
     * @return 转发到用户中心-文章列表页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ModelAndView refurlArticles(@RequestParam(defaultValue = "1") String now_page, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Subject current_user = SecurityUtils.getSubject();
        if (current_user != null) {
            User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
            if (user != null) {
                mav.addObject("user", user);
                int rows = 20;
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", user.getId());
                param.put("offrow", (StringUtil.getInt(now_page, 1) - 1) * rows);
                param.put("rows", rows);

                List<Article> articles = articleService.getArticleByUserId(param);
                int total = articleService.countArticleRows(param);
                Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), rows, 5, total, request.getContextPath().concat("/articles"), "0000");

                mav.addObject("pagination", pagination);
                mav.addObject("articles", articles);
                mav.setViewName("user/articles");
            } else {
                mav.setViewName("user/signin");
            }
        } else {
            mav.setViewName("user/signin");
        }
        return mav;
    }

    /**
     * 进入修改信息页面从session中获取到账户名并找到对应的用户
     *
     * @param model
     * @return 跳转到用户信息修改页面
     * @version 0.0.1-SNAPSHOT
     * @author 小朱，深圳-小兴
     */
    @RequiresAuthentication
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String refurlUpdate(Model model) {
        Subject current_user = SecurityUtils.getSubject();
        User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user != null) {
            User u = userService.loadByAccount(user.getAccount());
            model.addAttribute("user", u);
            return "user/update";
        }
        return "redirect:/user/center";
    }

    /**
     * 修改用户信息 修改用户的基本信息，成功返回主页，失败重新进入修改
     *
     * @param user 用户信息
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 小朱，深圳-小兴
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        // 防止用户修改其关键信息
        user.setPassword(null);
        user.setSalt(null);
        user.setPostDate(null);
        user.setCredits(null);
        // 如果邮箱有值则表示用户想要修改，此时应该将新邮箱设置为未认证状态。
        if (StringUtil.hasText(user.getEmail())) {
            user.setIsValidateEmail(0);
        } else {
            user.setIsValidateEmail(null);
        }
        if (this.userService.updateInfoByAccount(user) != 0) {
            return "redirect:/user/center";
        } else
            return "redirect:/user/update";
    }

    /**
     * 根据账户得到用户信息，跳转到修改密码页面 从session中获取到账户名，找到对应的用户
     *
     * @param model
     * @param session
     * @return 跳转到用户修改密码页面
     * @version 0.0.1-SNAPSHOT
     * @author 小朱
     */
    @RequiresAuthentication
    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public String updatePassword(Model model, HttpSession session) {
        User user = null;
        Subject current_user = SecurityUtils.getSubject();
        user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user == null || user.getId() <= 0) {
            user = userService.loadByAccount(current_user.getPrincipal().toString());
        }
        model.addAttribute("user", user);
        return "user/updatePassword";
    }

    /**
     * 检测用户密码与输入的原始密码是否匹配
     *
     * @param request
     * @param response
     * @return 返回1000表示输入的新密码和旧密码相同
     * @version 0.0.1-SNAPSHOT
     * @author 小朱
     */
    @RequiresAuthentication
    @RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg checkPassword(HttpServletRequest request, HttpServletResponse response) {
        RespMsg respMsg = new RespMsg();
        String originalanpassword = request.getParameter("originalanpassword");
        String account = request.getParameter("account");
        if (account != null && !"".equals(account)) {
            User user = userService.loadByAccount(account);
            String algorithmName = "SHA-512";
            String salt1 = user.getAccount();
            String salt2 = user.getSalt();
            int hashIterations = 2;
            SimpleHash hash = new SimpleHash(algorithmName, originalanpassword, salt1 + salt2, hashIterations);
            if (!hash.toBase64().equals(user.getPassword())) {
                respMsg.setStatus("1000");
                respMsg.setMessage(ConfigPool.getString("respMsg.user.EnterNewPasswordAndOldPasswordSame"));
            }
        }
        return respMsg;
    }

    /**
     * <h2>修改密码</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param user 用户数据
     * @return 密码修改成功后返回到用户中心
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(User user) {
        if (this.userService.updatePasswordByAccount(user)) {
            Template template = templateService.loadById(1);
            if (template != null) {
                String tmp_str = template.getContent();
                if (StringUtil.hasText(tmp_str)) {
                    tmp_str = tmp_str.replaceFirst("##account##", user.getAccount());
                }
                template.setContent(tmp_str);
                mailService.sendMail(user.getEmail(), template);
            } else
                throw new InternalException("Corresponding template does not exist.");
        } else {
            throw new InternalException("Password change fails.");
        }
        return "redirect:/user/center";
    }

    /**
     * 修改头像功能，跳转到修改头像页面
     *
     * @return 转发到用户修改头像页面
     * @version 0.0.1-SNAPSHOT
     * @author 天津小朱，深圳-小兴
     */
    @RequiresAuthentication
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String refurlUpload(Model model, HttpServletRequest request) {
        model.addAttribute("path", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
        return "user/upload";
    }

    /**
     * 完成修改头像功能，把用户的头像存到项目下photo文件夹中
     *
     * @param request
     * @return
     * @author 天津-小朱
     * @version 0.0.1-SNAPSHOT
     */
    @RequiresAuthentication
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(HttpServletRequest request) {
        String msg = "fail";
        User user = null;
        Subject current_user = SecurityUtils.getSubject();
        user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user == null || user.getId() <= 0) {
            user = userService.loadByAccount(current_user.getPrincipal().toString());
        }
        String path = request.getSession().getServletContext().getRealPath("/static/upload/") + "/photos/" + user.getId() + ".jpg";
        File file = new File(path);
        try {
            if (file.exists())
                file.delete();
            else
                file.createNewFile();
            FileUtils.copyInputStreamToFile(request.getInputStream(), file);
            msg = "success";
            log.debug(user.getAccount() + "Path modified image=" + path);
        } catch (IOException e) {
            throw new InternalException(log, "file path=" + path, e);
        }
        log.debug(msg + "," + user.getAccount());
        return msg + "," + user.getAccount();
    }

    /**
     * 进入会员分享页面
     * <ol>
     * <li>必须具备身份已认证权限</li>
     * </ol>
     *
     * @return 转发到用户发布文章页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequiresAuthentication
    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String refurlShare() {
        Subject current_user = SecurityUtils.getSubject();
        User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user != null) {
            return "user/share";
        }
        return "redirect:/user/signin";
    }

    /**
     * 发布一篇文章
     * <ol>
     * <li>必须具备身份已认证权限</li>
     * </ol>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public
    @ResponseBody
    RespMsg share(Article article, @RequestParam String label) {
        RespMsg respMsg = new RespMsg();
        if (article != null && StringUtil.hasText(article.getTitle()) && StringUtil.hasText(article.getContent())) {
            // 获取当前登录用户信息
            Subject current_user = SecurityUtils.getSubject();
            User um = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
            respMsg.setAppendInfo(um.getAccount());
            // 重新组织文章参数
            article.setUserId(um.getId());
            // 保存文章
            articleService.addArticle(article);

            // 保存标签
            if (StringUtil.hasText(label)) {
                String labels[] = label.split(",");
                if (labels != null && labels.length > 0) {
                    // 判断是否超过5个标签，如果大于5个标签则超出的部分不做处理。
                    int lng = (labels.length > 5) ? 5 : labels.length;
                    // 循环处理用户提交过来的标签
                    for (int i = 0; i < lng; i++) {
                        int label_id = 0;
                        // 判断每个标签是否存在于数据库中，如果存在则取出其标签编号.
                        Label l = new Label();
                        l.setName(labels[i].trim());
                        List<Label> list = labelService.getLabelByCondition(l);
                        if (list.size() > 0) {
                            label_id = list.get(0).getId();
                        } else { // 否则新增这个标签并获取它的编号
                            Label l2 = new Label();
                            l2.setUserId(um.getId());
                            l2.setName(labels[i].trim());
                            l2.setPostDate(DateUtil.getNowDateTimeStr(null));
                            labelService.saveLabel(l2);
                            label_id = l2.getId();
                        }
                        // 保存文章和标签之间的关联关系。
                        ArticleLabel asm = new ArticleLabel();
                        asm.setArticleId(article.getId());
                        asm.setLabelId(label_id);
                        asm.setUserId(um.getId());
                        articleService.saveArticleLabel(asm);
                    }
                } else {
                    log.warn("The article label format is not correct.");
                    respMsg.setStatus("2001");
                    respMsg.setMessage(ConfigPool.getString("respMsg.article.ArticlesLabelsCanotEmpty"));
                }
            } else {
                log.warn("The article label can't be empty.");
                respMsg.setStatus("2000");
                respMsg.setMessage(ConfigPool.getString("respMsg.article.ArticlesLabelsCanotEmpty"));
            }
        } else {
            log.warn("Article is a null value.");
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.article.ArticlesCanotEmpty"));
        }
        return respMsg;
    }

    /**
     * 新增一条用户评论
     *
     * @param comment_model
     * @return
     * @version 0.0.2-SNAPSHOT
     * @author 深圳-小兴，深圳-夕落
     */
    @RequiresAuthentication
    @Transactional
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String comment(Comment comment_model, @RequestParam String article_user_id) {
        if (StringUtil.hasText(comment_model.getContent())) {
            Subject current_user = SecurityUtils.getSubject();
            User user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
            if (user_model != null && user_model.getId() > 0 && !article_user_id.equals(String.valueOf(user_model.getId()))) {
                comment_model.setUserId(user_model.getId());
                comment_model.setType(1);
                commentService.addComment(comment_model);
                return "redirect:/article/" + comment_model.getObjId();
            }
        }
        return "redirect:/articles";
    }

    /**
     * 获取所有标签并以json表现形式呈现给客户端
     *
     * @return 返回对象json表现形式
     * @version 0.0.2-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/findLabel")
    @ResponseBody
    public List<AutoComplete> findLabel(@RequestParam String term) {
        List<AutoComplete> auto = new ArrayList<AutoComplete>();
        if (StringUtil.hasText(term)) {
            try {
                term = URLDecoder.decode(term, "UTF-8");
                Label label = new Label();
                label.setName(term);
                List<Label> list = labelService.getLabelByCondition(label);
                auto = this.label2AutoComplete(list);
            } catch (UnsupportedEncodingException e) {
                throw new InternalException(log, e);
            }
        } else {
            log.warn("Query parameters are not allowed to empty.");
        }
        return auto;
    }

    /**
     * 把标签对象转换成自动补全插件想要的数据模型
     *
     * @param labels 标签数据集合
     * @return 返回转换好的AutoComplete对象集合
     * @version 0.0.2-SNAPSHOT
     * @author 深圳-小兴
     */
    private List<AutoComplete> label2AutoComplete(List<Label> labels) {
        List<AutoComplete> autoCompletes = new ArrayList<AutoComplete>();
        if (labels != null && labels.size() > 0) {
            for (int i = 0; i < labels.size(); i++) {
                Label label = labels.get(i);
                AutoComplete ac = new AutoComplete();
                ac.setId(label.getId());
                ac.setLabel(label.getName());
                ac.setValue(label.getName());
                autoCompletes.add(ac);
            }
        }
        return autoCompletes;
    }

    /**
     * 邮件认证<br>
     * 为当前登录用户发送一封邮箱认证邮件
     *
     * @return 转发到邮箱认证页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequiresAuthentication
    @RequestMapping(value = "/emailskip/{account}")
    public String emailApprove(Model model) {
        // 1、查询邮件模板
        Template template = templateService.loadByName("certifiedMail");
        String str = template.getContent();
        // 2、获取变量
        Subject current_user = SecurityUtils.getSubject();
        User user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        String url = templateService.generateAuthURL(user_model.getAccount());
        // 3、数据替换
        str = str.replaceAll("#account#", user_model.getAccount());
        str = str.replaceAll("#url#", url);
        template.setContent(str);
        // 4、发送邮件
        mailService.sendMail(user_model.getEmail(), template);
        return "user/emailskip";
    }

    /**
     * 根据条件翻页查询动态文章
     *
     * @param model 返回数据封装
     * @return
     */
    @RequestMapping(value = "/dynamicArticles", method = RequestMethod.GET)
    public String refurlDynamicArticles(Model model, @RequestParam(defaultValue = "1") String now_page, HttpServletRequest request) {
        Subject current_user = SecurityUtils.getSubject();
        User user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user_model != null) {
            int rows = 20;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId", user_model.getId());
            param.put("offrow", (StringUtil.getInt(now_page, 1) - 1) * rows);
            param.put("rows", rows);
            // 查询最近参与话题5篇文章
            List<Article> dynamicArticles = articleService.getDynamicArticleByUserId(param);
            int total = articleService.countDynamicArticleRows(param);
            Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), rows, 5, total, request.getContextPath().concat("/dynamicArticles"), now_page);
            model.addAttribute("pagination", pagination);
            model.addAttribute("articles", dynamicArticles);
            model.addAttribute("user", user_model);
            return "user/dynamic_articles";
        } else {
            return "redirect:/user/signin";
        }
    }

    /**
     * 通过第三方登录后进入用户信息绑定页面<br>
     *
     * @return
     */
    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String refurlInfoBind() {
        return "user/bind";
    }
}
