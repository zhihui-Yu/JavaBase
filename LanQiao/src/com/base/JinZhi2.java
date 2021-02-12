package com.base;

import java.util.Scanner;

/**
 * 16 --> 10
 * <p>
 * 思路 ： 十六进制的每个数乘以相应的值如
 * (FF)10 = 15*16^0+15*16^1
 *
 * @author listener
 */
public class JinZhi2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String six = sc.next();

        sc.close();

        long sum = 0;

        for (int i = 0; i < six.length(); i++) {
            switch (six.charAt(six.length() - i - 1)) {
                case '0':
                    sum += Math.pow(16, i) * 0;
                    break;
                case '1':
                    sum += Math.pow(16, i) * 1;
                    break;
                case '2':
                    sum += Math.pow(16, i) * 2;
                    break;
                case '3':
                    sum += Math.pow(16, i) * 3;
                    break;
                case '4':
                    sum += Math.pow(16, i) * 4;
                    break;
                case '5':
                    sum += Math.pow(16, i) * 5;
                    break;
                case '6':
                    sum += Math.pow(16, i) * 6;
                    break;
                case '7':
                    sum += Math.pow(16, i) * 7;
                    break;
                case '8':
                    sum += Math.pow(16, i) * 8;
                    break;
                case '9':
                    sum += Math.pow(16, i) * 9;
                    break;
                case 'A':
                    sum += Math.pow(16, i) * 10;
                    break;
                case 'B':
                    sum += Math.pow(16, i) * 11;
                    break;
                case 'C':
                    sum += Math.pow(16, i) * 12;
                    break;
                case 'D':
                    sum += Math.pow(16, i) * 13;
                    break;
                case 'E':
                    sum += Math.pow(16, i) * 14;
                    break;
                case 'F':
                    sum += Math.pow(16, i) * 15;
                    break;
                default:
                    break;
            }
        }
        System.out.println(sum);
    }
}