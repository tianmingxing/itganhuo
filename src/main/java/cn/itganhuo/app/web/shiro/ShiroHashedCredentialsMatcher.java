package cn.itganhuo.app.web.shiro;

import cn.itganhuo.app.common.pool.ThreadLocalManager;
import cn.itganhuo.app.common.utils.StringUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * <h2>自定义密码匹配器，用来区分第三方登录还是本地常规登录。</h2>
 * <dl>
 * <dt>[功能描述]</dt>
 * <dd>
 * <ol>
 * <li>在密码匹配方法里根据登录类型动态切换认证方法;</li>
 * <li>如果是第三方例如则默认密码正确，因为第三方跳转过来就表示身份是得到认可了的;</li>
 * <li>如果是传统登录则还是走默认的密码匹配流程；</li>
 * </ol>
 * </dd>
 * <dt>[使用规范]</dt>
 * <dd>由SHIRO认证时调用</dd>
 * </dl>
 *
 * @author 深圳-小兴
 */
public class ShiroHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        if (StringUtil.hasText(ThreadLocalManager.getInstance().getValue())) {
            return true;
        } else {
            return super.doCredentialsMatch(token, info);
        }
    }

}
