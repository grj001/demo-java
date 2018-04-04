package com.grj.javatest.arrayoperation;

import java.util.Arrays;

import java.util.Scanner;

/*
	删除数组中的某一个元素
	运行结果：
	[1, 2, 4, 5, 9, 8, 0]
	请输入要删除第几个元素：
	3
	[1, 2, 0, 5, 9, 8]
 * 
 * */
public class MoveOneEleOutFromArray {

	public static void main(String[] args) {
		// 删除数组中的某一个元素的方法：
		// 把最后一个元素替代指定的元素，然后数组缩容
		Scanner sc = new Scanner(System.in);

		int[] arr = new int[] { 1, 2, 4, 5, 9, 8, 0 };

		System.out.println(Arrays.toString(arr));

		System.out.println("请输入要删除第几个元素：");
		int n = sc.nextInt();
		sc.close();

		// 把最后一个元素替代指定的元素
		arr[n - 1] = arr[arr.length - 1];

		// 数组缩容
		arr = Arrays.copyOf(arr, arr.length - 1);

		System.out.println(Arrays.toString(arr));
	}

}
