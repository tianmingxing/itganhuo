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
package cn.itganhuo.app.service;

import cn.itganhuo.app.entity.Template;

/**
 * <h2>邮件通知服务层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>该接口用于对外邮件通知功能</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
public interface MailService {

	/**
	 * <h2>[功能用途简述]</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>... ...</dd>
	 * <dt>使用规范</dt>
	 * <dd>... ...</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param email 邮件地址
	 * @param template 信息模板
	 */
	public void sendMail(String email, Template template);
}
