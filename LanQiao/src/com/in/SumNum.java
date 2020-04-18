package com.in;

import java.util.Scanner;
/**
 * A + B
 * @author listener
 *
 */
public class SumNum {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Integer a = sc.nextInt();
		Integer b = sc.nextInt();
		sc.close();
		System.out.println(a + b);
	}
}
