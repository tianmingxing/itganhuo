/*
 * 1. This project consists of JAVA private school online learning community group Friends co-creator
 *  [QQ group 329232140].
 * 2. See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 3. The author does not guarantee the quality of the project and its stability, reliability, and security
 *  does not bear any responsibility.
 * 1、本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140]；
 * 2、作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams]；
 * 3、作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任。
 */
package cn.itganhuo.app.service;

import java.util.List;

import cn.itganhuo.app.entity.Template;

/**
 * <h2>消息模板服务层接口</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>主要用于自定义消息模板格式，本系统里的消息形式有邮件、站内信两种。</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱，深圳-小兴
 */
public interface TemplateService {

	/**
	 * <h2>插入一条模板</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param template
	 *            模板数据
	 * @return 返回主键
	 */
	public int insert(Template template);

	/**
	 * <h2>根据模板名称返回相应模板</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param name
	 *            模板名称
	 * @return 一条模板记录
	 */
	public Template loadByName(String name);

	/**
	 * <h2>根据主键查询模板信息</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @param id
	 *            模板ID
	 * @return 一条模板记录
	 */
	public Template loadById(int id);

	/**
	 * <h2>[功能用途简述]</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>无</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 天津-小朱
	 * @return 返回模板列表
	 */
	public List<Template> loadAll();

	/**
	 * <h2>生成邮箱认证加密字符串</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>生成规则：域名/common/auth_email/用户ID/加密串</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param account
	 *            用户账号
	 * @return 返回邮件认证链接地址字符串
	 */
	public String generateAuthURL(String account);
}
