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
 * 翻页数据封装类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class Paging implements Serializable {

	private static final long serialVersionUID = 4222776003608625892L;
	/*
	 * 默认当前为第一页
	 */
	private Integer page = 1;
	/*
	 * 默认每页显示10条数据
	 */
	private Integer rows = 10;
	/*
	 * 默认游标位置处于0，因为默认查询第一页的数据。
	 */
	private Integer offrow = 0;
	/*
	 * 拟排序字段，对应在数据表中的字段。
	 */
	private String sort;
	/*
	 * 排序规则，升序或降序（ASC or DESC）
	 */
	private String order;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getOffrow() {
		return offrow;
	}

	public void setOffrow(Integer offrow) {
		this.offrow = offrow;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Paging [page=" + page + ", rows=" + rows + ", offrow=" + offrow + ", sort=" + sort + ", order=" + order + "]";
	}

}
