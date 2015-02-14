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
package cn.itganhuo.app.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.common.utils.PropertiesUtil;
import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.MailService;

/**
 * <h2>邮件服务服务层实现类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	TaskExecutor executor;

	/**
	 * 将用户修改的原始密码发送到其邮箱中,如果其邮箱没有修改，那么则默认发送到注册的帐户的邮箱中发件人的名称和
	 * 邮箱是从javamail.properties中获取得到的 email:要发送的邮件地址 template:邮件模板
	 */
	public void sendMail(final User user, final Template template) {

		executor.execute(new Runnable() {

			public void run() {

				try {
					MimeMessage msg = mailSender.createMimeMessage();

					MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");

					String myName = PropertiesUtil.getInstance().load("javamail").getProperty("myname");
					String myEmail = PropertiesUtil.getInstance().load("javamail").getProperty("myemail");
					helper.setFrom(MimeUtility.encodeText(myName) + "<" + myEmail + ">");
					if (user.getEmail() == null || "".equals(user.getEmail())) {
						helper.setTo(user.getAccount());
					} else {
						helper.setTo(user.getEmail());
					}
					helper.setSubject(template.getName());
					helper.setText(template.getContent(), true);

					mailSender.send(msg);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
