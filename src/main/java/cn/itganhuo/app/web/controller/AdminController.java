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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.entity.Dictionaries;
import cn.itganhuo.app.entity.Menu;
import cn.itganhuo.app.entity.Page;
import cn.itganhuo.app.entity.Permissions;
import cn.itganhuo.app.entity.Roles;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.DictionariesService;
import cn.itganhuo.app.service.MenuService;
import cn.itganhuo.app.service.PermissionsService;
import cn.itganhuo.app.service.RolesService;
import cn.itganhuo.app.service.UserService;

/**
 * 系统后台控制层（后台的所有访问都经过此控制器，记住不要和前台混合在一起使用。）
 * <ol>
 * <li>处理系统后台业务操作及视图返回，尽量避免后台的请求路径与其它控制层交叉。</li>
 * </ol>
 * 
 * @author 深圳-小兴(504487927) 2014年7月30日 下午10:30:43
 * @since itganhuo1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

  private MenuService menuService;
  private DictionariesService dictionariesService;
  private UserService userService;
  private PermissionsService permissionsService;
  private RolesService rolesService;

  @Autowired
  public void setAdminService(MenuService menuService) {
    this.menuService = menuService;
  }

  @Autowired
  public void setDictionariesService(DictionariesService dictionariesService) {
    this.dictionariesService = dictionariesService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  public void setPermissionsService(PermissionsService permissionsService) {
    this.permissionsService = permissionsService;
  }

  @Autowired
  public void setRolesService(RolesService rolesService) {
    this.rolesService = rolesService;
  }

  /**
   * 进入后台管理主页面
   * 
   * @author 深圳-小兴(504487927) 2014年8月2日 下午4:58:14
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public String refurlAdminIndex() {
    return "admin/index";
  }

  /**
   * 进入后台管理首页之中心页面
   * 
   * @author 深圳-小兴(504487927) 2014年8月2日 下午4:58:36
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/center", method = RequestMethod.GET)
  public String refurlAdminCenter() {
    return "admin/center";
  }

  /**
   * 进入后台通用的左边页面
   * 
   * @author 深圳-小兴(504487927) 2014年8月2日 下午4:59:11
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/west", method = RequestMethod.GET)
  public String refurlAdminWest() {
    return "admin/west";
  }

  /**
   * 专门用于获取系统后台功能导航菜单树结构数据，数据结构以json形式表示便于easyui渲染。
   * 
   * @author 深圳-小兴(504487927) 2014年7月30日 下午10:47:40
   * @since itganhuo1.0
   * @return json格式的表示后台菜单的tree
   */
  @RequestMapping(value = "/getMenuTreeJson")
  @ResponseBody
  public List<Menu> getMenuTreeJson() {
    return menuService.getMenuTree();
  }

  /**
   * 菜单列表页面
   * 
   * @author 深圳-小兴(504487927) 2014-8-10
   * @since itganhuo1.0
   * @return
   */
  @RequestMapping(value = "/menu_list", method = RequestMethod.GET)
  public String refurlMenuList() {
    return "admin/menu_list";
  }

  /**
   * 菜单列表
   * 
   * @author 深圳-小兴(504487927) 2014-8-10
   * @since itganhuo1.0
   * @return 后台管理菜单列表
   */
  @RequestMapping(value = "/getMenuListJson")
  @ResponseBody
  public Map<String, List<Object>> getMenuListJson(Menu menu, Page page) {
    Map<Object, Object> condition = new HashMap<Object, Object>();
    condition.put("pageModel", page);
    condition.put("menuModel", menu);
    return menuService.getMenuList(condition);
  }

  /**
   * 添加菜单
   * 
   * @author 深圳-小兴(504487927) 2014-8-21
   * @since itganhuo1.0
   * @param menu
   * @return
   */
  @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
  @ResponseBody
  public String addMenu(Menu menu) {
    if (menuService.addMenu(menu)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  /**
   * 删除菜单
   * 
   * @author 深圳-小兴(504487927) 2014-8-21
   * @since itganhuo1.0
   * @param menu
   * @return
   */
  @RequestMapping(value = "/delMenu", method = RequestMethod.POST)
  @ResponseBody
  public String delMenu(Menu menu) {
    /*String id = menu.getId();
    if (menuService.delMenu(id)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }*/
    return null;
  }

  /**
   * 编辑菜单
   * 
   * @author 深圳-小兴(504487927) 2014-8-24
   * @since itganhuo1.0
   * @param menu
   * @return
   */
  @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
  @ResponseBody
  public String updateMenu(Menu menu) {
    if (menuService.updateMenu(menu) > 0) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.updateSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.updateFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/getDictionariesListJson")
  @ResponseBody
  public Map<String, List<Object>> getDictionariesListJson(Dictionaries dictionaries, Page page) {
    Map<Object, Object> condition = new HashMap<Object, Object>();
    condition.put("pageModel", page);
    condition.put("dictionariesModel", dictionaries);
    return dictionariesService.getDictionariesList(condition);
  }

  @RequestMapping(value = "/addDictionaries", method = RequestMethod.POST)
  @ResponseBody
  public String addDictionaries(Dictionaries dictionaries) {
    if (dictionariesService.addDictionaries(dictionaries)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/delDictionaries", method = RequestMethod.POST)
  @ResponseBody
  public String delDictionaries(Dictionaries dictionaries) {
    /*String id = dictionaries.getId();
    if (dictionariesService.delDictionaries(id)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }*/
    return null;
  }

  @RequestMapping(value = "/updateDictionaries", method = RequestMethod.POST)
  @ResponseBody
  public String updateDictionaries(Dictionaries dictionaries) {
    if (dictionariesService.updateInfoDictionaries(dictionaries)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/dictionaries_list", method = RequestMethod.GET)
  public String refurlDictionariesList() {
    return "admin/dictionaries_list";
  }

  @RequestMapping(value = "user_list", method = RequestMethod.GET)
  public String refurlUserList() {
    return "admin/user_list";
  }

  @RequestMapping(value = "/getUserListJson")
  @ResponseBody
  public Map<String, List<Object>> getUserListJson(User user, Page page) {
    Map<Object, Object> condition = new HashMap<Object, Object>();
    condition.put("pageModel", page);
    condition.put("userModel", user);
    return userService.getUserList(condition);
  }

  @RequestMapping(value = "/getPermissionsListJson")
  @ResponseBody
  public Map<String, List<Object>> getPermissionsListJson(Permissions permissions, Page page) {
    Map<Object, Object> condition = new HashMap<Object, Object>();
    condition.put("pageModel", page);
    condition.put("permissionsModel", permissions);
    return permissionsService.getPermissionsList(condition);
  }

  @RequestMapping(value = "/addPermisstions", method = RequestMethod.POST)
  @ResponseBody
  public String addPermissionss(Permissions permissions) {
    if (permissionsService.addpermissions(permissions)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/delPermissionss", method = RequestMethod.POST)
  @ResponseBody
  public String delPermissionss(Permissions permissions) {
    String id = permissions.getId();
    if (permissionsService.delPermissions(id)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/updatePermissions", method = RequestMethod.POST)
  @ResponseBody
  public String updatePermissionss(Permissions permissions) {
    if (permissionsService.updateInfoPermissions(permissions)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/permissions_list", method = RequestMethod.GET)
  public String refurlPermisstionsList() {
    return "admin/permissions_list";
  }

  @RequestMapping(value = "/getRolesListJson")
  @ResponseBody
  public Map<String, List<Object>> getRolesListJson(Roles roles, Page page) {
    Map<Object, Object> condition = new HashMap<Object, Object>();
    condition.put("pageModel", page);
    condition.put("rolesModel", roles);
    return rolesService.getRolesList(condition);
  }

  @RequestMapping(value = "/addRoles", method = RequestMethod.POST)
  @ResponseBody
  public String addRoles(Roles roles) {
    if (rolesService.addRoless(roles)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.addFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/delRoles", method = RequestMethod.POST)
  @ResponseBody
  public String delRoles(Roles roles) {
    String id = roles.getId();
    if (rolesService.delRoles(id)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/updateRoles", method = RequestMethod.POST)
  @ResponseBody
  public String updateRoles(Roles roles) {
    if (rolesService.updateInfoRoles(roles)) {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delSuccess", "success")
          + "\", \"status\": \"1\"}";
    } else {
      return "{\"msg\":\"" + ConfigPool.getString("msg.delFailure", "failure")
          + "\", \"status\": \"0\"}";
    }
  }

  @RequestMapping(value = "/roles_list", method = RequestMethod.GET)
  public String refurlRolesList() {
    return "admin/roles_list";
  }
}
