package cn.itganhuo.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itganhuo.app.dao.SuggestionDao;
import cn.itganhuo.app.entity.Suggestion;
import cn.itganhuo.app.service.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService{

	@Resource
	private SuggestionDao suggestionDao;
	
	@Override
	public int insertSuggestion(Suggestion suggestion) {
		return suggestionDao.insertSuggestion(suggestion);
	}

	@Override
	public int updateInfoById(Suggestion suggestion) {
		return suggestionDao.updateInfoById(suggestion);
	}

	@Override
	public List<Suggestion>  getSuggestionList(Map<String, Object> condition) {
		List<Suggestion> suggestions = suggestionDao.getSuggestionList(condition);
		return suggestions;
	}

	@Override
	public Suggestion loadSuggestionById(int suggestionId) {
		if (suggestionId > 0)
			return suggestionDao.loadSuggestionById(suggestionId);
		else
			return null;
	}

	@Override
	public int countSuggestionList(Map<String, Object> condition) {
		return suggestionDao.countSuggestionList(condition);
	}

}
