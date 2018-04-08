package com.grj.javatest.io.serialize.userdefine.readresolve;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

import com.grj.javatest.path.GetCurrentPath;

class OrientationII implements java.io.Serializable {
    public static final OrientationII HORIZONTAL = new OrientationII(1);
    public static final OrientationII VERTICAL = new OrientationII(2);
    private int value;
    public OrientationII(int value) {
        this.setValue(value);
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    private Object readResolve() throws ObjectStreamException {
        if (value==1) {
            return HORIZONTAL;
        }
        if (value==2) {
            return VERTICAL;
        }
        return null;
    }
}

public class ReadResolveTestOverWrite{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GetCurrentPath.getCurrentPath(new ReadResolveTestOverWrite().getClass())+"readResolve.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GetCurrentPath.getCurrentPath(new ReadResolveTestOverWrite().getClass())+"readResolve.txt"))) {
            
            oos.writeObject(OrientationII.HORIZONTAL);

            //紧接着readObject()，将会调用重写的readResolve()，并取代readObject
            //我们在readResolve()中返回的是单例，这就解决对象序列化破坏单例设计模型的问题了
            OrientationII ori = (OrientationII)ois.readObject();
            System.out.println(ori==OrientationII.HORIZONTAL);
        }
    }
}