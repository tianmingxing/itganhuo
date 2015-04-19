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
package cn.itganhuo.app.service;

import cn.itganhuo.app.entity.Attention;
import cn.itganhuo.app.entity.RespMsg;

import java.util.List;
import java.util.Map;

/**
 * 关注操作之服务层接口
 * Created by 小兴 on 2015/3/22.
 */
public interface AttentionService {

    /**
     * 根据用户编号和标签编号或被关注用户编号查询列表数据
     *
     * @param param 查询参数，可以传1-3个参数进来
     * @return 返回数据列表
     */
    public List<Attention> find(Map<String, Object> param);

    /**
     * <h2>保存关注数据，可以关注标签、关注作者和收藏文章</h2>
     * <dl>
     * <dt>功能描述</dt>
     * <dd>这三者的关联数据全部放到关注表里，因为收藏文章也是一种关注行为。</dd>
     * <dt>使用规范</dt>
     * <dd>返回状态信息供前端业务处理</dd>
     * </dl>
     *
     * @return 处理状态信息
     * @author 深圳-小兴
     */
    public RespMsg saveAttentionInfo(Attention attention);

}
