package com.lanqiao;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 问题描述 给定三个整数 a, b, c，如果一个整数既不是 a 的整数倍也不是 b 的整数倍还不是 c 的整数倍，则这个数称为反倍数。 请问在 1 至 n
 * 中有多少个反倍数。
 *
 * @author listener
 */
public class FanBeiShu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        sc.close();

        TreeSet<Integer> ts = new TreeSet<>();

        while (n-- > 0) {
            if (n % a != 0 && n % b != 0 && n % c != 0) {
                ts.add(n);
            }
        }
        /*
         * for (Integer integer : ts) { System.out.println(integer); }
         */
        System.out.println(ts.size());
    }
}
