package com.in;

import java.util.Scanner;
/**
 * ��1+2+3+...+n��ֵ��
 * ������Ϊ 1000000000ʱ��int��sum����ᳬ����Χ
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
