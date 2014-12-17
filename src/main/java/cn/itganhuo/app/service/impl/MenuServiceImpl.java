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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.MenuDao;
import cn.itganhuo.app.entity.Menu;
import cn.itganhuo.app.service.MenuService;

/**
 * <p>
 * 系统后台：服务层实现类
 * </p>
 * 
 * @author 深圳-小兴(504487927) 2014年7月30日 下午10:35:13
 * @since itganhuo1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

  private MenuDao menuDao;

  @Autowired
  public void setMenuMapper(MenuDao menuDao) {
    this.menuDao = menuDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.AdminService#getMenuTreeJson()
   */
  public List<Menu> getMenuTree() {
    /*List<Menu> list1 = menuDao.findMenuByPid("0");
    if (list1 != null && list1.size() > 0) {
      for (int i = 0; i < list1.size(); i++) {
        String id = list1.get(i).getId();
        if (StringUtil.hasText(id)) {
          List<Menu> list2 = menuDao.findMenuByPid(id);
          if (list2 != null && list2.size() > 0) {
            list1.get(i).setChildren(list2);
            for (int j = 0; j < list2.size(); j++) {
              String id2 = list2.get(j).getId();
              if (StringUtil.hasText(id2)) {
                List<Menu> list3 = menuDao.findMenuByPid(id2);
                if (list3 != null && list3.size() > 0) {
                  list2.get(j).setChildren(list3);
                }
              }
            }
          }
        }
      }
    }
    return list1;*/
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.AdminService#getMenuListJson()
   */
  public Map<String, List<Object>> getMenuList(Map<Object, Object> condition) {
    List<Object> list = menuDao.getMenuList(condition);
    List<Object> total = new ArrayList<Object>();
    if (list != null && list.size() > 0) {
      total.add(menuDao.countMenuList(condition));
    }
    Map<String, List<Object>> map = new HashMap<String, List<Object>>();
    map.put("total", total);
    map.put("rows", list);
    return map;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.AdminService#addMenu(cn.itganhuo.model.MenuModel)
   */
  public boolean addMenu(Menu menu) {
    String id = UUID.randomUUID().toString();
    //menu.setId(id);
    return menuDao.addMenu(menu);
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.AdminService#delMenu(java.lang.String)
   */
  public boolean delMenu(String id) {
    if (menuDao.delMenu(id) > 0) {
      return true;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.MenuService#updateMenu(cn.itganhuo.model.MenuModel)
   */
  public int updateMenu(Menu menu) {
    if (menuDao.updateMenu(menu) > 0) {
      return 1;
    }
    return 0;
  }

}
