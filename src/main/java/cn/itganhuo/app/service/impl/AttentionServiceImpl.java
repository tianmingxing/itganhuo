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

import cn.itganhuo.app.common.pool.ConstantPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.dao.AttentionDao;
import cn.itganhuo.app.entity.Attention;
import cn.itganhuo.app.entity.User;
import cn.itganhuo.app.service.AttentionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean save(Attention attention) {
        attention.setPostDate(DateUtil.getNowDateTimeStr(null));
        if (attentionDao.insert(attention) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<Attention> find(Map<String, Object> param) {
        return attentionDao.find(param);
    }
}
