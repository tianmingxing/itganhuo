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

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.dao.AttentionDao;
import cn.itganhuo.app.entity.Attention;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.AttentionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注操作之服务层实现类
 * Created by 小兴 on 2015/3/22.
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionDao attentionDao;

    @Override
    public List<Attention> find(Map<String, Object> param) {
        return attentionDao.find(param);
    }

    @Override
    public RespMsg saveAttentionInfo(Attention attention) {
        RespMsg respMsg = new RespMsg();
        Subject current_user = SecurityUtils.getSubject();
        User user_model = (User) current_user.getSession().getAttribute(ConstantPool.USER_SHIRO_SESSION_ID);
        if (user_model != null) {
            // 判断用户是否有关注过该标签
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("userId", user_model.getId());
            param.put("labelId", attention.getLabelId());
            param.put("byUserId", attention.getByUserId());
            param.put("articleId", attention.getArticleId());
            List<Attention> attentions = this.find(param);
            if (attentions == null || attentions.size() == 0) {
                attention.setUserId(user_model.getId());
                attention.setPostDate(DateUtil.getNowDateTimeStr(null));
                if (!attentionDao.insert(attention)) {
                    respMsg.setStatus("9999");
                    respMsg.setMessage(ConfigPool.getString("respMsg.attention.SaveConcernInfoFailed"));
                }
            } else {
                respMsg.setStatus("2000");
                respMsg.setMessage(ConfigPool.getString("respMsg.attention.YouBeenConcernedAboutLabel"));
            }
        } else {
            respMsg.setStatus("1000");
        }
        return respMsg;
    }
}
