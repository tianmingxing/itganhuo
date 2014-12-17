package cn.itganhuo.app.web.controller;

/*
  * Solemnly declare(2014-11-11):
  * 1 This project is a collective creation of its copyrighted Java private school online learning
 community (QQ group number 329 232 140) of all, is prohibited without the author's permission for
 commercial profit without permission;
  * 2 See the list of IT ganhuo sharing network http://www.itganhuo.cn/teams;
  * 3, the author does not guarantee the quality of the project and its stability, reliability, and
 security does not assume any responsibility if you get the source code for this project in some
 way, all the consequences of the subsequent occurrence of ego nothing to do with this group and 
 author;
  * 4 mx.tian@qq.com have any questions, please contact us.
  *
 * 郑重声明(2014-11-11)：
 * 1、本项目属集体创作其版权归Java私塾在线学习社区（QQ群号329232140）作者所有，禁止未经作者授权擅自用于商业盈利；
 * 2、作者名单详见IT干货技术分享网http://www.itganhuo.cn/teams；
 * 3、作者不保证项目质量并对其稳定性、可靠性和安全性不承担任何责任，如果你通过某些途径获取本项目源代码，其后发生的一切后果自负与本群及作者无关；
 * 4、有任何问题请与我们联系mx.tian@qq.com。
 */

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.HttpUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.Article;
import cn.itganhuo.app.entity.ArticleLabel;
import cn.itganhuo.app.entity.Comment;
import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.entity.AutoComplete;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.EmailUnauthorizedException;
import cn.itganhuo.app.exception.InternalException;
import cn.itganhuo.app.service.ArticleService;
import cn.itganhuo.app.service.CommentService;
import cn.itganhuo.app.service.EmailTemplateService;
import cn.itganhuo.app.service.MailService;
import cn.itganhuo.app.service.SubjectService;
import cn.itganhuo.app.service.UserService;

