package cn.itganhuo.app.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.itganhuo.app.AbstractContextControllerTests;
import cn.itganhuo.app.common.page.Pagination;
import cn.itganhuo.app.common.utils.DateUtil;
import cn.itganhuo.app.entity.Suggestion;
/**
 * 
 * <p>意见列表测试</p>
 * <ol>
 * <li></li>
 * </ol>
 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-4-5 下午2:47:05
 * @since   itganhuo1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSuggestionService extends AbstractContextControllerTests {

	@Resource
	private  SuggestionService suggestionService;

	//测试存储
	@Test
	public void testSaveSuggestion(){
		Suggestion s = new Suggestion();
		s.setTitle("test");
		s.setCommitter("test");
		s.setComment("test");
		s.setCommitDate(DateUtil.getNowDateTimeStr("yyyy-MM-dd HH:mm:SS"));
		s.setTreatmentSuggestion("okok");
		suggestionService.insertSuggestion(s);
	}
	//测试更新
	@Test
	public void testUpdateSuggestion(){
		Suggestion s = new Suggestion();
		s.setId(2);
		s.setCommitDate(DateUtil.getNowDateTimeStr("yyyy-MM-dd HH:mm:ss"));
		suggestionService.updateInfoById(s);
	}
	//测试findById
	@Test
	public void testLoadSuggestionById(){
		System.out.println(suggestionService.loadSuggestionById(2));
	}
	//测试 findAll
	@Test
	public void testGetSuggestionList(){
		System.out.println(suggestionService.getSuggestionList(null));
	}
}
