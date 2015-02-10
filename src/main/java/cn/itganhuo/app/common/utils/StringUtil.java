/*
 * 1. This project consists of JAVA private school online learning community group Friends co-creator
 *  [QQ group 329232140].
 * 2. See the list of IT dry technology sharing network [http://www.itganhuo.cn/teams].
 * 3. The author does not guarantee the quality of the project and its stability, reliability, and security
 *  does not bear any responsibility.
 * 1、本项目由JAVA私塾在线学习社区群友共同创作[QQ群329232140]；
 * 2、作者名单详见IT干货技术分享网[http://www.itganhuo.cn/teams]；
 * 3、作者不保证本项目质量并对其稳定性、可靠性和安全性不承担任何责任。
 */
package cn.itganhuo.app.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

import cn.itganhuo.app.exception.InternalException;

/**
 * <h2>针对字符串封装常用功能，具体可看每个方法上的注释。</h2>
 * <dl>
 * <dt>功能描述</dt>
 * <dd>无</dd>
 * <dt>使用规范</dt>
 * <dd>在本类中添加新方法前仔细对比不要弄重复了，作为工具类建议每个方法都为static，这样可避免new太多的实例造成 内存紧张。</dd>
 * </dl>
 */
public class StringUtil {

	private static final Logger logger = LogManager.getLogger(StringUtil.class);

	private StringUtil() {
	}

	/**
	 * 采用MD5算法加密字符串
	 * <ol>
	 * <li>加密算法工具类由shiro提供</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param str
	 * @return
	 */
	public static final String getMD5Shiro(String str) {
		if (!StringUtil.hasText(str))
			return "";
		return new Md5Hash(str).toHex();
	}

	/**
	 * 采用Sha512散列算法加密字符串
	 * <ol>
	 * <li>加密算法工具类由shiro提供</li>
	 * </ol>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param str
	 *            你要加密的字符
	 * @param hexEncoded
	 *            默认采用十六进制编码，该值为true时使用六十四进制编码
	 * @return 加密后的字符
	 */
	public static final String getSha512Shiro(String str, boolean hexEncoded) {
		if (!StringUtil.hasText(str))
			return "";
		if (hexEncoded)
			return new Sha512Hash(str).toBase64();
		else
			return new Sha512Hash(str).toHex();
	}

	/**
	 * 判断一个字符串是否不为空
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param str
	 *            要判断的字符
	 * @return 如果有值返回true，否则返回false
	 */
	public static final boolean hasText(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return false;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return true;
		return false;
	}

	/**
	 * 将一个字符串转换成int型（转换出错默认结果是0）
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param string
	 * @return
	 */
	public static final int getInt(String s) {
		return getInt(s, 0);
	}

	/**
	 * 将一个字符串转换成int型，可以指定默认值
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param string
	 * @return
	 */
	public static final int getInt(String s, int i) {
		if (s == null)
			return i;
		try {
			return Integer.parseInt(s.trim());
		} catch (Exception exception) {
			return i;
		}
	}

	/**
	 * 获取当前访问者真实IP
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @return
	 */
	public static String getNowHttpIp(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String tmp = request.getHeader("x-forwarded-for");
		if (StringUtil.hasText(tmp)) {
			if (tmp.indexOf(",") >= 0) {
				ip = tmp.split(",")[0].trim();
			} else {
				ip = tmp;
			}
		}
		return ip;
	}

	/**
	 * 把unicode转换为中文
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param ascii
	 *            要转换的unicode字符
	 * @return
	 * @throws ItGanHuoException
	 */
	public static String unicode2Str(String s) {
		if (!StringUtil.hasText(s)) {
			throw new InternalException(logger, "value does not exist.");
		}
		return fromEncodedUnicode(s.toCharArray(), 0, s.length());
	}

