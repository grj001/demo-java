package com.grj.javatest.io.serialize.userdefine.readresolve;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.grj.javatest.path.GetCurrentPath;
/*
 * 与writeReplace对应的是readResolve方法，
 * 
 * 它将紧接着readObject调用，readObject的结果将被直接丢弃，
 * readResolve的返回值将替代readObject的返回值。
 * 
 * 对象序列化会破坏单例设计，
 * 因为在反序列化过程中，将会生成一个新的对象并用原来序列化时候的值来初始化，
 * 也就是反序列化和序列化两个对象不是同一个对象。
 * 但是对于单例类，
 * 例如枚举类，如果反序列化得到的对象不是序列化之前的对象，
 * 这将违背单例类的原则，因此我们需要用一个特殊的反序列方法readResolve().
*/
/*
 * 值得注意的是，readResolve()跟writeReplace()一样可以使用任意访问控制符，
 * 这样子类就有可能继承父类的readResolve()方法。
 * 这将会有一个问题，如果父类包含一个protected或public的readResolve()方法时，
 * 
 * 如果子类不重写该方法，在反序列化时将会得到一个父类对象，这显然不是程序想要的结果，而且不易发现。
 * 但是总是在子类中重写这个方法也挺麻烦，无疑增加了不必要的工作量，而且还容易忽视这前面的潜在问题。
 * 因此如果一个类将成为父类的话，
 * 要么这个类是final类，则可以用任意访问权限修饰它的readResolve()方法； 
 * 要么将它的readResolve()方法最好是定义为private的。
*/

/*
 * 执行结果发现ori与Orientation.HORIZONTAL不相等，
 * 因为反序列化是生成新对象的过程（其实是一个拷贝对象的过程，因为这里的类初始化方法是private的）。
 * 为了解决这个问题，我们在目标类中重写readResolve()方法，
*/
class Orientation implements java.io.Serializable {
    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);
    private int value;
    private Orientation(int value) {
        this.value = value;
    }
}

public class ReadResolveTest {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(GetCurrentPath.getCurrentPath(new ReadResolveTest().getClass())+"readResolve.txt"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(GetCurrentPath.getCurrentPath(new ReadResolveTest().getClass())+"readResolve.txt"))) {
            oos.writeObject(Orientation.HORIZONTAL);
            Orientation ori = (Orientation)ois.readObject();
            System.out.println(ori == Orientation.HORIZONTAL);
        }
    }
}
