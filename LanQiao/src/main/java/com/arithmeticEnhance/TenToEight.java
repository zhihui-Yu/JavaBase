package com.arithmeticEnhance;

import java.util.Scanner;

/**
 * 10 --> 8
 * <p>
 * 12/8 = 1 ... 4
 * 4/8  = 0 ... 4
 * <p>
 * 蓝桥杯这题有问题，他要求每个得到的八进制数前面都有加"0",除了0
 *
 * @author listener
 */
public class TenToEight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        sc.close();

        String eight = tenToEight(n);
        if (Integer.parseInt(eight) == 0) {
        }
        if (eight.length() % 3 == 1) {
            eight = "00" + eight;
        } else if (eight.length() % 3 == 2) {
            eight = "0" + eight;
        }

        System.out.println(eight);

    }

    public static String tenToEight(int n) {

        StringBuilder sb = new StringBuilder();
        if (n > 8) {
            int i = 0;
            while (n > 8) {
                //商
                i = n / 8;

                //余数
                n = n - 8 * i;

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
