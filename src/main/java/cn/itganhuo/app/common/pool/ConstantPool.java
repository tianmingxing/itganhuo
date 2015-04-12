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
package cn.itganhuo.app.common.pool;

/**
 * <h2>自定义常量属性池</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>本系统中所用到的常量值都统一在这里进行配置。</dd>
 * <dt>使用规范</dt>
 * <dd>在此配置的常量属性值应该是静态不可修改的，如果不符合这两个条件那么请将到resources/config-pool.properties中配置你要的
 * 属性。在程序中反复使用且二次变动性较小的常量属性可以在本类中配置，从而可以避免重复命名提高程序可维护性。</dd>
 * </dl>
 *
 * @author 深圳-小兴
 * @version 0.0.1-SNAPSHOT
 */
public class ConstantPool {

    /**
     * 用户信息在登录时会存储在Shiro会话中，这个常量是用来作为KEY的一个标识。
     */
    public final static String USER_SHIRO_SESSION_ID = "user_shiro_session_id";

    /**
     * 用户账号信息临时存放到客户端缓存中，这个常量是用来作为KEY的一个标识。
     */
    public final static String USER_ACCOUNT_COOKIE_ID = "user_account_cookie_id";

    /**
     * 系统配置文件所在相对路径名称
     */
    public final static String CONFIG_POOL_FILE_NAME = "config-pool.properties";

    /**
     * 系统配置文件所在相对路径名称
     */
    public final static String KEYWORD_POOL_FILE_NAME = "keyword-pool.txt";

    /**
     * 在客户端请求中的令牌标识
     */
    public final static String REQUEST_TOKEN = "request_token";

    /**
     * 在服务端会话中的令牌标识
     */
    public final static String SESSION_TOKEN = "session_token";

    /**
     * 存放到config-pool.properties中用来标识项目部署真实路径绝对地址的键
     */
    public final static String REAL_PATH = "real_path";

    /**
     * 存放到本次会话中的访问者前一次访问时间的标识
     */
    public final static String LAST_REQUEST_TIME = "last_request_time";
    /**
     * 存放到本次会话期内访问次数标识
     */
    public final static String VISITS_DURING_SESSION = "visits_during_session";
    /**
     * 访问者标识，它的存在与否用来决定在会话期内是否统计文章访问量
     */
    public final static String VISITS_FLAG = "visits_flag";
    /**
     * 替换字符，用来在检测到敏感字符后进行替换
     */
    public final static String REPLACEMENT_CHARACTER = "*";

}
