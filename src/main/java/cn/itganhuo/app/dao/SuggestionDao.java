package cn.itganhuo.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itganhuo.app.entity.Suggestion;

/**
 * 
 * <p>提交意见数据层接口</p>
 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:19:05
 * @since   itganhuo1.0
 */
@Repository
public interface SuggestionDao {
	/**
	 * 
	 * <p>新增一条意见，成功返回1，失败返回0</p>
	 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:20:41
	 * @since   itganhuo1.0
	 * @param suggestion
	 * @return
	 */
	public int insertSuggestion(Suggestion suggestion);
	/**
	 * 
	 * <p>回复提交的意见</p>
	 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:29:13
	 * @since   itganhuo1.0
	 * @param suggestion 意见
	 * @return
	 */
	public int updateInfoById(Suggestion suggestion);

	/**
	 * 
	 * <p>根据条件返回提交的意见列表</p>
	 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:31:35
	 * @since   itganhuo1.0
	 * @param condition 查询条件
	 * @return 意见列表
	 */
	public List<Suggestion> getSuggestionList(Map<String, Object> condition);
	/**
	 * 
	 * <p>根据意见ID获得一个意见详细</p>
	 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午4:32:53
	 * @since   itganhuo1.0
	 * @param suggestionId 意见ID
	 * @return 意见详细
	 */
	public Suggestion loadSuggestionById(int suggestionId);
	/**
	 * 
	 * <p>根据条件统计数据行为分页做准备</p>
	 * @author  Java私塾在线学习社区（329232140）深圳-小朱36044976 2015-3-14 下午5:02:02
	 * @since   itganhuo1.0
	 * @param condition
	 * @return
	 */
	public int countSuggestionList(Map<String, Object> condition);
}
