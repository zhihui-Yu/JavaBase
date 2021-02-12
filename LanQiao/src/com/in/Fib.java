package com.in;

import java.util.Scanner;

/**
 * Fibonacci数列的递推公式为：Fn=Fn-1+Fn-2，其中F1=F2=1。
 * <p>
 * 当n比较大时，Fn也非常大，现在我们想知道，Fn除以10007的余数是多少。
 *
 * @author listener
 */
public class Fib {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        if (n < 3) {
            System.out.println(1);
        }
        if (n == 3) {
            System.out.println(2);
        }
        if (n > 3) {
            int f1 = 1, f2 = 1, f3 = 2;
            for (int i = 3; i < n; i++) {
                f1 = f2;
                f2 = f3;
                f3 = (f1 + f2) % 10007;
            }
            System.out.println(f3);
        }
    }
}
