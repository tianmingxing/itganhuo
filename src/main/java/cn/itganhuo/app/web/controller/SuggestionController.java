package cn.itganhuo.app.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.Paging;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.Suggestion;
import cn.itganhuo.app.service.SuggestionService;

/**
 * 
 * <p>提交意见控制器</p>
 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午5:27:14
 * @since   itganhuo1.0
 */
@Controller
@RequestMapping("/suggestion")
public class SuggestionController {
	
	@Resource
	private SuggestionService suggestionService;
	
	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	@ResponseBody
	public RespMsg suggestion(Suggestion suggestion_model){
		RespMsg respMsg = new RespMsg();
		if (suggestion_model != null && StringUtil.hasText(suggestion_model.getComment())) {
			if ("".equals(suggestion_model.getCommitter())) suggestion_model.setCommitter("匿名提交");
			suggestion_model.setCommitDate(DateUtil.getNowDateTimeStr(null));
			suggestion_model.setTreatmentSuggestion("您的意见正在处理当中");
			if (suggestionService.insertSuggestion(suggestion_model) > 0) {
				respMsg.setStatus("1000");
				respMsg.setMessage(ConfigPool.getString("respMsg.suggestion.success"));
			}
			else{
				respMsg.setStatus("9000");
				respMsg.setMessage(ConfigPool.getString("respMsg.suggestion.failure"));
			} 
			
		}
		return respMsg;
	}
	
	@RequestMapping(value = "/list/{now_page}", method = RequestMethod.GET)
	public ModelAndView suggestions(@PathVariable String now_page, HttpServletRequest request){
		// 请求路径
		String request_get_context_path = request.getContextPath();

		// 组织分页参数
		Paging paging = new Paging();
		paging.setSort("id");
		paging.setOrder("desc");
		if (now_page.matches("^([1-9]|[1-9][0-9]+)+$")) {
			paging.setPage(StringUtil.getInt(now_page, 1));
		}
		paging.setOffrow((paging.getPage() -1) * paging.getRows());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", paging);
		List<Suggestion> suggestions = suggestionService.getSuggestionList(map);
		int total = suggestionService.countSuggestionList(null);
		Pagination pagination = new Pagination(StringUtil.getInt(now_page, 1), 10, 5, total, request_get_context_path.concat("/suggestion/list"),"");
		
		// 返回数据
		ModelAndView mav = new ModelAndView();
		mav.addObject("suggestions", suggestions);
		mav.addObject("pagination", pagination);
		mav.addObject("path", request.getContextPath());
		mav.addObject("servletPath", request.getServletPath());
		mav.setViewName("suggestion_list");
		return mav;
	}

}
