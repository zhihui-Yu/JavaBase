package com.arithmeticEnhance;

import java.util.Scanner;

/**
 * 10 --> 8
 * 
 * 12/8 = 1 ... 4
 * 4/8  = 0 ... 4
 * 
 * ���ű����������⣬��Ҫ��ÿ���õ��İ˽�����ǰ�涼�м�"0",����0
 * 
 * @author listener
 *
 */
public class TenToEight {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		sc.close();
		
		String eight = tenToEight(n);
		if(Integer.parseInt(eight) == 0){
		}
		if(eight.length()%3 == 1){
			eight = "00"+eight;
		}else if(eight.length()%3 == 2){
			eight = "0"+eight;
		}
		
		System.out.println(eight);
		
	}
	
	public static String tenToEight(int n){
		
		StringBuilder sb = new StringBuilder();
		if(n > 8){
			int i = 0;
			while(n > 8) {
				//��
				i = n/8;
				
				//����
				n = n - 8*i;
				
				sb.append(n);
				
				n = i;
				
			}
			sb.append(i).reverse();
			return sb.toString();
		} else {
			
			return sb.append(n).toString();
		}
	}
}
