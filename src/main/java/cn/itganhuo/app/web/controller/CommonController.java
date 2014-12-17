/*
 * Solemnly declare(2014-11-11):
 * 1 This project is a collective creation of its copyrighted Java private school online learning community (QQ group number 329 232 140) of all, is prohibited without the author's permission for commercial profit without permission;
 * 2 See the list of IT ganhuo sharing network http://www.itganhuo.cn/teams;
 * 3, the author does not guarantee the quality of the project and its stability, reliability, and security does not assume any responsibility if you get the source code for this project in some way, all the consequences of the subsequent occurrence of ego nothing to do with this group and author;
 * 4 mx.tian@qq.com have any questions, please contact us.
 *
 * 郑重声明(2014-11-11)：
 * 1、本项目属集体创作其版权归Java私塾在线学习社区（QQ群号329232140）作者所有，禁止未经作者授权擅自用于商业盈利；
 * 2、作者名单详见IT干货技术分享网http://www.itganhuo.cn/teams；
 * 3、作者不保证项目质量并对其稳定性、可靠性和安全性不承担任何责任，如果你通过某些途径获取本项目源代码，其后发生的一切后果自负与本群及作者无关；
 * 4、有任何问题请与我们联系mx.tian@qq.com。
 */
package cn.itganhuo.app.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.InternalException;
import cn.itganhuo.app.service.UserService;

/**
 * 公共访问路径控制类
 * 
 * @author 深圳-小兴(504487927) 2014年7月20日 下午4:34:38
 * @since itganhuo1.0
 */
@Controller
public class CommonController {

  private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * <p>
   * 返回头部公共页面
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月2日 下午2:48:43
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/header", method = RequestMethod.GET)
  public String refurlHeader() {
    return "common/header";
  }

  /**
   * <p>
   * 返回底部公共页面
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年8月2日 下午2:48:50
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/footer", method = RequestMethod.GET)
  public String refurlFooter() {
    return "common/footer";
  }

  /**
   * <p>
   * 用户登出系统
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-8-31
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout() {
    Subject current_user = SecurityUtils.getSubject();
    current_user.logout();
    return "redirect:/articles";
  }

  /**
   * <p>
   * 未授权操作跳转提示页面
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-1
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/unauthorized")
  public String refurlUnauthorized() {
    return "common/unauthorized";
  }

  /**
   * <p>
   * 进入开发团队介绍页面
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-1
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/teams", method = RequestMethod.GET)
  public String refurlTeam() {
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
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/auth_email/{id}/{code}", method = RequestMethod.GET)
  public ModelAndView authEmail(@PathVariable(value = "id") String id,
      @PathVariable(value = "code") String code) {
    ModelAndView mav = new ModelAndView();
    String msg = "";
    /*if (StringUtil.hasText(id) && StringUtil.hasText(code)) {
      User user_model = userService.loadById(id);
      // 判断系统中是否存在这个账号，且账号没有被邮件认证过。
      if (user_model != null && StringUtil.hasText(user_model.getId())) {
        if (1 != user_model.getValidate_email()) {
          Date validate_date;
          try {
            validate_date = new SimpleDateFormat("").parse(user_model.getValidate_date());
            Date now_date = DateUtil.getAfterOrBeforDay(new Integer(ConfigPool
                .getString2("emailLinkValidCertification")));
            // 比较当前时间是否超过有效期
            if (now_date.after(validate_date)) {
              // 比较验证码是否正确
              if (code.equals(user_model.getCode())) {
                // 修改用户状态
                User um = new User();
                um.setAccount(user_model.getAccount());
                um.setValidate_email(1);
                um.setIsLock(0);
                userService.updateInfoByAccount(um);
                String str = ConfigPool.getString2("msg.email.emailAuthSuccessful");
                msg = str.replaceFirst("#account#", user_model.getAccount());
              } else {
                msg = ConfigPool.getString("msg.email.certificationSignatureAddressErrors");
              }
            } else {
              msg = ConfigPool.getString("msg.email.linkSddressFailure");
            }
          } catch (ParseException e) {
            throw new InternalException(logger,
                "Date conversion error, the date may be the source character is not legitimate.", e);
          }
        } else {
          msg = ConfigPool.getString("msg.email.emailHasBeenCertified");
        }
      } else {
        msg = ConfigPool.getString("msg.email.unknownAccount");
      }
    } else {
      msg = ConfigPool.getString("msg.email.requestAddressWrong");
    }
    mav.setViewName("common/email_auth_result");
    mav.addObject("msg", msg);
    return mav;*/
    return null;
  }

  /**
   * 邮件认证跳转提示页面
   * 
   * @author 深圳-小兴(504487927) 2014-9-10
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/emailskip/{account}")
  public ModelAndView refurlEmailskip(@PathVariable String account) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("user/emailskip");
    return mav;
  }

  /**
   * 进入开发日志页面
   * 
   * @author 深圳-小兴(504487927) 2014-9-13
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/devblog", method = RequestMethod.GET)
  public String refurlDevblog() {
    return "devblog";
  }

  /**
   * 进入关于我们页面
   * 
   * @author 深圳-小兴(504487927) 2014-9-13
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String refurlAbout() {
    return "about";
  }

  /**
   * 进入邀请朋友页面
   * 
   * @author 深圳-小兴(504487927) 2014-9-13
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/invitation", method = RequestMethod.GET)
  public String invated() {
    return "invitation";
  }
}
