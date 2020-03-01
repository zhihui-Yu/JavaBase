package com.base;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 特殊回文 123321是一个非常特殊的数，它从左边读和从右边读是一样的。 输入一个正整数n， 编程求所有这样的五位和六位十进制数，满足各位数字之和等于n。
 * 
 * 例： 输入 55 输出：899998 989989 998899
 * 
 * 输出顺序： 从小到大
 * 
 * @author listener
 *
 */
public class SpecialNumber {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int sum = sc.nextInt();

		sc.close();

		// 数字长度
		int len = 0;

		// 放数字的集合
		Set<Integer> set = new TreeSet<>();

		// 判断是不是回文
		boolean flag = false;

		// 保存回文的和
		int numSum = 0;

		for (int i = 10000; i < 1000000; i++) {

			String str = String.valueOf(i);

			len = str.length() - 1;

			// 判断数字是不是回文
			for (int j = 0; j < len - 1; j++) {
				// 首尾相等
				if (str.charAt(j) == str.charAt(len - j)) {
					flag = true;
				} else {
					// 有一次不相等就 退出
					flag = false;
					break;
				}
			}

			// 是回文还要判断数字相加是不是等于输入值
			if (flag) {

				// 清空
				numSum = 0;
				// 将所有数字相加
				for (int j = 0; j <= len; j++) {
					numSum += getInt(str.charAt(j));
				}
				
				//System.out.println(numSum);
				
				// 判断数字和是不是相等
				if (numSum != sum) {
					flag = false;
				}
			}
			//相等 即保存
			if (flag) {
				set.add(Integer.parseInt(str));
			}
		}
		// 输出
		for (Integer in : set) {
			System.out.println(in);
		}
	}

	public static int getInt(char c) {
		switch (c) {
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		default:
			return 0;
		}
	}
}
