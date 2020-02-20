package com.str;

public class Str {
	public static void main(String[] args) {
		System.out.println(strStr( "aaabca",  "bc"));
	}

	public static int strStr(String haystack, String needle) {
		int len1 = haystack.length();
		int len2 = needle.length();
		if(len2 > len1 ){
			return -1;
		}
		if(len2 == 0) {
			return 0;
		}
		//比较次数
		int len = len1 - len2 + 1;
		int k = 0,l = 0;
		int count = 0;
		for (;len > 0;){
			if(k == len2) {
				return count;
			}
			if(needle.charAt(k++)==haystack.charAt(count+l++)){
				continue;
			} else {
				k = 0;
				len--;
				l = 0;
				count += 1;
			}
		}
		return -1;
	}
}
