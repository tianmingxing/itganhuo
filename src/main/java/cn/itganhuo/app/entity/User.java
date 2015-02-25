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
package cn.itganhuo.app.entity;

import java.io.Serializable;

/**
 * 用户数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class User implements Serializable {

	private static final long serialVersionUID = -5147036028865697127L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 登录账户
	 */
	private String account;
	/*
	 * 登录密码
	 */
	private String password;
	/*
	 * 密码加盐字段
	 */
	private String salt;
	/*
	 * 账号是否锁定，默认新注册账号是锁定状态（0不锁定，1锁定）
	 */
	private Integer isLock;
	/*
	 * 昵称
	 */
	private String nickname;
	/*
	 * 性别（1男，2女，3保密）
	 */
	private Integer sex;
	/*
	 * 邮箱地址
	 */
	private String email;
	/*
	 * QQ
	 */
	private Integer qq;
	/*
	 * 手机
	 */
	private String phone;
	/*
	 * 座机
	 */
	private String tel;
	/*
	 * 账号注册时间
	 */
	private String postDate;
	/*
	 * 账号类型（1会员，999管理员）
	 */
	private Integer type = 1;
	/*
	 * 账号最后登录时的IP地址
	 */
	private String lastLoginIp;
	/*
	 * 账号最后一次登录时间
	 */
	private String lastLoginDate;
	/*
	 * 邮箱地址是否认证（0未认证，1已认证）
	 */
	private Integer isValidateEmail;
	/*
	 * 邮箱认证码
	 */
	private String emailValidateCode;
	/*
	 * 邮箱认证开始时间，如果认证成功则修改为验证成功时的时间。
	 */
	private String emailValidateDate;
	/*
	 * 用户积分
	 */
	private Double credits;

	public Double getCredits() {
		return credits;
	}

	public void setCredits(Double credits) {
		this.credits = credits;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getIsValidateEmail() {
		return isValidateEmail;
	}

	public void setIsValidateEmail(Integer isValidateEmail) {
		this.isValidateEmail = isValidateEmail;
	}

	public String getEmailValidateCode() {
		return emailValidateCode;
	}

	public void setEmailValidateCode(String emailValidateCode) {
		this.emailValidateCode = emailValidateCode;
	}

	public String getEmailValidateDate() {
		return emailValidateDate;
	}

	public void setEmailValidateDate(String emailValidateDate) {
		this.emailValidateDate = emailValidateDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password="
				+ password + ", salt=" + salt + ", isLock=" + isLock
				+ ", nickname=" + nickname + ", sex=" + sex + ", email="
				+ email + ", qq=" + qq + ", phone=" + phone + ", tel=" + tel
				+ ", postDate=" + postDate + ", type=" + type
				+ ", lastLoginIp=" + lastLoginIp + ", lastLoginDate="
				+ lastLoginDate + ", isValidateEmail=" + isValidateEmail
				+ ", emailValidateCode=" + emailValidateCode
				+ ", emailValidateDate=" + emailValidateDate + ", credits" + credits + "]";
	}

}