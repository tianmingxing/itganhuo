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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.UserDao;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.exception.EmailUnauthorizedException;
import cn.itganhuo.app.service.UserService;

/**
 * 用户业务处理实现类
 * 
 * @author 深圳-小兴(504487927) 2014年7月7日 下午11:45:03
 * @since itganhuo1.0
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao = null;

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.UserService#login(cn.itganhuo.model.User)
   */
  public User login(String r_account) throws AuthenticationException {
    /*// 1、判断账号是否存在并且是否锁定状态
    User d_user = userDao.loadByAccount(r_account);
    if (d_user == null) {
      throw new UnknownAccountException();
    }
    if (0 != d_user.getIsLock()) {
      throw new LockedAccountException();
    }
    if (1 != d_user.getValidate_email()) {
      throw new EmailUnauthorizedException();
    }
    return d_user;*/
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.UserService#insert(cn.itganhuo.model.User)
   */
  public int insert(User user) {
    //user.setId(UUID.randomUUID().toString());
    return this.userDao.insert(user);
  }

  /**
   * 根据账户返回用户信息
   * 
   * @param account
   * @return user
   * @author 朱塞佩
   */
  public User loadByAccount(String account) {
    if (StringUtil.hasText(account))
      return userDao.loadByAccount(account);
    else
      return null;
  }

  /**
   * 根据页面更新用户信息
   * 
   * @param user
   * @return 是否更新成功
   * @author 朱塞佩
   */
  public int updateInfoByAccount(User user) {
    return userDao.updateInfoByAccount(user);
  }

  /**
   * 修改用户密码
   * 
   * @param user
   * @return
   */
  public int updatePasswordByAccount(User user) {
    String algorithmName = "SHA-512";
    String salt1 = user.getAccount();
    String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
    int hashIterations = 2;
    SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(), salt1 + salt2,
        hashIterations);
    user.setPassword(hash.toBase64());
    user.setSalt(salt2);
    return this.userDao.updatePasswordByAccount(user);
  }

  /**
   * 修改用户头像 首先根据账户信息的到该用户的过去的头像，将头像修改为新头像的同时删除头像图片
   */
  public int updateImg(String account, String img) {
    /*User user = this.loadByAccount(account);
    String oldImg = user.getImg();
    user.setImg(img);
    if (userDao.updateImg(user) != 0) {
      File file = new File(oldImg);
      file.delete();
      return 1;
    }
    return 0;*/
    return 0;
  }

  public Map<String, List<Object>> getUserList(Map<Object, Object> condition) {
    List<Object> list = userDao.getUserList(condition);
    List<Object> total = new ArrayList<Object>();
    if (list != null && list.size() > 0) {
      total.add(userDao.countUserList(condition));
    }
    Map<String, List<Object>> map = new HashMap<String, List<Object>>();
    map.put("total", total);
    map.put("rows", list);
    return map;
  }

  /*
   * (non-Javadoc)
   * 
   * @see cn.itganhuo.service.UserService#loadById(java.lang.String)
   */
  public User loadById(String id) {
    if (StringUtil.hasText(id))
      return userDao.loadById(id);
    else
      return null;
  }

}
