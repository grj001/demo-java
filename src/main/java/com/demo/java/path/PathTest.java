package com.demo.java.path;

import java.io.File;

import org.junit.Test;

public class PathTest {
	
	@Test
	public void test() {
		File directory = new File(".");// 设定为当前文件夹
		try {
			System.out.println(directory.getCanonicalPath());// 获取标准的路径
			System.out.println(directory.getAbsolutePath());// 获取绝对路径
		} catch (Exception e) {
		}
	}
	@Test
	public void test01() {
		//项目路径
		System.out.println(System.getProperty("user.dir"));
	}
	
	@Test
	public void test02() {
		//编码后的路径
		File f = new File(this.getClass().getResource("/").getPath()); 
		System.out.println(f);
	}
	
	@Test
	public void test03() {
		//包的路径
		String packageName = this.getClass().getPackage().getName();
		System.out.println(packageName);
	}
	
}
