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
package cn.itganhuo.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.User;

/**
 * 用户表数据层接口<br>
 * 
 * 本接口的实现有两种方式：<br>
 * <ol>
 * <li>通过*Mapper.xml文件来实现本接口中的方法；</li>
 * <li>直接在本接口中通过注解的方式直接写SQL语句；</li>
 * <li>本类定义的接口要和*Mapper.xml里一一对应，如果接口中有定义而xml中没有则会报错。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-07-07
 */
@Repository
public interface UserDao {

  /**
   * 根据主键删除对应的一条数据
   * @author 深圳-小兴(504487927)
   * @version 2014-07-07
   * @param id 主键
   * @return 返回删除的数据行数
   */
  @Delete("DELETE FROM T_USER WHERE ID=#{id}")
  public int deleteById(String id);
  
  /**
   * 根据主键修改对应一名用户的密码字段
   * @author 深圳-小兴(504487927)
   * @version 2014-07-07
   * @param record 用户
   * @return 返回修改的数据行数
   */
  @Update("UPDATE T_USER SET PASSWORD=#{password} WHERE ID=#{id}")
  public int updateById(User record);

  /**
   * 添加一条用户信息
   * @author 深圳-小兴(504487927)
   * @version 2014-07-07
   * @param user 用户
   * @return 如果插入成功则返回1，否则返回0
   */
  public int insert(User user);

  /**
   * 根据用户主键查询对应的唯一一条数据
   * 
   * @author 深圳-小兴(504487927) 2014年7月7日 下午11:49:53
   * @since itganhuo1.0
   * @param id
   *          主键
   * @return User
   */
  public User loadById(String id);

  public User loadByAccount(String account);

  /**
   * 根据帐户修改用户信息，包括用户名，性别，email，qq， 电话号码
   * 
   * @param record
   *          用户
   * @author 朱塞佩
   * @since itganhuo1.0
   * @return
   */
  public int updateInfoByAccount(User record);

  /**
   * 根据帐户修改密码
   * 
   * @param record
   *          用户
   * @author 朱塞佩
   * @since itganhuo1.0
   * @return
   */
  public int updatePasswordByAccount(User record);

  /**
   * 根据账户修改头像
   * 
   * @param record
   * @return
   * @author 朱塞佩
   */
  public int updateImg(User record);

  public List<Object> getUserList(Map<Object, Object> condition);

  public int countUserList(Map<Object, Object> condition);

}