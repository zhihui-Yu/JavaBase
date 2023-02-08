package com.str;

/**
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 *
 * @author listener
 */
public class UrlTest {
    public static void main(String[] args) {
        System.out.println(replaceSpaces("nwmog q k  gW  c    H  DYpIE    Lcz         gV    Bj   vkH X g       l   ", 72));
    }

    public static String replaceSpaces(String S, int length) {

        String[] str = S.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
