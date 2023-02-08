package com.lanqiao;

import java.util.Scanner;

/**
 * 问题描述 给定一个单词，请使用凯撒密码将这个单词加密。
 * 凯撒密码是一种替换加密的技术，单词中的所有字母都在字母表上向后偏移3位后被替换成密文。即a变为d，b变为e，...，w变为z，x变为a，y变为b，
 * z变为c。 例如，lanqiao会变成odqtldr。
 *
 * @author listener
 */
public class KaiSaJiaMi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        sc.close();

        StringBuilder ans = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c < 'x' && c >= 'a') {
                c = (char) (c + 3);
                ans.append(c);
            }
            if (c >= 'x' && c <= 'z') {
                c = (char) ('a' + 3 - ('z' - c));
                ans.append(c);
            }
            if (c < 'X' && c >= 'A') {
                ans.append((char) c + 3);
            }
            if (c >= 'X' && c <= 'Z') {
                ans.append((char) 'A' + 3 - ('Z' - c));
            }
        }
        System.out.println(ans.toString());
    }
}
