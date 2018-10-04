package com.java.demo.io.serialize.deserializeAclass;

import java.io.Serializable;

/*反序列化兼容—— class版本
 * 
 * 在反序列化时必须提供class文件，
 * 但class文件随着程序升级也会有不同的版本，
 * 但很多情况下需要保证两个class版本序列化能兼容，该如何做到呢？
 * 
 * 
 * Java的序列化机制允许定义一个version值，
 * 只要升级前后两个class的version值一样，
 * 就看作同一个版本，序列化前后能兼容。
 * 
 * 定义如下，
 * 
 * */



public class Test implements Serializable {

 private static final long serialVersionUID = 512L;

}
 
/*通常建议在每个类中都加入这个变量，
 * 这样在在反序列化时，即使提供的class文件版本已经跟序列化时的class文件有所改动，
 * 但只要这个version变量不变，就可以反序列化。
 * 
 * 
 * 如果不显式定义version变量，JVM会根据类信息计算出一个（通常会变化），
 * 从而导致反序列化因为类版本不同而失败。
 * 
 * 
 * 通过JDK下的serialver.exe 可以获取该类的serverUID
 * 
 * 
 * 命令： serialver Person
 * 
 * 输出结果： Person: static final long serialVersionUID = -2595800114629327570L;
 * */


