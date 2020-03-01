package com.str;

import java.util.HashMap;
import java.util.Map;

/**
 * 	给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *
 *	回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *
 *	回文串不一定是字典当中的单词。
 *
 * @author listener
 *
 */
public class HuiWen {
	public static void main(String[] args) {
		System.out.println(canPermutePalindrome("aa"));
	}
	  public static boolean canPermutePalindrome(String s) {
	        int count = 0;
	        char temp = ' ';
	        Map<Character,Integer> map = new HashMap<>();
	        for(int i = 0; i < s.length(); i++){
	        	temp = s.charAt(i);
	            if(map.containsKey(temp)) {
	                 map.put(temp,map.get(temp)+1);
	            } else {
	            	map.put(s.charAt(i),1);
	            }
	        }
	        for (Integer value : map.values()) {
				if(value % 2 == 1){
					count ++;
				}
			}
	        return count==1 || count==0;
	    }
}
