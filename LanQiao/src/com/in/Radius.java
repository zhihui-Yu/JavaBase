package com.in;

import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * ����Բ�İ뾶�������������С�������λ
 * 
 * @author listener
 *
 */
public class Radius {
	public static void main(String[] args) {
		double PI=3.14159265358979323;
		Scanner sc = new Scanner(System.in);
		int radius = sc.nextInt();
		sc.close();
		PI = PI*radius*radius;
		DecimalFormat df = new DecimalFormat("#.0000000");
		System.out.println(df.format(PI));
	}
}
