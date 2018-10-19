package com.demo.java.buffer;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;


public class ByteBufferDemo {
	
	
	@Test
	public void test01(){
		ByteBuffer result = ByteBuffer.allocate(16);
		result.put(Bytes.toBytes(1));
		result.put(Bytes.toBytes(Long.MAX_VALUE - new Date().getTime()));
		result.put(Bytes.toBytes(22));
		System.out.println(Arrays.toString(result.array()));
	}
	
	
	public static void main(String args[]) throws FileNotFoundException {  
		  
	    System.out.println("----------Test allocate--------");  
	    System.out.println("before alocate:"  
	            + Runtime.getRuntime().freeMemory());  
	      
	    // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？  
	    // 要超过多少内存大小JVM才能感觉到？ 
	    // 从堆空间中分配一个容量大小为capacity的byte数组作为缓冲区的byte数据存储器
	    ByteBuffer buffer = ByteBuffer.allocate(102400);  
	    System.out.println("buffer = " + buffer);  
	      
	    System.out.println("after alocate:"
	            + Runtime.getRuntime().freeMemory());  
	      
	    // 这部分直接用的系统内存，所以对JVM的内存没有影响  
	    /*
	     * 是不使用JVM堆栈而是通过操作系统来创建内存块用作缓冲区，
	     * 它与当前操作系统能够更好的耦合，因此能进一步提高I/O操作速度。
	     * 但是分配直接缓冲区的系统开销很大，因此只有在缓冲区较大并长期存在，
	     * 或者需要经常重用时，才使用这种缓冲区.
	     */
	    ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);  
	    System.out.println("directBuffer = " + directBuffer);  
	    System.out.println("after direct alocate:"  
	            + Runtime.getRuntime().freeMemory());  
	      
	    System.out.println("----------Test wrap--------");  
	    byte[] bytes = new byte[32]; 
	    /*
	     * 这个缓冲区的数据会存放在byte数组中，bytes数组或buff缓冲区任何一方中数据的改动都会影响另一方。
	     * 其实ByteBuffer底层本来就有一个bytes数组负责来保存buffer缓冲区中的数据，
	     * 通过allocate方法系统会帮你构造一个byte数组
	     */
	    buffer = ByteBuffer.wrap(bytes);
	    System.out.println(buffer);  
	     
	    /*
	     * 在上一个方法的基础上可以指定偏移量和长度，这个offset也就是包装后byteBuffer的position，
	     * 而length呢就是limit-position的大小，
	     * 从而我们可以得到limit的位置为length+position(offset)
	     */
	    buffer = ByteBuffer.wrap(bytes, 10, 10);  
	    System.out.println(buffer);   
	}  
}
