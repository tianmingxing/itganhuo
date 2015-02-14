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
 * <h2>处理用户授权、认证</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>用户登录和权限验证</dd>
 * <dt>使用规范</dt>
 * <dd>如果在系统运行过程中修改了用户权限，此时还应该调用this.clearUserCache清除缓存以立即生效。</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
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
		// TODO 此处暂时把权限固定，后续根据需求在做。
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
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Began to validate user credentials.");
		}

		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		User user = userService.login(uptoken.getUsername());
		if (user != null && StringUtil.hasText(user.getAccount())) {
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());
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
	 * @version 0.0.2-SNAPSHOT
	 * @author 深圳-小兴
	 * @param user_id
	 *            用户账号
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
