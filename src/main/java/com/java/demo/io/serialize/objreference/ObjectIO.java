package com.java.demo.io.serialize.objreference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*对象引用的序列化

如果某个类的成员变量不是基本类型，
而是另一个类的引用类型(例如 Teacher 类中有个成员变量 private Person student)，
那么这个引用类(Person)必须是可序列化的，当前类(Teacher)才可以序列化，
否则会抛出java.io.NotSerializableException异常。

对象不会被重复序列化
序列化对象将会产生一个序列号，每次序列化对象时会先检查是否已经序列化过该对象，只有未被序列化的对象才序列化，已被序列化的对象则直接输出序列号，而不会重新序列化该对象。
即：对象只有第一次序列化才生效。
*/

class Person implements java.io.Serializable {
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

class Teacher implements java.io.Serializable {

	private static final long serialVersionUID = 123456L;
	private String name;
	private Person student;

	public Teacher(String name, Person student) {
		super();
		this.name = name;
		this.student = student;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

}

public class ObjectIO {

	public static void writeTeacher() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"src/main/java/com/grj/javatest/io/filestream/serialize/objreference/teacher.txt"))) {
			Person per = new Person("孙悟空", 500);
			Teacher t1 = new Teacher("唐僧", per);
			Teacher t2 = new Teacher("菩提祖师", per);

			// 下面四行只会序列化三个对象，其中t1, t2 将引用同一个对象
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(per);
			oos.writeObject(t2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"src/main/java/com/grj/javatest/io/filestream/serialize/objreference/teacher.txt"))) {
			// 反序列化需要按照序列化的顺序取对象
			Teacher t1 = (Teacher) ois.readObject();
			Teacher t2 = (Teacher) ois.readObject();
			Person p = (Person) ois.readObject();
			Teacher t3 = (Teacher) ois.readObject();
			System.out.println("t1的student引用和p是否相同: " + (t1.getStudent() == p));
			System.out.println("t2的student引用和p是否相同: " + (t2.getStudent() == p));
			System.out.println("t2和t3是否是同一个对象: " + (t2 == t3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		writeTeacher();
	}
}
