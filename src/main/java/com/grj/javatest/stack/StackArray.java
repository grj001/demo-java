package com.grj.javatest.stack;

public class StackArray {
	private int top = -1;
	private Object[] objs;

	/**
	 * 初始化栈数组
	 * @param capacity
	 * @throws Exception
	 */
	public StackArray(int capacity) throws Exception{  
	        if(capacity < 0)  
	            throw new Exception("Illegal capacity:"+capacity);  
	        objs = new Object[capacity];  
	    }

	public void push(Object obj) throws Exception {
		//原来top是-1,objs.length - 1=1
		//0,1 放了一个元素
		//1,1 放了一个元素,有两个元素了
		//1,1 栈的元素满了
		if (top == objs.length - 1)
			throw new Exception("Stack is full!");
		objs[++top] = obj;
	}

	/**
	 * 从栈中拿去顶部元素,并不是真的拿走了,而是把顶部元素的索引更新了
	 * @return
	 * @throws Exception
	 */
	public Object pop() throws Exception {
		if (top == -1)
			throw new Exception("Stack is empty!");
		return objs[top--];
	}

	public void dispaly() {
		System.out.print("bottom -> top: | ");
		for (int i = 0; i <= top; i++) {
			System.out.print(objs[i] + " | ");
		}
		System.out.print("\n");
	}

	public static void main(String[] args) throws Exception {
		StackArray s = new StackArray(2);
		s.push(1);
		s.push(2);
		s.dispaly();
		System.out.println(s.pop());
		s.dispaly();
		s.push(99);
		s.dispaly();
		s.push(99);
	}
}
