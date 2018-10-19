package com.demo.java.path;

import org.junit.Test;

public class GetCurrentPath {
	@Test
	public void test04() {
		String projectPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
		//包的路径
		String packageName = this.getClass().getPackage().getName().replaceAll("\\.", "/");
		System.out.println(projectPath+"/src/main/java/"+packageName+"/");
	}
	
	public static String getCurrentPath(Class<?> A){
		String projectPath = System.getProperty("user.dir").replaceAll("\\\\", "/");
		//包的路径
		String packageName = A.getPackage().getName().replaceAll("\\.", "/");
		return projectPath+"/src/main/java/"+packageName+"/";
	}
}
