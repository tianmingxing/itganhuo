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
 * 邮件&短信&站内信模板数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
public class Template implements Serializable {

	private static final long serialVersionUID = 2668521167465565436L;
	/*
	 * ID
	 */
	private Integer id;
	/*
	 * 模块类型（1邮件模板，2短信模板，3站内信模板）
	 */
	private Integer type;
	/*
	 * 模板名称
	 */
	private String name;
	/*
	 * 模板内容，需要替换的变量用##包含起来。
	 */
	private String content;
	/*
	 * 邮件模板发布时间
	 */
	private String postDate;
	/*
	 * 是否启用，0未启用1启用
	 */
	private Integer isAvailable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
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
		Template other = (Template) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", type=" + type + ", name=" + name + ", content=" + content + ", postDate=" + postDate + ", isAvailable=" + isAvailable
				+ "]";
	}

}
