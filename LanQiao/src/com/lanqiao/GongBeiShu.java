package com.lanqiao;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题描述 小张是软件项目经理，他带领3个开发组。工期紧，今天都在加班呢。为鼓舞士气，小张打算给每个组发一袋核桃（据传言能补脑）。他的要求是：
 * 
 * 1. 各组的核桃数量必须相同
 * 
 * 2. 各组内必须能平分核桃（当然是不能打碎的）
 * 
 * 3. 尽量提供满足1,2条件的最小数量（节约闹革命嘛）
 * 
 * 输入格式 输入包含三个正整数a, b, c，表示每个组正在加班的人数，用空格分开（a,b,c<30） 输出格式 输出一个正整数，表示每袋核桃的数量。
 * 
 * @author listener
 *
 */
public class GongBeiShu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		int d1 = division(a, b);
		int d2 = division(a*b/d1, c);
		
		System.out.println((a*b*c/d1) /d2);
		
	}
	//辗转相除法
	public static int division(int a,int b) {
		int temp;
		while(a % b!=0) {//直到余数为0 ，最大公约数为上一步的余数
			temp= a%b;
			a = b;
			b = temp;
		}
		return b;
	}
}
