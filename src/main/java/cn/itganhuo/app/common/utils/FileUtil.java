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
package cn.itganhuo.app.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 对文件操作的工具方法类
 * 
 * @version 0.0.1-SNAPSHOT
 * @author 深圳-小兴
 */
public class FileUtil {

	private static final Logger log = LogManager.getLogger(FileUtil.class.getName());
	public static File file = null;

	public FileUtil() {
	}

	public static boolean createDirectory(String s) {
		try {
			file = new File(s);
			if (file.exists()) {
				throw new IOException("Directory exists!");
			}
			return file.mkdir();
		} catch (IOException ioexception) {
			System.out.println(ioexception.getMessage());
		}
		return false;
	}

	public static boolean createFile(String s) {
		try {
			file = new File(s);
			if (file.exists()) {
				throw new IOException("File exists!");
			}
			return file.createNewFile();
		} catch (IOException ioexception) {
			System.out.println(ioexception.getMessage());
		}

		return false;
	}

	public static String readPropertiesFile(String s, String s1) throws IOException {
		FileInputStream fileinputstream = new FileInputStream(new File(s));

		Properties properties = new Properties();
		properties.load(fileinputstream);
		try {

		} catch (Exception exception) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer
					.append("\u951F\u65A4\u62F7\u951F\u6770\u8BB9\u62F7\u53D6\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u4FA5\u7877\u62F7readPropertiesFile error: ");
			stringbuffer.append(s);
			stringbuffer
					.append("\u951F\u65A4\u62F7\u786E\u951F\u4FA5\u7877\u62F7\u951F\u89D2\u51E4\u62F7\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u8857\u9769\u62F7\u951F\u65A4\u62F7\u951F\u94F0\u51E4\u62F7\u951F\u65A4\u62F7\u951F\uFFFD");
			throw new IOException(stringbuffer.toString());
		}
		if (properties.containsKey(s1))
			return properties.getProperty(s1, null);
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u4FA5\u7877\u62F7\u951F\u53EB\u8BE7\u62F7\u951F\u65A4\u62F7(\u951F\u65A4\u62F7\u951F\u65A4\u62F7\u951F\u65A4\u62F7");
		stringbuffer.append(s1);
		throw new IOException(stringbuffer.toString());
	}

	public static final void delFile(String s) {
		File file1 = new File(s);
		if (file1.exists())
			file1.delete();
		return;
	}

	public static final void delFolder(String s) throws Exception {
		File file1 = new File(s);
		if (file1.exists() && file1.isDirectory()) {
			File afile[] = file1.listFiles();
			for (int i = 0; i < afile.length; i++) {
				if (afile[i].isFile()) {
					if (afile[i].exists())
						afile[i].delete();
					continue;
				}
				if (afile[i].isDirectory())
					delFolder(afile[i].toString());
			}

			file1.delete();
		}
		return;
	}

	public static final String readFile2String(String s) throws Exception {
		StringBuffer stringbuffer = new StringBuffer("");
		String s1 = "";
		BufferedReader bufferedreader = new BufferedReader(new FileReader(s));
		try {
			do {
				String s2 = bufferedreader.readLine();
				s1 = s2;
				if (s2 == null) {
					break;
				}
				stringbuffer.append(s1);
				stringbuffer.append("\n");
			} while (true);
			bufferedreader.close();
		} catch (IOException ioexception) {
			throw new Exception(new StringBuffer().append("Read file error:").append(ioexception.getMessage()).toString());
		}
		return stringbuffer.toString();
	}

	public static final String readFile2String(String filename, String charset) throws Exception {
		StringBuffer stringbuffer = new StringBuffer("");
		String s2 = "";
		try {
			File file1 = new File(filename);
			InputStreamReader inputstreamreader = new InputStreamReader(new FileInputStream(file1), charset);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			do {
				String s3 = bufferedreader.readLine();
				s2 = s3;
				if (s3 == null) {
					break;
				}
				stringbuffer.append(s2);
				stringbuffer.append("\n\r");
			} while (true);
			inputstreamreader.close();
			bufferedreader.close();
		} catch (IOException ioexception) {
			throw new Exception(new StringBuffer().append("Read file error:").append(ioexception).toString());
		}
		return stringbuffer.toString();
	}

	public static final String[] readFile2Array(String s) throws Exception {
		ArrayList<Object> arraylist = new ArrayList<Object>();
		BufferedReader bufferedreader = null;

		try {
			bufferedreader = new BufferedReader(new FileReader(s));
			do {
				String s2 = bufferedreader.readLine();
				if (s2 == null) {
					break;
				}
				arraylist.add(s2);
			} while (true);
		} catch (IOException ioexception) {
			throw new Exception(new StringBuffer().append("Read file error:").append(ioexception.getMessage()).toString());
		} finally {
			try {
				if (bufferedreader != null)
					bufferedreader.close();
			} catch (Exception exception) {
				throw exception;
			}

		}
		return (String[]) arraylist.toArray(new String[0]);
	}

