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
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;

import cn.itganhuo.app.entity.User;

/**
 * <p>
 * 用户业务处理接口
 * </p>
 * 
 * @author [Java私塾在线学习社区329232140] 深圳-小兴504487927 2014年6月22日 下午4:21:34
 * @version 2014年7月7日 下午11:41:59
 */
public interface UserService {

  /**
   * <p>
   * 根据登录账号查询对应的唯一一条记录
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014年7月7日 下午11:41:59
   * @since itganhuo1.0
   * @param account
   *          账号
   * @return 会员信息
   */
  public User login(String account) throws AuthenticationException;

  /**
   * <p>
   * 会员注册
   * </p>
   * <ol>
   * <li>收集会员账号和密码保存到数据库</li>
   * </ol>
   * 
   * @author [Java私塾在线学习社区329232140] 深圳-小兴504487927@qq.com
   * @version 2014年6月22日 下午9:34:01
   * @param user
   * @return
   */
  public int insert(User user);

  /**
   * 根据账户查找用户信息
   * 
   * @param account
   * @return
   * @author 朱塞佩
   */
  public User loadByAccount(String account);

  /**
   * 根据主键查询一条用户信息
   * 
   * @author 深圳-小兴(504487927) 2014-9-8
   * @since itganhuo1.0
   * @param id
   *          主键
   * @return
   */
  public User loadById(String id);

  /**
   * 修改用户信息
   * 
   * @param user
   * @return
   * @author 朱塞佩
   */
  public int updateInfoByAccount(User user);

  /**
   * 修改用户密码
   * 
   * @param user
   * @return
   * @author 朱塞佩
   */
  public int updatePasswordByAccount(User user);

  /**
   * 修改用户头像
   * 
   * @param user
   * @return
   * @author 朱塞佩
   */
  public int updateImg(String account, String img);

  public Map<String, List<Object>> getUserList(Map<Object, Object> condition);

}