	private static String fromEncodedUnicode(char[] in, int off, int len) {
		char aChar;
		char[] out = new char[len];
		int outLen = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = (char) aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	/**
	 * 将字符串转换成unicode
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param theString
	 *            要被转换的字符
	 * @param escapeSpace
	 *            是否忽略空格，为true时在空格后面是否加个反斜杠。
	 * @return
	 * @throws ItGanHuoException
	 */
	public static String toEncodedUnicode(String theString, boolean escapeSpace) {
		if (!StringUtil.hasText(theString)) {
			throw new InternalException(logger, "value does not exist.");
		}
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}

			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/**
	 * <h2>判断传进来的字符中是否包含特殊字符</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>如果你要自定义传入特殊字符可以使用这个方法。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param source
	 *            要判断的字符
	 * @param str
	 *            传入特殊字符，多个特殊字符之间用下划线“_”分隔。
	 * @return 如果字符合法返回ture，否则返回false表示字符中含有认定的特殊字符。
	 */
	public static boolean ifContainsSpecialStr(String source, String str) {
		if (!StringUtil.hasText(source) || !StringUtil.hasText(str)) {
			logger.warn("Can not be judged as parameter is empty.");
			return false;
		}
		String[] s = str.split("_");
		if (s.length <= 0) {
			logger.warn("Can not be judged because of the special characters incoming format is incorrect.");
			return false;
		}
		for (int i = 0; i < s.length; i++) {
			if (source.indexOf(s[i].trim()) != -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <h2>判断传进来的字符中是否包含特殊字符</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>目前认定属于特殊字符的有：`_~_!_#_$_%_^_&_*_(_)_-_=_+_{_}_[_]_|_\_;_:_'_"_<_>_,_._/_?。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param source
	 *            要判断的字符
	 * @return 如果字符合法返回ture，否则返回false表示字符中含有认定的特殊字符。
	 */
	public static boolean ifContainsSpecialStr(String source) {
		return StringUtil.ifContainsSpecialStr(source, "`_~_!_#_$_%_^_&_*_(_)_-_=_+_{_}_[_]_|_\\_;_:_\'_\"_<_>_,_._/_?");
	}

	/**
	 * <h2>判断传进来的字符中是否包含特殊字符，如果存在就将这个特殊字符替换成指定字符。</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>如果你要自定义传入特殊字符可以使用这个方法。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param source
	 *            要判断的字符
	 * @param str
	 *            传入特殊字符，多个特殊字符之间用下划线“_”分隔。
	 * @param replaceStr
	 *            传入要替换的字符，如果传入null则表示删除找到的特殊字符。
	 * @return 如果字符合法返回ture，否则返回false表示字符中含有认定的特殊字符。
	 */
	public static String ifContainsSpecialStr2Replace(String source, String str, String replaceStr) {
		if (!StringUtil.hasText(source) || !StringUtil.hasText(str)) {
			logger.warn("Can not be judged as parameter is empty.");
			return null;
		}
		String[] s = str.split("_");
		if (s.length <= 0) {
			logger.warn("Can not be judged because of the special characters incoming format is incorrect.");
			return null;
		}
		return StringUtil.replaceSpecialStrMatch(source, s, replaceStr);
	}

	/**
	 * <h2>判断传进来的字符中是否包含特殊字符，如果存在就将这个特殊字符删除掉（用NULL来替换它）。</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>目前认定属于特殊字符的有：`_~_!_#_$_%_^_&_*_(_)_-_=_+_{_}_[_]_|_\_;_:_'_"_<_>_,_._/_?。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param source
	 *            要判断的字符
	 * @return 如果字符合法返回ture，否则返回false表示字符中含有认定的特殊字符。
	 */
	public static String ifContainsSpecialStr2Replace(String source) {
		return StringUtil.ifContainsSpecialStr2Replace(source, "`_~_!_#_$_%_^_&_*_(_)_-_=_+_{_}_[_]_|_\\_;_:_\'_\"_<_>_,_._/_?", null);
	}

	/**
	 * <h2>匹配替换指定的特殊字符</h2>
	 * <dl>
	 * <dt>功能描述</dt>
	 * <dd>这个方法是与ifContainsSpecialStr2Replace方法配套使用的私有方法。</dd>
	 * <dt>使用规范</dt>
	 * <dd>无</dd>
	 * </dl>
	 * 
	 * @version 0.0.1-SNAPSHOT
	 * @author 深圳-小兴
	 * @param source
	 *            要判断的字符
	 * @param str
	 *            传入特殊字符，多个特殊字符之间用下划线“_”分隔。
	 * @param replaceStr
	 *            传入要替换的字符，如果传入null则表示删除找到的特殊字符。
	 * @return 如果字符合法返回ture，否则返回false表示字符中含有认定的特殊字符。
	 * @return 返回处理OK的字符
	 */
	private static String replaceSpecialStrMatch(String source, String[] s, String replaceStr) {
		for (int i = 0; i < s.length; i++) {
			boolean bool = true;
			while (bool) {
				int index = source.indexOf(s[i].trim());
				if (index != -1) {
					if (replaceStr == null || "".equals(replaceStr.trim())) {
						source = source.substring(0, index) + source.substring(index + 1, source.length());
					} else {
						source = source.substring(0, index) + replaceStr + source.substring(index, source.length());
					}
				} else {
					bool = false;
				}
			}
		}
		return source;
	}

}
