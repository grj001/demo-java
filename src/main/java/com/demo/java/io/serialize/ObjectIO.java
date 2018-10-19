package com.demo.java.io.serialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO {
	public static void writeObject() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src/main/java/com/grj/javatest/io/filestream/serialize/obj.txt"))) {
			Person per = new Person("孙悟空", 500);
			oos.writeObject(per);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/java/com/grj/javatest/io/filestream/serialize/obj.txt"))) {
			Person p = (Person) ois.readObject();
			System.out.println("name: " + p.getName() + ", age: " + p.getAge());
		}
	}

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		// writeObject();
		// 不会调用构造器
		readObject();
	}
}
