package cn.itganhuo.app.common.pool;

/**
 * 用来管理线程安全的变量
 * Created by Tianmingxing on 2015/4/22.
 */
public class ThreadLocalManager {

    private ThreadLocalManager() {
        this.loginType = new ThreadLocal<String>();
    }

    /**
     * 用来标识当前线程登录类型
     */
    private ThreadLocal<String> loginType;
    private final static ThreadLocalManager local = new ThreadLocalManager();

    public static ThreadLocalManager getInstance() {
        return local;
    }

    public void setValue(String value) {
        loginType.set(value);
    }

    public String getValue() {
        return (loginType.get() == null) ? null : loginType.get();
    }

    public void remove() {
        loginType.remove();
    }
}
