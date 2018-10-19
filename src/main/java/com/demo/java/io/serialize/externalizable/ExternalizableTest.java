package com.demo.java.io.serialize.externalizable;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 这种方式与实现Serializable接口方式基本相同，
 * 但是Externalizable必须要在目标类中手工实现两个方法：writeExternal 用来实现序列化，readExternal用来实现反序列化。 
 * 且目标类必须包含一个默认构造函数(无参).
 * 
 * 
 * 下面是一个例子，注意在writeExternal和readExternal使用的是
 * ObjectOutput和ObjectInput而不是ObjectInputStream和ObjectOutputStream.
*/

class People implements java.io.Externalizable {
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
    
    public People(String name, int age) {
        System.out.println("有参数构造器");
        this.name = name;
        this.age = age;
    }
    
    public People() {
        System.out.println("无参数构造器");
    }
    
    @Override
    public void writeExternal(java.io.ObjectOutput out) throws IOException {
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }
    
    @Override
    public void readExternal(java.io.ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer)in.readObject()).reverse().toString();
        this.age = in.readInt();
    }
}

public class ExternalizableTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("external.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("external.txt"))) {
            People peo = new People("孙悟空" , 500);
            oos.writeObject(peo);
            
            People p = (People)ois.readObject();
            System.out.println(p.getName()+" : "+p.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
