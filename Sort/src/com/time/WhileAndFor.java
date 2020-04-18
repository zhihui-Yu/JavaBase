package com.time;

public class WhileAndFor {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int i = 0;
		while(i < 2100000000){
			i++;
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		start = System.currentTimeMillis();
		for (int j = i; j < 2100000000; j++) {
			
		}
		end = System.currentTimeMillis();
		System.out.println(end-start);
	}
}
