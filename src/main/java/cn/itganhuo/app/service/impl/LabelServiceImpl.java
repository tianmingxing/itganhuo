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

import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.dao.LabelDao;
import cn.itganhuo.app.entity.Label;
import cn.itganhuo.app.service.LabelService;

/**
 * <h2>标签服务层实现类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelDao labelDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.LabelService#saveLabel(cn.itganhuo.app.entity.Label)
	 */
	public int saveLabel(Label label) {
		if (label == null || !StringUtil.hasText(label.getName())) {
			return 0;
		}
		return labelDao.insert(label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.LabelService#delLabelById(java.lang.String)
	 */
	public boolean delLabelById(int id) {
		if (id <= 0) {
			return false;
		}
		return labelDao.delById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.LabelService#modLabelById(cn.itganhuo.app.entity.Label)
	 */
	public boolean updateLabelById(Label label) {
		if (label == null || !StringUtil.hasText(label.getName())) {
			return false;
		}
		return labelDao.updateById(label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.itganhuo.app.service.LabelService#getLabelByCondition(cn.itganhuo.app.entity.Label)
	 */
	public List<Label> getLabelByCondition(Label label) {
		return labelDao.getLabelByCondition(label);
	}

}
