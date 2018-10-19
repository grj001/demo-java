package com.demo.java.io.transienttest;

/*
 * transient关键字
如果不希望序列化某个变量（例如银行账户信息等敏感信息），又或者某个实例变量类型不可序列化（例如某个成员变量是引用了一个不可序列化的类型），

我们不希望程序递归地去序列化这些对象，则可以通过transient关键字来修饰变量，程序就不会序列化这个变量，例如下面这样。
如果用上面的例子序列化这个类，再执行程序将会得到下面的结果，可以看到aga并没有取序列化之前的结果，而是被初始化成0了
*/
class Person implements java.io.Serializable {

	private String name;
	private transient int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person(String name, int age) {
		System.out.println("有参数构造器");
		this.name = name;
		this.age = age;
	}
}
