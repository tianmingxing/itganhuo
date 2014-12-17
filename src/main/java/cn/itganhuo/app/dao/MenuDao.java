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

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Menu;

/**
 * 后台菜单表数据层接口
 * 
 * @author 深圳-小兴(504487927)
 * @since 0.0.1-SNAPSHOT
 * @version 2014-07-30
 */
@Repository
public interface MenuDao {

  /**
   * <p>
   * 根据主键查询一条对应的值
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014-8-10
   * @since itganhuo1.0
   * @param id
   *          主键
   * @return 返回一条菜单
   */
  public List<Menu> findMenuById(String id);

  /**
   * <p>
   * 根据外键查询对应的菜单集合
   * </p>
   * <ol>
   * <li>注意：最高父级别的外键默认是0</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-8-10
   * @since itganhuo1.0
   * @param pid
   *          外键（本表的主键）
   * @return 返回一个菜单集合
   */
  public List<Menu> findMenuByPid(String pid);

  /**
   * <p>
   * 到菜单表中分布按条件查询列表数据
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-8-10
   * @since itganhuo1.0
   * @return
   */
  public List<Object> getMenuList(Map<Object, Object> condition);

  /**
   * <p>
   * 根据条件统计记录数（不分页的情况下）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014-8-11
   * @since itganhuo1.0
   * @param condition
   * @return
   */
  public int countMenuList(Map<Object, Object> condition);

  /**
   * <p>
   * 新增一条记录到菜单表（t_menu）
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014-8-13
   * @since itganhuo1.0
   * @param menu
   * @return
   */
  public boolean addMenu(Menu menu);

  /**
   * <p>
   * 根据主键到菜单表（t_menu）删除一条记录
   * </p>
   * 
   * @author 深圳-小兴(504487927) 2014-8-13
   * @since itganhuo1.0
   * @param menuModel
   * @return
   */
  public int delMenu(String id);

  /**
   * <p>
   * 修改一条菜单
   * </p>
   * <ol>
   * <li></li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-8-24
   * @since itganhuo1.0
   * @param menu
   * @return
   */
  public int updateMenu(Menu menu);
}
