package com.test;

import java.util.Arrays;
import java.util.Random;

import com.sort.ChaRu;
import com.sort.GuiBing;
import com.sort.KuaiPai;
import com.sort.MaoPao;
import com.sort.XuanZe;

public class Main {
	//数组大小
	static int size = 100000;
	//运行开始时间
	static long start;
	//运行结束时间
	static long end;
	//初始化数组
	static int[] arr;
	static String str = "aaa";
	//各种排序的时间(ms)
	public static void main(String[] args) {
		//Arrays.sort 默认使用快排，但是会根据数据的长度在使用升级版快排 dualpivotquicksort
		System.out.println("-----------Arrays.sort-------------------");
		giveNum(size);
		start = System.currentTimeMillis();
		Arrays.sort(arr);
		end = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println("-----------归并---------------------------");
		giveNum(size);
		start = System.currentTimeMillis();
		GuiBing.sort(arr, 0, arr.length-1);
		end = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println("-----------快排---------------------------");
		giveNum(size);
		start = System.currentTimeMillis();
		KuaiPai.sort(arr, 0, arr.length-1);
		end = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println("-----------选择---------------------------");
		giveNum(size);
		System.out.println(XuanZe.sort(arr, start, end));
		System.out.println("-----------冒泡---------------------------");
		giveNum(size);
		System.out.println(MaoPao.sort(arr, start, end));
		System.out.println("-----------插入---------------------------");
		giveNum(size);
		System.out.println(ChaRu.sort(arr, start, end));
		
	}
	// 赋值
	public static void giveNum(int size) {
		arr = new int[size];
		Random random = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(100);
		}
	}
}
