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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.service.EmailTemplateService;

@Controller
@RequestMapping("/emailTemplate")
public class EmailTemplateController {

  private static final Logger logger = LoggerFactory.getLogger(EmailTemplateController.class);

  private EmailTemplateService emailTemplateService;

  @Autowired
  public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
    this.emailTemplateService = emailTemplateService;
  }

  /**
   * 返回email模板集合
   * 
   * @param model
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String listEmailTemplate(Model model) {
    model.addAttribute("emailTemplateList", this.emailTemplateService.loadAll());
    return "emailTemplate/list";
  }

  /**
   * 准备插入email模板的数据，跳转到插入页面
   * 
   * @param model
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/insert", method = RequestMethod.GET)
  public String insertEmailTemplate(Model model) {
    Template template = new Template();
    model.addAttribute("emailTemplate", template);
    return "emailTemplate/insert";
  }

  /**
   * 插入一条email模板信息 成功跳转到模板信息集合页面并进行日志记录 失败重新进入插入业务
   * 
   * @param template
   * @return
   * @author 朱塞佩
   */
  @RequestMapping(value = "/insert", method = RequestMethod.POST)
  public String insertEmailTemplate(Template template) {
    if (emailTemplateService.insert(template) != 0) {
      if (logger.isInfoEnabled()) {
        logger.info("插入email信息" + template.getName() + "成功");
      }
      return "redirect:list";
    } else
      return "redirect:list";
  }

}
