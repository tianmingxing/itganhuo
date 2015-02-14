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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.dao.ReplyDao;
import cn.itganhuo.app.entity.Reply;
import cn.itganhuo.app.service.ReplyService;

/**
 * <h2>评论回复数据业务处理实现类</h2>
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
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;

	/* (non-Javadoc)
	 * @see cn.itganhuo.app.service.ReplyService#addReply(cn.itganhuo.app.entity.Reply)
	 */
	@Override
	public int addReply(Reply reply) {
		if (reply == null) {
			return 0;
		}
		return replyDao.insert(reply);
	}

}
