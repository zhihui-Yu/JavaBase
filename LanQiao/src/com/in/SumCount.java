package com.in;

import java.util.Scanner;
/**
 * 求1+2+3+...+n的值。
 * 当数字为 1000000000时，int的sum结果会超过范围
 * 
 * @author listener
 *
 */
public class SumCount {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		sc.close();
		long sum = 0;
		for(int i = 1; i <= count; i++ ){
			sum += i;
		}
		System.out.println(sum);
	}
}
