package cn.itganhuo.app.web.controller;

import cn.itganhuo.app.common.pool.ConfigPool;
import cn.itganhuo.app.common.utils.StringUtil;
import cn.itganhuo.app.entity.RespMsg;
import cn.itganhuo.app.entity.User;
import org.junit.Test;

/**
 * Created by Administrator on 2015/4/18.
 */
public class TestUserController {

    @Test
    public void test() {
        User user = new User();
        user.setAccount("504487927");
        user.setPassword("504487927");
        user.setAccessToken("504487927");
        RespMsg respMsg = new RespMsg();
        // 判断用户名长度是否在区间值内
        if (user.getAccount().length() < 6 || user.getAccount().length() > 20) {
            respMsg.setStatus("1000");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
        }
        // 判断用户名是否含有特殊字符
        if (!StringUtil.ifContainsSpecialStr(user.getAccount())) {
            respMsg.setStatus("1001");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.AccountNumberFormatNotLegitimate"));
        }
        // 判断密码长度是否在区间值内
        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            respMsg.setStatus("2000");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
        }
        // 判断密码是否含有特殊字符
        String[] s = {"`", "~", "#", "$", "%", "^", "&", "*", "(", ")", "-", "=", "+", "{", "}", "[", "]", "|", "\\", ";", ":", "\'", "\"", "<", ">", ",", "/"};
        if (!StringUtil.ifContainsSpecialStr(user.getPassword(), s)) {
            respMsg.setStatus("2001");
            respMsg.setMessage(ConfigPool.getString("respMsg.register.PasswordFormatNotLegitimate"));
        }
        // 判断用户名、密码中是否含有中文字符
        if (user.getAccount().matches("[\u4e00-\u9fa5]+") || user.getPassword().matches("[\u4e00-\u9fa5]+")) {
            respMsg.setStatus("3000");
            respMsg.setMessage(ConfigPool.getString("respMsg.common.CanNotContainChineseStr"));
        }
        System.out.println(respMsg);
    }
}
