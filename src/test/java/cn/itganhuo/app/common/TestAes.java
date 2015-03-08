package cn.itganhuo.app.common;

import org.junit.Test;

public class TestAes {

	String key = "362C1E982014B30D73DFCEAE0A";
	
	@Test
	public void testEncrypt() {
		//生成MySQL驱动信息
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("com.mysql.jdbc.Driver", key)));
		
		//生成本地数据库连接信息
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("jdbc:mysql://10.162.103.166/itganhuo?useUnicode=true&characterEncoding=utf-8", key)));
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("root", key)));
		
		//生成公共测试库连接信息
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("jdbc:mysql://114.215.171.211:3306/test?useUnicode=true&characterEncoding=utf-8", key)));
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("test", key)));
	}
}
