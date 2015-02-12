package cn.itganhuo.app.common;

import org.junit.Test;

public class TestAes {

	String key = "362C1E982014B30D73DFCEAE0A";
	
	@Test
	public void testEncrypt() {
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("jdbc:mysql://localhost:3306/itganhuo?useUnicode=true&characterEncoding=utf-8", key)));
		System.out.println(Aes.parseByte2HexStr(Aes.encrypt("root", key)));
	}
}
