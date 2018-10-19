package com.demo.java.io.serialize;

class Person implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

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

	private String name;
	private int age;

	public Person(String name, int age) {
		System.out.println("有参数构造器");
		this.name = name;
		this.age = age;
	}
}
