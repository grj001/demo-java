package com.java.demo.io.serialize.objreference;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 可变对象序列化
由于Java对象只会序列化一次，
当可变对象在序列化之后发生了变化，
即使再进行一次序列化，也不能改变序列化对象的值，
反序列化之后依然是第一次序列化的值，例如下面的例子，
*/

public class MutableSerialize {
	public static void mutable() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"src/main/java/com/grj/javatest/io/filestream/serialize/objreference/mutable.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
						"src/main/java/com/grj/javatest/io/filestream/serialize/objreference/mutable.txt"))) {
			Person per = new Person("孙悟空", 500);
			oos.writeObject(per);
			per.setName("猪八戒");
			// per在前面已经序列化过，这里再序列化不会生效
			oos.writeObject(per);
			Person p1 = (Person) ois.readObject();
			Person p2 = (Person) ois.readObject();
			System.out.println(p1 == p2);
			System.out.println(p2.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		mutable();
	}
}
