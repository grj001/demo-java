package com.java.demo.designpatterns.singleton;

public class InnerClassSingletonPattern {

	/**
	 * 测试延迟加载
	 */
	static InnerClassSingletonPattern i0;

	/**
	 * 内部类实现单例模式 延迟加载，减少内存开销
	 */
	private static class InnerClassSingletonPatternHandler {
		private static InnerClassSingletonPattern instance = new InnerClassSingletonPattern();
		static {
			System.out.println("内部类被解析了,但是没有初始化!");
		}
	}

	/**
	 * 私有的构造函数
	 */
	private InnerClassSingletonPattern() {
		// 判断单例对象是否已经存在，用于控制非法反射单例类的构造函数
		if (InnerClassSingletonPatternHandler.instance != null) {
			try {
				throw new IllegalAccessException("单例对象已经被实例化，请不要非法反射构造函数");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 合法获取单例对象的途径
	 */
	public static InnerClassSingletonPattern getInstance() {
		return InnerClassSingletonPatternHandler.instance;
	}

	protected void method() {
	}

	public static void main(String[] args) {
		// 测试延迟加载
		System.out.println(i0);
		i0 = InnerClassSingletonPattern.getInstance();
		System.out.println(i0);
		
		InnerClassSingletonPattern i1 = InnerClassSingletonPattern.getInstance();
		InnerClassSingletonPattern i2 = InnerClassSingletonPattern.getInstance();
		
		// 测试是否为单例
		if (i1.equals(i2)) {
			System.out.println("是单例模式!");
		}
	}
}
