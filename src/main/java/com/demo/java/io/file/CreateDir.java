package com.demo.java.io.file;

import java.io.File;

public class CreateDir {

	public static void main(String args[]) {
		String dirname = "src/main/java/com/grj/javatest/io/file/test";
		File d = new File(dirname);
		// 现在创建目录
		d.mkdirs();
	}
}
