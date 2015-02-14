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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itganhuo.app.dao.DictionariesDao;
import cn.itganhuo.app.entity.Dictionaries;
import cn.itganhuo.app.service.DictionariesService;

/**
 * <h2>数据字典服务层实现类</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>无</dd>
 * </dl>
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 天津-小朱
 */
@Service
public class DictionariesServiceImpl implements DictionariesService {

	@Autowired
	private DictionariesDao dictionariesDao;

	public int addDictionaries(Dictionaries dictionaries) {
		return dictionariesDao.insert(dictionaries);
	}

	public boolean updateInfoDictionaries(Dictionaries dictionaries) {
		return dictionariesDao.updateInfo(dictionaries) == 1;
	}

	public boolean delDictionaries(int id) {
		return dictionariesDao.delete(id);
	}

	public Dictionaries getDictionariesModelById(int id) {
		return dictionariesDao.loadById(id);
	}

	public Map<String, List<Object>> getDictionariesList(Map<Object, Object> condition) {
		List<Object> list = dictionariesDao.getDictionariesList(condition);
		List<Object> total = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			total.add(dictionariesDao.countDictionariesList(condition));
		}
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}

}
