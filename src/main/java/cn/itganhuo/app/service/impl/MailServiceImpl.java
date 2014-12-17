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
package cn.itganhuo.app.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.PropertiesUtil;
import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.MailService;

@Service
public class MailServiceImpl implements MailService {

  JavaMailSender mailSender;

  TaskExecutor executor;

  @Autowired
  public void setMailSender(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Autowired
  public void setTs(TaskExecutor executor) {
    this.executor = executor;
  }

  /**
   * 将用户修改的原始密码发送到其邮箱中,如果其邮箱没有修改，那么则默认发送到注册的帐户的邮箱中 发件人的名称和邮箱是从javamail.properties中获取得到的
   * email:要发送的邮件地址 template:邮件模板
   */
  public void sendMail(final User user, final Template template) {

    executor.execute(new Runnable() {

      public void run() {

        try {
          MimeMessage msg = mailSender.createMimeMessage();

          MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");

          String myName = PropertiesUtil.getInstance().load("javamail").getProperty("myname");
          String myEmail = PropertiesUtil.getInstance().load("javamail").getProperty("myemail");
          helper.setFrom(MimeUtility.encodeText(myName) + "<" + myEmail + ">");
          if (user.getEmail() == null || "".equals(user.getEmail())) {
            helper.setTo(user.getAccount());
          } else {
            helper.setTo(user.getEmail());
          }
          helper.setSubject(template.getName());
          helper.setText(template.getContent(), true);

          mailSender.send(msg);
        } catch (MessagingException e) {
          e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      }
    });
  }

}
