package com.in;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 输入圆的半径，求面积。保留小数点后七位
 *
 * @author listener
 */
public class Radius {
    public static void main(String[] args) {
        double PI = 3.14159265358979323;
        Scanner sc = new Scanner(System.in);
        int radius = sc.nextInt();
        sc.close();
        PI = PI * radius * radius;
        DecimalFormat df = new DecimalFormat("#.0000000");
        System.out.println(df.format(PI));
    }
}
