package com.base;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给定一个长度为n的数列，将这个数列按从小到大的顺序排列。1<=n<=200
 * @author listener
 *
 */
public class SortArr {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//获取数组长度
		int n = sc.nextInt();
		//创建数组
		int[] arr = new int[n];
		//将输入的数据插入数组
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		//关闭流
		sc.close();
		//Arrays.sort(arr);
		sort(arr, 0, arr.length-1);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	//快排
	// 6 2 3 1
	public static void sort(int[] arr,int start, int end){
		int top = start;
		int last = end;
		
		//如果前面的指针大于后面，说明结束了
		if(start > end) {
			return;
		}
		//基数
		int temp = arr[start];
		
		//没有结束  改变指针的位置
		while (start < end) {
			//从前往后遍历 升序
			while (start < end && temp < arr[end]){
				end = end - 1;
			}
			//循环结束，表示找完了
			//在范围内的话，就说明有交换出现
			if(start < end ){
				arr[start] = arr[end];
				start = start + 1;
			}
			
			//从后往前找
			while (start < end && temp > arr[start]) {
				start ++;
			}
			//如果有交换
			if(start < end ){
				arr[end] = arr[start];
				end --;
			}
		}
		arr[end] = temp;
		//第一个数交换完 遍历其他的
		sort(arr, top, start-1);
		sort(arr, start+1, last);
	}
}
