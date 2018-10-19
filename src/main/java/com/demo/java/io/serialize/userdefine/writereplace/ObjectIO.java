package com.demo.java.io.serialize.userdefine.writereplace;


import com.demo.java.path.GetCurrentPath;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;


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
        this.name = ((StringBuilder)ois.readObject()).reverse().toString();
        this.age = ois.readInt();
    }
    
    private Object writeReplace() throws ObjectStreamException {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(age);
        return list;
    }
}


//Java在序列化某个对象之前，将先调用writeReplace(), writeReplace()方法的返回值将用来替换实际将被序列化的对象，例如下面，
public class ObjectIO {
    
    public static void writeReplaceTest() throws FileNotFoundException, IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GetCurrentPath.getCurrentPath(new ObjectIO().getClass())+"writeReplace.txt"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GetCurrentPath.getCurrentPath(new ObjectIO().getClass())+"writeReplace.txt"))) {
            Person per = new Person("孙悟空", 500);
            oos.writeObject(per);
            ArrayList list = (ArrayList)ois.readObject();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
        writeReplaceTest();
    }
}