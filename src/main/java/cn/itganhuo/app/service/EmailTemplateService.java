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
package cn.itganhuo.app.service;

import java.util.List;

import cn.itganhuo.app.entity.Template;

public interface EmailTemplateService {

  /**
   * 插入一条模板
   * 
   * @author 朱塞佩
   * @since itganhuo1.0
   * @param template
   *          模板
   * @return
   */
  public int insert(Template template);

  /**
   * 根据模板名称返回相应模板
   * 
   * @author 朱塞佩
   * @since itganhuo1.0
   * @param name
   *          模板名称
   * @return
   */
  public Template loadByName(String name);

  /**
   * 根据主键查询模板信息
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param id
   *          主键
   * @return
   */
  public Template loadById(String id);

  /**
   * @return返回emailtemplate列表
   * @author 朱塞佩
   */
  public List<Template> loadAll();

  /**
   * 生成邮箱认证加密字符串
   * <ol>
   * <li>生成规则：域名/common/auth_email/用户ID/加密串</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param account
   *          账号
   * @return
   */
  public String generateAuthURL(String account);
}
