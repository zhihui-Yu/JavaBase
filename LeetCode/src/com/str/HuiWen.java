package com.str;

import java.util.HashMap;
import java.util.Map;

/**
 * 	����һ���ַ�������дһ�������ж����Ƿ�Ϊĳ�����Ĵ�������֮һ��
 *
 *	���Ĵ���ָ������������һ���ĵ��ʻ���������ָ��ĸ���������С�
 *
 *	���Ĵ���һ�����ֵ䵱�еĵ��ʡ�
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