	public static final String[] readFile2Array(String s, String s1) throws Exception {
		ArrayList<Object> arraylist = new ArrayList<Object>();
		BufferedReader bufferedreader = null;
		File file1 = new File(s);
		InputStreamReader inputstreamreader = new InputStreamReader(new FileInputStream(file1), s1);
		bufferedreader = new BufferedReader(inputstreamreader);
		try {
			do {
				String s3 = bufferedreader.readLine();
				if (s3 == null) {
					break;
				}
				if (!s3.trim().equals(""))
					arraylist.add(s3);
			} while (true);
		} catch (IOException ioexception) {
			throw new Exception(new StringBuffer().append("Read file error:").append(ioexception.getMessage()).toString());
		} finally {
			try {
				if (inputstreamreader != null)
					inputstreamreader.close();
				if (bufferedreader != null)
					bufferedreader.close();
			} catch (Exception exception) {
				throw exception;
			}

		}

		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

	public static final String[] getFileList(String s) {
		File file1 = new File(s);
		String as[] = file1.list();
		return as;
	}

	public static final void reName(String s, String s1) {
		File file1 = new File(s);
		if (file1.exists()) {
			file1.renameTo(new File(s1));
		}
	}

	public static final String[] readFolder(String s) {
		ArrayList<Object> arraylist = new ArrayList<Object>();
		File file1 = new File(s);
		File afile[] = file1.listFiles();
		for (int i = 0; i < afile.length; i++)
			arraylist.add(afile[i].getName());

		return (String[]) arraylist.toArray(new String[0]);
	}

	public static final String[] getFolderFromFolder(String s) {

		ArrayList<Object> arraylist = new ArrayList<Object>();
		File file1 = new File(s);
		File afile[] = file1.listFiles();
		for (int i = 0; i < afile.length; i++)
			if (afile[i].isDirectory())
				arraylist.add(afile[i].getName());
		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

	public static final String[] getFileFromFolder(String s) {

		ArrayList<Object> arraylist = new ArrayList<Object>();
		File file1 = new File(s);
		File afile[] = file1.listFiles();
		for (int i = 0; i < afile.length; i++)
			if (afile[i].isFile())
				arraylist.add(afile[i].getName());

		return (String[]) (String[]) arraylist.toArray(new String[0]);
	}

	public static final void createFile(String file, String charset, String content) throws Exception {
		BufferedWriter bufferedwriter = null;
		bufferedwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		try {
			bufferedwriter.write(content);
			if (bufferedwriter != null)
				bufferedwriter.close();
		} catch (IOException ioexception) {
			throw new Exception(ioexception);
		} catch (Exception exception) {
			throw exception;
		} finally {
			if (bufferedwriter != null)
				bufferedwriter.close();
		}
	}

	public static final void createFile(String file, String content) throws Exception {
		BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		bufferedwriter.write(content);
		bufferedwriter.close();
	}

	public static final void moveFile(String s, String s1) {
		FileInputStream fileinputstream = null;
		FileOutputStream fileoutputstream = null;
		File file1 = new File(s1);
		if (file1.exists())
			file1.delete();
		try {
			fileinputstream = new FileInputStream(s);
			fileoutputstream = new FileOutputStream(s1);
			int j;
			byte abyte0[] = new byte[1024];
			do {
				int i;
				j = fileinputstream.read(abyte0);
				i = j;
				if (j == -1)
					break;
				fileoutputstream.write(abyte0, 0, i);
			} while (true);
		} catch (FileNotFoundException filenotfoundexception) {
			filenotfoundexception.printStackTrace();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				fileinputstream.close();
				fileoutputstream.close();
			} catch (Exception exception) {
			}
		}
		File file_ = new File(s);
		if (file_.exists())
			file_.delete();
		return;
	}

	public static final void moveImg(String s, String s1) throws Exception {
		if (StringUtil.hasText(s) || StringUtil.hasText(s1) || s.equals(s1)) {
			return;
		}
		FileInputStream fileinputstream = null;
		FileOutputStream fileoutputstream = null;

		try {
			String as[] = s1.split("/");
			String s2 = "";
			for (int i = 0; i < as.length - 3; i++) {
				s2 = new StringBuffer().append(s2).append(as[i]).append("/").toString();
			}

			String s3 = as[as.length - 3];
			String s4 = as[as.length - 2];
			File file2 = new File(new StringBuffer().append(s2).append(s3).toString());
			if (!file2.exists())
				file2.mkdir();
			File file3 = new File(new StringBuffer().append(s2).append(s3).append("/").append(s4).toString());
			if (!file3.exists())
				file3.mkdir();

			File file4 = new File(s);
			if (file4.exists()) {
				fileinputstream = new FileInputStream(s);
				File file5 = new File(s1);
				if (file5.exists())
					file5.delete();
				fileoutputstream = new FileOutputStream(s1);
				int k;
				byte abyte0[] = new byte[1024];
				do {
					int j;
					k = fileinputstream.read(abyte0);
					j = k;
					if (k == -1)
						break;
					fileoutputstream.write(abyte0, 0, j);
				} while (true);
			}

		} catch (FileNotFoundException filenotfoundexception) {
			log.error(new StringBuffer().append("FileUtil.moveImg FileNotFoundException:").append(filenotfoundexception).toString());
		} catch (IOException ioexception) {
			log.error(new StringBuffer().append("FileUtil.moveImg IOException:").append(ioexception).toString());
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				fileinputstream.close();
				fileoutputstream.close();
			} catch (Exception exception) {
			}
		}
		File file1 = new File(s);
		if (file1.exists())
			file1.delete();
		return;
	}

	public static final void copyFile(String s, String s1) {
		FileInputStream fileinputstream = null;
		FileOutputStream fileoutputstream = null;

		try {
			fileinputstream = new FileInputStream(s);
			fileoutputstream = new FileOutputStream(s1);
			int j;
			byte abyte0[] = new byte[1024];
			do {
				int i;
				j = fileinputstream.read(abyte0);
				i = j;
				if (j == -1)
					break;
				fileoutputstream.write(abyte0, 0, i);
			} while (true);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				fileinputstream.close();
				fileoutputstream.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

}
