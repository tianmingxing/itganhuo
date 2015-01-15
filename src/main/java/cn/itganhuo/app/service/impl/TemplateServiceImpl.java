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
package cn.itganhuo.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.TemplateDao;
import cn.itganhuo.app.entity.Template;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.TemplateService;
import cn.itganhuo.app.service.UserService;

/**
 * <h2>[类用途简述]</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-朱塞佩
 */
@Service
public class TemplateServiceImpl implements TemplateService {

	private TemplateDao templateDao;
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	public int insert(Template template) {
		// template.setId(UUID.randomUUID().toString());
		return templateDao.insert(template);
	}

	public Template loadByName(String name) {
		return templateDao.loadByName(name);
	}

	public List<Template> loadAll() {
		return templateDao.loadAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.EmailTemplateService#loadById(java.lang.String)
	 */
	public Template loadById(int id) {
		return templateDao.loadById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.service.EmailTemplateService#generateAuthURL(java.lang.String )
	 */
	@Transactional
	public String generateAuthURL(String account) {
		StringBuffer buffer = new StringBuffer(ConfigPool.getString("requestGetContextPath")).append("/auth_email/");
		User um = userService.loadByAccount(account);
		buffer.append(um.getId());
		String str = String.valueOf(um.getId()).concat(account).concat(um.getSalt()) + System.currentTimeMillis();
		String code = StringUtil.getSha512Shiro(str, false);
		buffer.append("/".concat(code));
		User tmp_user2 = new User();
		tmp_user2.setAccount(account);
		tmp_user2.setEmailValidateCode(code);
		tmp_user2.setEmailValidateDate(DateUtil.getNowDateTimeStr(null));
		userService.updateInfoByAccount(tmp_user2);
		return buffer.toString();
	}

}
