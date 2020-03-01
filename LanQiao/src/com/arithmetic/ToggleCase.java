package com.arithmetic;

import java.util.Scanner;

/**
 * 大小写互换
 * 
 * 大写：65 - 90
 * 小写：97 - 121
 * 
 * @author listener
 *
 */
public class ToggleCase {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String str = sc.next();

		sc.close();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			//小写
			if(str.charAt(i)+1 > 65 && str.charAt(i)+1 < 91){
				sb.append((char)(str.charAt(i) + 32));
			}
			//大写
			if(str.charAt(i)+1 > 97 && str.charAt(i)+1 < 121){
				sb.append((char)(str.charAt(i) - 32));
			}
		}
		System.out.println(sb.toString());
	}
}
