package com.base;

import java.util.Scanner;

/**
 * 10 --> 16
 * <p>
 * 256/16 = 16 ... 0 16/16 = 1 .....0 1/16 = 0......1
 *
 * @author listener
 */
public class JinZhi3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int six = sc.nextInt();

        sc.close();

        StringBuilder sb = new StringBuilder();

        if (six == 0) {
            System.out.println(0);
            return;
        }

        // 余数
        int temp = 0;
        //商
        int l = 0;

        while (six > 16) {

            //商
            l = six / 16;

            //余数
            temp = six - l * 16;

            // 右移四位
            six = l;

            // 判断字符
            if (temp < 10) {
                sb.append(temp);
            } else {
                sb.append(getNum(temp));
            }
        }
        //获取最后的数  ps：可能上面循环没走，所以l可能是0
        l = six % 16;

        //添加最后的数字
        sb.append(l < 10 ? l : getNum(l));

        //如果刚好整除  需要在末尾加1
        if (l == 0) {
            sb.append("1");
        }
        //反向输出
        System.out.println(sb.reverse().toString());

    }

    public static String getNum(int temp) {
        switch (temp) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return "";
        }
    }
}
