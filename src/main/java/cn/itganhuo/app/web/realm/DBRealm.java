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
package cn.itganhuo.app.web.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.UserService;

/**
 * 自定义域用来处理用户授权、认证
 * 
 * @author 深圳-小兴(504487927) 2014年7月6日 下午2:25:44
 * @since itganhuo1.0
 */
public class DBRealm extends AuthorizingRealm {

  private static final Logger logger = LoggerFactory.getLogger(DBRealm.class);

  private UserService userService = null;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * 根据用户名去数据库查询它对应的权限集合
   * 
   * @author 深圳-小兴(504487927) 2014-6-30
   * @since itganhuo1.0
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    if (logger.isDebugEnabled()) {
      logger.debug("Start reading user permissions.");
    }

    String account = (String) getAvailablePrincipal(principals);
    User user = userService.login(account);
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    Set<String> role = new HashSet<String>();
    Set<String> stringPermissions = new HashSet<String>();
    if (1 == user.getType()) {
      role.add("user");
      stringPermissions.add("user:*");
    } else if (999 == user.getType()) {
      role.add("admin");
      stringPermissions.add("admin:*");
    }
    info.setRoles(role);
    info.setStringPermissions(stringPermissions);
    return info;
  }

  /**
   * 把界面上用户输入的账户密码与数据库中的凭证相比较
   * 
   * @author 深圳-小兴(504487927) 2014-6-30
   * @since itganhuo1.0
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    if (logger.isDebugEnabled()) {
      logger.debug("Began to validate user credentials.");
    }

    UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
    User user = userService.login(uptoken.getUsername());
    if (user != null && StringUtil.hasText(user.getAccount())) {
      SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getAccount(),
          user.getPassword(), getName());
      info.setCredentialsSalt(ByteSource.Util.bytes(uptoken.getUsername() + user.getSalt()));
      return info;
    } else {
      return null;
    }
  }

  /**
   * 清除缓存中指定用户的数据
   * <ol>
   * <li>目的：为了让缓存数据同步更新，先清除掉指定用户信息，逼迫shiro去查询数据库。</li>
   * <li>提示：通常在后台更改了用户权限之后需要立即生效时调用此方法</li>
   * </ol>
   * 
   * @author 深圳-小兴(504487927) 2014-9-4
   * @since itganhuo1.0
   * @param user_id
   *          用户账号
   */
  public void clearUserCache(String user_id) {
    if (logger.isDebugEnabled()) {
      logger.debug("Began to clear the user cache.");
    }

    SimplePrincipalCollection spc = new SimplePrincipalCollection();
    spc.add(user_id, getName());
    super.clearCachedAuthorizationInfo(spc);
  }

}
