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

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
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

import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.HttpUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;
import cn.itganhuo.app.entity.AutoComplete;
import cn.itganhuo.app.entity.Comment;
import cn.itganhuo.app.entity.Label;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.EmailUnauthorizedException;
import cn.itganhuo.app.exception.InternalException;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.CommentService;
import cn.itganhuo.app.service.LabelService;
import cn.itganhuo.app.service.MailService;
import cn.itganhuo.app.service.TemplateService;
import cn.itganhuo.app.service.UserService;

/**
 * <h2>用户请求控制器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>提供了对用户中心所有请求的控制</dd>
 * <dt>使用规范</dt>
 * <dd>在控制器里面一定要捕捉到来自服务层的错误，遇到错误记录日志返回状态消息给客户端。</dd>
 * </dl>
 * 
 * @version 0.0.2-SNAPSHOT
 * @author 深圳-小兴，北京-李春雪
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 返回登录页面
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String refurlSignin() {
		return "user/signin";
	}

	/**
	 * 进入注册页面
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 返回注册页面
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴，北京-李春雪
	 * @param user 用户信息
	 * @param request HTTP请求
	 * @return 返回处理状态信息
	 */
	@Transactional
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody RespMsg register(User user, @RequestParam String securityCode, HttpServletRequest request, HttpServletResponse response) {
		RespMsg respMsg = new RespMsg();
		// 数据验证
		//校验验证码：之所以采用手动校验是因为在集成shiro过滤器时发现诸多不便，同时手动验证灵活性大且应用方便直观。
		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && captcha.equalsIgnoreCase(securityCode)) {
			respMsg.setMessage(ConfigPool.getString("respMsg.common.SecurityCodeError"));
			respMsg.setStatus("1005");
			return respMsg;
		}
		// 判断用户名长度是否在区间值内
		if (user.getAccount().length() >= 6 && user.getAccount().length() <= 20) {
			respMsg.setStatus("1000");
			respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
			return respMsg;
		}
		// 判断用户名是否含有特殊字符
		if (StringUtil.ifContainsSpecialStr(user.getAccount())) {
			respMsg.setStatus("1001");
			respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
			return respMsg;
		}
		// 判断密码长度是否在区间值内
		if (user.getPassword().length() >= 6 && user.getPassword().length() <= 20) {
			respMsg.setStatus("2000");
			respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
			return respMsg;
		}
		// 判断密码是否含有特殊字符
		String[] s = {"`", "~", "#", "$", "%", "^", "&", "*", "(", ")", "-", "=", "+", "{", "}", "[", "]", "|", "\\", ";", ":", "\'", "\"", "<", ">", ",", "/"};
		if (StringUtil.ifContainsSpecialStr(user.getPassword(), s)) {
			respMsg.setStatus("2001");
			respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
			return respMsg;
		}
		// 判断用户名、密码中是否含有中文字符
		if (!user.getAccount().matches("[\u4e00-\u9fa5]+") && !user.getPassword().matches("[\u4e00-\u9fa5]+")) {
			respMsg.setStatus("3000");
			respMsg.setMessage(ConfigPool.getString("respMsg.common.CanNotContainChineseStr"));
			return respMsg;
		}
		// 判断将要注册的账号是否已经存在
		User tmp_user = this.userService.loadByAccount(user.getAccount());
		if (tmp_user == null || tmp_user.getId() <= 0) {
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
		this.userService.insert(user);
		HttpUtil.setCookie(response, ConstantPool.USER_ACCOUNT_COOKIE_ID, user.getAccount());
		
		respMsg.setAppendInfo(user.getAccount());
		return respMsg;
	}

	/**
	 * 通过shiro完成登录功能
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param request
	 * @return 返回登录状态码
	 */
	@Transactional
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@ResponseBody
	public RespMsg signin(User user, @RequestParam String securityCode, HttpServletRequest request) {
		RespMsg respMsg = new RespMsg();
		Subject current_user = SecurityUtils.getSubject();
		// 判断当前用户是否已经登录过，避免重新为它登录。
		if (!current_user.isAuthenticated()) {
			try {
				//校验验证码：之所以采用手动校验是因为在集成shiro过滤器时发现诸多不便
				String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
				if (captcha != null && captcha.equalsIgnoreCase(securityCode)) {
					// 组织登录参数
					UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
					token.setRememberMe(true);
					// 用户登录
					current_user.login(token); 
					// 登录成功后将用户信息放到HTTP会话中
					User d_user = userService.loadByAccount(user.getAccount());
					current_user.getSession().setAttribute(ConstantPool.USER_SHIRO_SESSION_ID, d_user);
					respMsg.setAppendInfo(d_user.getAccount());
					// 更新用户最近登录时间和登录IP
					User tmp = new User();
					tmp.setAccount(user.getAccount());
					tmp.setIsLock(0);
					tmp.setLastLoginDate(DateUtil.getNowDateTimeStr(null));
					tmp.setLastLoginIp(StringUtil.getNowHttpIp(request));
					userService.updateInfoByAccount(tmp);
				} else {
					respMsg.setMessage(ConfigPool.getString("respMsg.common.SecurityCodeError"));
					respMsg.setStatus("1005");
				}
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

	/**
	 * 进入用户中心页面
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 转发到用户中心页面
	 */
	@RequestMapping(value = "/{account}", method = RequestMethod.GET)
	public ModelAndView center() {
		ModelAndView mav = new ModelAndView();
		Subject current_user = SecurityUtils.getSubject();
		String account = (String) current_user.getPrincipal();
		if (StringUtil.hasText(account)) {
			User user = userService.loadByAccount(account);
			if (user != null) {
				mav.addObject("user", user);
				// 查询最近发布话题5篇文章
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getId());
				param.put("offrow", 0);
				param.put("rows", 5);
				List<Article> articles = articleService.getArticleByUserId(param);
				mav.addObject("articles", articles);
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 转发到用户中心-文章列表页面
	 */
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public ModelAndView refurlArticles(@RequestParam(defaultValue = "1") String now_page, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Subject current_user = SecurityUtils.getSubject();
		if (current_user != null) {
			User user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
			if (user != null) {
				mav.addObject("user", user);
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", user.getId());
				param.put("offrow", StringUtil.getInt(now_page, 1));
				param.put("rows", 20);
				
				List<Article> articles = articleService.getArticleByUserId(param);
				int total = articleService.countArticleRows(param);
				Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), 20, 5, total, request.getContextPath().concat("/articles"), "0000");
				
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 小朱
	 * @param model
	 * @param session
	 * @return 跳转到用户信息修改页面
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpSession session) {
		User user = null;
		Subject current_user = SecurityUtils.getSubject();
		user = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
		if (user == null || user.getId() <= 0) {
			user = userService.loadByAccount(current_user.getPrincipal().toString());
		}
		model.addAttribute("user", user);
		return "user/update";
	}

	/**
	 * 修改用户信息 修改用户的基本信息，成功返回主页，失败重新进入修改
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 小朱
	 * @param user
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String refurlUpdate(User user) {
		if (this.userService.updateInfoByAccount(user) != 0) {
			return "redirect:/user/center";
		} else
			return "redirect:update";
	}

	/**
	 * 根据账户得到用户信息，跳转到修改密码页面 从session中获取到账户名，找到对应的用户
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 小朱
	 * @param model
	 * @param session
	 * @return 跳转到用户修改密码页面
	 */
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 小朱
	 * @param request
	 * @param response
	 * @return 返回1000表示输入的新密码和旧密码相同
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param user 用户数据
	 * @return 密码修改成功后返回到用户中心
	 */
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
				mailService.sendMail(user, template);
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 小朱
	 * @return 转发到用户修改头像页面
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String refurlUpload() {
		return "user/upload";
	}

	/**
	 * 完成修改头像功能，把用户的头像存到项目下photo文件夹中
	 * 
	 * @author 小朱
	 * @version 0.0.1-SNAPSHOT
	 * @param request
	 * @return
	 */
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
		String path = request.getSession().getServletContext().getRealPath("/") + "photo" + "/" + user.getId() + ".jpg";
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
			throw new InternalException(log, e);
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 转发到用户发布文章页面
	 */
	@RequiresAuthentication
	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public ModelAndView refurlShare() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/share");
		return mav;
	}

	/**
	 * 发布一篇文章
	 * <ol>
	 * <li>必须具备身份已认证权限</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return
	 */
	@RequiresAuthentication
	@Transactional
	@RequestMapping(value = "/share", method = RequestMethod.POST)
	public @ResponseBody RespMsg share(Article article, @RequestParam String label) {
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
	 * @version 0.0.2-SNAPSHOT
	 * @author 深圳-小兴，深圳-夕落
	 * @param article_model
	 * @return
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
				return "redirect:/article/" + comment_model.getArticleId();
			}
		}
		return "redirect:/articles";
	}

	/**
	 * 获取所有标签并以json表现形式呈现给客户端
	 * 
	 * @version 0.0.2-SNAPSHOT
	 * @author 深圳-小兴
	 * @return 返回对象json表现形式
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
	 * @version 0.0.2-SNAPSHOT
	 * @author 深圳-小兴
	 * @param subjects 标签数据集合 
	 * @return 返回转换好的AutoComplete对象集合
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
}