/**
 * 用户控制器
 * 
 * @author 深圳-小兴(504487927) 2014年7月7日 下午11:54:04
 * @since itganhuo1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  private UserService userService;
  private MailService mailService;
  private ArticleService articleService;
  private CommentService commentService;
  private EmailTemplateService emailTemplateService;
  private SubjectService subjectService;

  @Autowired
  public void setCommentService(CommentService commentService) {
    this.commentService = commentService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setMailService(MailService mailService) {
    this.mailService = mailService;
  }

  @Autowired
  public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
    this.emailTemplateService = emailTemplateService;
  }

  @Autowired
  public void setArticleService(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Autowired
  public void setSubjectService(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  /**
   * 进入登录页面
   * 
   * @author 深圳-小兴(504487927) 2014年7月20日 下午4:28:09
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/signin", method = RequestMethod.GET)
  public String refurlSignin() {
    return "user/signin";
  }

  /**
   * 进入注册页面
   * 
   * @author 深圳-小兴(504487927) 2014年7月7日 下午11:55:33
   * @since itganhuo1.0
   * @return 返回注册视图
   */
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String refurlRegister() {
    return "user/register";
  }

  /**
   * 处理注册请求
   * <ol>
   * <li>只允许邮箱注册，会员填写邮箱地址和密码后系统以邮箱地址为账号并加密密码后存储到数据库</li>
   * <li>账号添加成功后此时处于锁定状态，系统发送一款邮件给这个邮箱做确认</li>
   * <li>得到javamail.properties里面的东东。注册，发送邮件到注册邮箱，邮箱模版中有跳转回来的东东----北京--C&B</li>
   * </ol>
   * 
   * @author 【新建】Java私塾在线学习社区（329232140）深圳-小兴504487927 2014年7月7日 下午11:56:04
   * @author 【修改】Java私塾在线学习社区（329232140）北京--C&B 2014年8月24日 下午9:56:04
   * @author 【修改】Java私塾在线学习社区（329232140）深圳-小兴504487927 2014年8月31日
   * @since itganhuo1.0
   * @param request
   * @return 返回到提示用户登录自己邮箱的提示页面
   * @throws ItGanHuoException
   */
  @Transactional
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  @ResponseBody
  public String register(User user, HttpServletRequest request) {
    String msg = "";
    /*if (user.getAccount().trim().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
      User tmp_user = this.userService.loadByAccount(user.getAccount());
      if (tmp_user == null || !StringUtil.hasText(tmp_user.getId())) {
        String algorithmName = "SHA-512";
        String salt1 = user.getAccount();
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1 + salt2,
            hashIterations);
        user.setPassword(hash.toBase64());
        user.setSalt(salt2);
        user.setIsLock(1);
        user.setPostDate(new Date());
        user.setType(1);
        this.userService.insert(user);

        
         * 新注册会员账号处于锁定状态，发送邮件认证信息后对账号解锁。 1、从数据库查询出指定邮件模块，将邮件内容相关变量赋值后传给mailSevice；
         * 2、该用什么模板来发信息是已知的，这里直接查询。
         
        Template mail_model = emailTemplateService.loadById("1");
        String tmp_str = mail_model.getContent();
        if (StringUtil.hasText(tmp_str)) {
          tmp_str = tmp_str.replaceFirst("#account#", user.getAccount());
          String code = emailTemplateService.generateAuthURL(user.getAccount());
          tmp_str = tmp_str.replaceFirst("#auth_url#", code);
        }
        mail_model.setContent(tmp_str);
        mailService.sendMail(user, mail_model);

        HttpUtil.setValue(ConstantPool.USER_TEMP_INFO, user);

        msg = "{\"msg\":\"" + ConfigPool.getString("msg.register.registerSuccessfully")
            + "\", \"status\": \"1\", \"account\": \"" + user.getAccount() + "\"}";
      } else {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.register.accountExists")
            + "\", \"status\": \"0\"}";
      }
    } else {
      msg = "{\"msg\":\"" + ConfigPool.getString("msg.register.accountFormatIsNotCorrect")
          + "\", \"status\": \"0\"}";
    }*/
    return msg;
  }

  /**
   * 通过shiro完成登录功能
   * 
   * @author 深圳-小兴(504487927) 2014年7月7日 下午11:54:37
   * @since itganhuo1.0
   * @param request
   * @return 返回状态码
   */
  @Transactional
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  @ResponseBody
  public String signin(User user, HttpServletRequest request) {
    String msg = "{\"msg\":\"" + user.getAccount() + "\", \"status\": \"1\"}";
    Subject current_user = SecurityUtils.getSubject();
    /*if (!current_user.isAuthenticated()) {
      UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
      token.setRememberMe(true);
      try {
        current_user.login(token);
        // 1、登录成功后将用户信息放到HTTP会话中
        User d_user = userService.loadByAccount(user.getAccount());
        current_user.getSession().setAttribute(ConstantPool.USER_SHIRO_SESSION_ID, d_user);
        // 2、更新当前登录用户最近登录时间和最近登录IP
        User tmp = new User();
        tmp.setAccount(user.getAccount());
        tmp.setLastLoginDate(new Date());
        tmp.setLastLoginIp(StringUtil.getNowHttpIp(request));
        userService.updateInfoByAccount(tmp);
      } catch (UnknownAccountException uae) {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.user.unknownAccount", "failure")
            + "\", \"status\": \"2\"}";
      } catch (IncorrectCredentialsException ice) {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.user.incorrectCredentials", "failure")
            + "\", \"status\": \"3\"}";
      } catch (LockedAccountException lae) {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.user.lockedAccount", "failure")
            + "\", \"status\": \"4\"}";
      } catch (EmailUnauthorizedException ae) {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.user.emailUnauthorized", "failure")
            + "\", \"status\": \"5\"}";
      } catch (AuthenticationException ae) {
        msg = "{\"msg\":\"" + ConfigPool.getString("msg.user.authentication", "failure")
            + "\", \"status\": \"5\"}";
      }
    }*/
    return msg;
  }

  /**
   * 进入用户中心页面
   * 
   * @author 深圳-小兴(504487927) 2014-8-31
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/{account}", method = RequestMethod.GET)
  public ModelAndView center(@PathVariable(value = "account") String account) {
    /*ModelAndView mav = new ModelAndView();
    User user_model = null;
    Subject current_user = SecurityUtils.getSubject();
    user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
    if (user_model == null || !StringUtil.hasText(user_model.getId())) {
      user_model = userService.loadByAccount(current_user.getPrincipal().toString());
      current_user.getSession().setAttribute(ConstantPool.USER_SHIRO_SESSION_ID, user_model);
    }
    mav.addObject("user_model", user_model);

    // 查询最近发布话题
    List<Article> articles = articleService.getArticleByUserId(user_model.getId());
    mav.addObject("articles", articles);

    mav.setViewName("user/center");
    return mav;*/
    return null;
  }

  /**
   * 进入修改信息页面 从session中获取到账户名，找到对应的用户，如果session中不存在账户信息或用户不存在则跳转到登陆界面 把查询到的该用户信息返回到页面
   * 
   * @param model
   * @param session
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/update", method = RequestMethod.GET)
  public String update(Model model, HttpSession session) {
    User user_model = null;
    Subject current_user = SecurityUtils.getSubject();
    user_model = userService.loadByAccount(current_user.getPrincipal().toString());
    model.addAttribute("user", user_model);
    return "user/update";

  }

  /**
   * 修改用户信息 修改用户的基本信息，成功返回主页，失败重新进入修改
   * 
   * @param user
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String refurlUpdate(User user) {
    if (this.userService.updateInfoByAccount(user) != 0) {
      return "redirect:/user/center";
    } else
      return "redirect:update";
  }

  /**
   * 根据账户得到用户信息，跳转到修改密码页面 从session中获取到账户名，找到对应的用户，如果session中不存在账户信息或用户不存在则跳转到登陆界面 返回用户信息
   * 
   * @param model
   * @param session
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
  public String updatePassword(Model model, HttpSession session) {
    /*User user_model = null;
    Subject current_user = SecurityUtils.getSubject();
    user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
    if (user_model == null || !StringUtil.hasText(user_model.getId())) {
      user_model = userService.loadByAccount(current_user.getPrincipal().toString());
    }
    model.addAttribute("user", user_model);
    return "user/updatePassword";*/
    return null;
  }

  /**
   * 
   * <p>
   * 检测用户密码与输入的原始密码是否匹配，如果不匹配返回1
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author Java私塾在线学习社区（329232140）天津-朱塞佩360449762014-10-6 上午11:15:48
   * @since itganhuo1.0
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
  @ResponseBody
  public String checkPassword(HttpServletRequest request, HttpServletResponse response) {
    String msg = "pass";
    String originalanpassword = request.getParameter("originalanpassword");
    String account = request.getParameter("account");
    if (account != null && !"".equals(account)) {
      User user_model = userService.loadByAccount(account);
      String algorithmName = "SHA-512";
      String salt1 = user_model.getAccount();
      String salt2 = user_model.getSalt();
      int hashIterations = 2;
      SimpleHash hash = new SimpleHash(algorithmName, originalanpassword, salt1 + salt2,
          hashIterations);
      if (!hash.toBase64().equals(user_model.getPassword())) {
        msg = "noPass";
      }
    }
    return msg;
  }

  /**
   * 根据用户修改密码更新数据库信息，根据密码修改模板为该用户发送修改后的密码 如果修改密码成功，则把用户输入的密码发送到其邮箱中，返回index页面
   * 
   * @param user
   * @return
   * @author 朱塞佩
   * @throws ItGanHuoExcpetion
   */
  @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
  public String updatePassword(User user) {
    if (this.userService.updatePasswordByAccount(user) != 0) {
      Template template = emailTemplateService.loadByName("密码修改通知");
      if (template != null) {
        String tmp_str = template.getContent();
        if (StringUtil.hasText(tmp_str)) {
          tmp_str = tmp_str.replaceFirst("#account#", user.getAccount());
        }
        template.setContent(tmp_str);
        mailService.sendMail(user, template);
        logger.info("用户成功修改密码");
        return "redirect:/user/center";
      } else
        throw new InternalException("对应模板不存在");
    } else
      throw new InternalException("修改密码操作异常，请与管理员联系");

  }

  /**
   * 修改头像功能，跳转到修改头像页面
   * 
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/upload", method = RequestMethod.GET)
  public String refurlUpload() {
    return "user/upload";
  }

  /**
   * 完成修改头像功能，把用户的头像存到项目下photo文件夹中
   * 
   * @param request
   * @return
   * @author 朱塞佩
   * @throws ItGanHuoException
   */
  @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
  @ResponseBody
  public String uploadImg(HttpServletRequest request) {
    /*String msg = "fail";
    User user_model = null;
    Subject current_user = SecurityUtils.getSubject();
    user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
    if (user_model == null || !StringUtil.hasText(user_model.getId())) {
      user_model = userService.loadByAccount(current_user.getPrincipal().toString());
    }
    String path = request.getSession().getServletContext().getRealPath("/") + "photo" + "/"
        + user_model.getAccount() + ".jpg";
    File file = new File(path);
    try {
      if (file.exists())
        file.delete();
      else
        file.createNewFile();
      FileUtils.copyInputStreamToFile(request.getInputStream(), file);
      msg = "success";
      if (logger.isDebugEnabled()) {
        logger.debug(user_model.getAccount() + "Path modified image=" + path);
      }
    } catch (IOException e) {
      throw new InternalException(logger, e);
    }
    if (logger.isDebugEnabled()) {
      logger.debug(msg + "," + user_model.getAccount());
    }
    return msg + "," + user_model.getAccount();*/
    return null;
  }

  /**
   * 进入会员分享页面（发布文章页面）
   * <ol>
   * <li>必须具备身份已认证权限</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-13
   * @since itganhuo1.0
   * @return
   */
  @RequiresAuthentication
  @RequestMapping(value = "/share", method = RequestMethod.GET)
  public ModelAndView refurlShare() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("user/create");
    return mav;
  }

  /**
   * 发布一篇文章（分享）
   * <ol>
   * <li>必须具备身份已认证权限</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-13
   * @since itganhuo1.0
   * @return
   */
  @RequiresAuthentication
  @Transactional
  @RequestMapping(value = "/share", method = RequestMethod.POST)
  public String create(Article article_model, @RequestParam String subject) {
    if (article_model == null || !StringUtil.hasText(article_model.getTitle())) {
      throw new InternalException(logger, "Article is a null value.");
    }

    if (StringUtil.hasText(article_model.getTitle())
        && StringUtil.hasText(article_model.getContent())) {
      Subject current_user = SecurityUtils.getSubject();
      User um = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);

      /*
       * 保存文章
       */
      //article_model.setUser_id(um.getId());
      String id = articleService.addArticle(article_model);

      /*
       * 根据用户输入的标签有选择性的保存，判断规则： 1、判断是否超过5个标签，如果大于5个标签则超出的部分不做处理；
       * 2、判断每个标签是否存在于数据库中，如果存在则取出其标签编号，否则新增这个标签并获取它的编号； 3、保存文章和标签之间的关联关系。
       */
      /*if (!StringUtil.hasText(subject)) {
        throw new InternalException(logger, "The article label can't be empty.");
      }
      String subjects[] = subject.split(",");
      if (subjects == null || subjects.length <= 0) {
        throw new InternalException(logger, "The article label format is not correct.");
      }
      int lng = (subjects.length > 5) ? 5 : subjects.length;
      for (int i = 0; i < lng; i++) {
        String tmp_str = subjects[i].trim().toLowerCase();
        String subject_id = null;
        Subject subject2 = new Subject();
        subject.setName(tmp_str);
        List<Subject> list = subjectService.getSubjectByCondition(subject);
        if (list.size() > 0) {
          subject_id = list.get(0).getId();
        } else {
          Subject subjectModel2 = new Subject();
          subject_id = UUID.randomUUID().toString();
          subjectModel2.setId(subject_id);
          subjectModel2.setName(tmp_str.toLowerCase());
          subjectModel2.setUser_id(um.getId());
          subjectModel2.setPost_date(new Date());
          subjectService.insertSubject(subjectModel2);
        }
        ArticleLabel asm = new ArticleLabel();
        asm.setId(UUID.randomUUID().toString());
        asm.setArticle_id(id);
        asm.setSubject_id(subject_id);
        asm.setUser_id(um.getId());
        asm.setPost_date(new Date());
        articleService.insertArticleSubject(asm);
      }*/
    }

    return "redirect:/user/center";
  }

  /**
   * 新增一条用户评论
   * 
   * @author 深圳-小兴(504487927) 2014-9-15
   * @since itganhuo1.0
   * @author 深圳-夕落 2014-11-19
   * @param article_model
   * @return
   */
  @RequiresAuthentication
  @RequestMapping(value = "/comment", method = RequestMethod.POST)
  public String comment(Comment comment_model, @RequestParam String article_user_id) {
    if (StringUtil.hasText(comment_model.getContent())) {
      Subject current_user = SecurityUtils.getSubject();
      User user_model = (User) current_user.getSession().getAttribute(
          ConstantPool.USER_SHIRO_SESSION_ID);
      /*if (user_model != null && StringUtil.hasText(user_model.getId())
          && !article_user_id.equals(user_model.getId())) {
        comment_model.setUser_id(user_model.getId());
        comment_model.setType(1);
        commentService.addComment(comment_model);
        return "redirect:/article/" + comment_model.getObject_id();
      }*/
    }
    return "redirect:/articles";
  }

  /**
   * 获取所有标签并以json表现形式呈现给客户端
   * 
   * @author 深圳-小兴(504487927) 2014-11-8
   * @since itganhuo1.0
   * @return 返回对象json表现形式
   */
  @RequestMapping(value = "/findLabel")
  @ResponseBody
  public List<AutoComplete> findLabel(@RequestParam String term) {
    if (!StringUtil.hasText(term)) {
      throw new InternalException(logger, "Query parameters are not allowed to empty.");
    }
    try {
      term = URLDecoder.decode(term, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new InternalException(logger, e);
    }
    /*Subject subject = new Subject();
    subject.setName(term);
    List<Subject> list = subjectService.getSubjectByCondition(subject);*/
//    return this.subject2Label(list);
    return null;
  }

  /**
   * 把标签对象转换成自动补全插件想要的数据模型
   * 
   * @param list
   * @return
   */
  private List<AutoComplete> subject2Label(List<Subject> list) {
    List<AutoComplete> autoCompletes = new ArrayList<AutoComplete>();
    if (list != null && list.size() > 0) {
      for (int i = 0; i < list.size(); i++) {
        Subject sm = list.get(i);
        AutoComplete lm = new AutoComplete();
       /* lm.setId(sm.getId());
        lm.setLabel(sm.getName());
        lm.setValue(sm.getName());*/
        autoCompletes.add(lm);
      }
    }
    return autoCompletes;
  }
}
