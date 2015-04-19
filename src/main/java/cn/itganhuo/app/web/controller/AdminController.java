/*
 * Copyright 2014-2024 the https://github.com/xiaoxing598/itganhuo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * This project consists of JAVA private school online learning community group Friends co-creator [QQ group 329232140].
 * 本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140];
 * See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams];
 * The author does not guarantee the quality of the project and its stability, reliability, and security does not bear any responsibility.
 * 作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任.
 */
package cn.itganhuo.app.web.controller;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.entity.*;
import cn.itganhuo.app.service.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>系统后台控制层：后台的所有访问都经过此控制器</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>
 * <ol>
 * <li>处理系统后台业务操作及视图返回。</li>
 * </ol>
 * </dd>
 * <dt>使用规范</dt>
 * <dd>千万不要和前台请求路径混合在一起使用，因为要考虑到后续做前后台分别部署。</dd>
 * </dl>
 *
 * @author 深圳-小兴，天津-夕落
 * @version 0.0.1-SNAPSHOT
 */
@Controller
@RequestMapping("/admin")
@RequiresAuthentication
public class AdminController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private DictionariesService dictionariesService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionsService permissionsService;
    @Autowired
    private RolesService rolesService;

    /**
     * <h2>进入后台管理主页面</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>后台管理都需要做权限控制，非授权用户禁止访问。</dd>
     * </dl>
     *
     * @return 转发到后台首页页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String refurlAdminIndex() {
        return "admin/index";
    }

    /**
     * <h2>进入后台管理首页之中心页面</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return 转发到后台首页之中心页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/center", method = RequestMethod.GET)
    public String refurlAdminCenter() {
        return "admin/center";
    }

    /**
     * <h2>进入后台通用的左边页面</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return 转发到后台通用左边页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/west", method = RequestMethod.GET)
    public String refurlAdminWest() {
        return "admin/west";
    }

    /**
     * <h2>获取菜单</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>专门用于获取系统后台功能导航菜单树结构数据，数据结构以json形式表示便于easy ui渲染。</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return 返回json格式菜单数据
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/getMenuTreeJson")
    @ResponseBody
    public List<Menu> getMenuTreeJson() {
        return menuService.getMenuTree(0);
    }

    /**
     * <h2>菜单列表管理页面</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return 转发到菜单管理列表页面
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/menu_list", method = RequestMethod.GET)
    public String refurlMenuList() {
        return "admin/menu_list";
    }

    /**
     * <h2>获取菜单列表页面数据</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param menu 菜单模型
     * @param page 分页模型
     * @return 返回菜单列表数据
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/getMenuListJson")
    @ResponseBody
    public Map<String, List<Object>> getMenuListJson(Menu menu, Paging page) {
        Map<Object, Object> condition = new HashMap<Object, Object>();
        condition.put("pageModel", page);
        condition.put("menuModel", menu);
        return menuService.getMenuList(condition);
    }

    /**
     * <h2>添加一个菜单</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param menu 菜单模型
     * @return 返回处理状态信息
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg addMenu(Menu menu) {
        RespMsg respMsg = new RespMsg();
        if (menuService.addMenu(menu) <= 0) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.AddFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>删除菜单</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param menu
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/delMenu", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg delMenu(Menu menu) {
        RespMsg respMsg = new RespMsg();
        if (!menuService.delMenu(menu.getId())) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.DelFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>编辑菜单</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param menu
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 深圳-小兴
     */
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg updateMenu(Menu menu) {
        RespMsg respMsg = new RespMsg();
        if (!menuService.updateMenuById(menu)) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.UpdateFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param dictionaries
     * @param page
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/getDictionariesListJson")
    @ResponseBody
    public Map<String, List<Object>> getDictionariesListJson(Dictionaries dictionaries, Paging page) {
        Map<Object, Object> condition = new HashMap<Object, Object>();
        condition.put("pageModel", page);
        condition.put("dictionariesModel", dictionaries);
        return dictionariesService.getDictionariesList(condition);
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param dictionaries
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/addDictionaries", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg addDictionaries(Dictionaries dictionaries) {
        RespMsg respMsg = new RespMsg();
        if (dictionariesService.addDictionaries(dictionaries) <= 0) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.AddFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param dictionaries
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/delDictionaries", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg delDictionaries(Dictionaries dictionaries) {
        RespMsg respMsg = new RespMsg();
        if (!dictionariesService.delDictionaries(dictionaries.getId())) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.DelFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param dictionaries
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/updateDictionaries", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg updateDictionaries(Dictionaries dictionaries) {
        RespMsg respMsg = new RespMsg();
        if (!dictionariesService.updateInfoDictionaries(dictionaries)) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.UpdateFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/dictionaries_list", method = RequestMethod.GET)
    public String refurlDictionariesList() {
        return "admin/dictionaries_list";
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "user_list", method = RequestMethod.GET)
    public String refurlUserList() {
        return "admin/user_list";
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param user
     * @param page
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/getUserListJson")
    @ResponseBody
    public Map<String, List<Object>> getUserListJson(User user, Paging page) {
        Map<Object, Object> condition = new HashMap<Object, Object>();
        condition.put("pageModel", page);
        condition.put("userModel", user);
        return userService.getUserList(condition);
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param permissions
     * @param page
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/getPermissionsListJson")
    @ResponseBody
    public Map<String, List<Object>> getPermissionsListJson(Permissions permissions, Paging page) {
        Map<Object, Object> condition = new HashMap<Object, Object>();
        condition.put("pageModel", page);
        condition.put("permissionsModel", permissions);
        return permissionsService.getPermissionsList(condition);
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param permissions
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/addPermisstions", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg addPermissionss(Permissions permissions) {
        RespMsg respMsg = new RespMsg();
        if (permissionsService.addpermissions(permissions) <= 0) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.AddFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param permissions
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/delPermissionss", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg delPermissionss(Permissions permissions) {
        RespMsg respMsg = new RespMsg();
        if (permissionsService.delPermissions(permissions.getId())) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.DelFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param permissions
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/updatePermissions", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg updatePermissionss(Permissions permissions) {
        RespMsg respMsg = new RespMsg();
        if (!permissionsService.updateInfoPermissions(permissions)) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.UpdateFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/permissions_list", method = RequestMethod.GET)
    public String refurlPermisstionsList() {
        return "admin/permissions_list";
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param roles
     * @param page
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/getRolesListJson")
    @ResponseBody
    public Map<String, List<Object>> getRolesListJson(Roles roles, Paging page) {
        Map<Object, Object> condition = new HashMap<Object, Object>();
        condition.put("pageModel", page);
        condition.put("rolesModel", roles);
        return rolesService.getRolesList(condition);
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param roles
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/addRoles", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg addRoles(Roles roles) {
        RespMsg respMsg = new RespMsg();
        if (rolesService.addRoless(roles) <= 0) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.AddFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param roles
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/delRoles", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg delRoles(Roles roles) {
        RespMsg respMsg = new RespMsg();
        if (rolesService.delRoles(roles.getId())) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.DelFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @param roles
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/updateRoles", method = RequestMethod.POST)
    @ResponseBody
    public RespMsg updateRoles(Roles roles) {
        RespMsg respMsg = new RespMsg();
        if (!rolesService.updateInfoRoles(roles)) {
            respMsg.setMessage(ConfigPool.getString("respMsg.common.UpdateFailure"));
        }
        return respMsg;
    }

    /**
     * <h2>[功能用途简述]</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>无</dd>
     * <dt>使用规范</dt>
     * <dd>无</dd>
     * </dl>
     *
     * @return
     * @version 0.0.1-SNAPSHOT
     * @author 天津-小朱
     */
    @RequestMapping(value = "/roles_list", method = RequestMethod.GET)
    public String refurlRolesList() {
        return "admin/roles_list";
    }
}
