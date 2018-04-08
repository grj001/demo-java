package com.grj.javatest.io.serialize.userdefine.writeobject;

import java.io.IOException;

/*
 * 通过重写writeObject/readObject自定义序列化
transient可以对某些变量进行屏蔽序列化效果，但是过于死板。Java还提供了一种方法来自定义序列化，就是在目标类中重写流对象的writeObject/readObject方法。

上面的序列化方式都使用的是流对象的默认序列化方法，oos.defaultWriteObject/ois.defaultReadObject，包括transient关键字，默认就是不做序列化。

而通过重写这两个方法，可以很具体地对目标类的指定变量做特殊操作，transient关键字修饰的变量不再是直接屏蔽，而是和其他变量一样可以自定义序列化的方式。

还有一个可重写的方法是readObjectNoData()，当序列化不完整（例如序列化版本不同，或者序列化流被篡改）时使用这个方法可以正确地初始化反序列化的对象。

重写的writeObject跟重写readObject总是成对出现，在序列化中做了什么操作（例如加密），在反序列化中需要做相反操作，且各个变量序列化和反序列化的顺序需要一样。

例如下面这样，
*/

class Person implements java.io.Serializable {

	private String name;
	private transient int age;

	public Person(String name, int age) {
		System.out.println("有参数构造器");
		this.name = name;
		this.age = age;
	}

	private void writeObject(java.io.ObjectOutputStream oos) throws IOException {
		oos.writeObject(new StringBuilder(name).reverse());
		oos.writeInt(age);
	}

	private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
		this.name = ((StringBuilder) ois.readObject()).reverse().toString();
		this.age = ois.readInt();
	}

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
}
