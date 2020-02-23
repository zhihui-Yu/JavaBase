package com.fib;

import java.util.Arrays;

/**
 * 寻找最长的fib数列
 * @author listener
 *
 */
public class FibTest {
	public static void main(String[] args) {
		int[] A = {1,3,7,11,12,14,18};
		FibTest fib = new FibTest();
		
		System.out.println(fib.lenLongestFibSubseq(A));
	} 
	public int lenLongestFibSubseq(int[] A) {
		int tem1 = 0;
		int tem2 = 0;
		int max = 2;
		int sum = 0;
		int len = 0;
		//a[i] i < n-2
		for (int i = 0; i < A.length-2; i++) {
			//a[j] i+1 < n-1
			for (int j = i+1; j < A.length-1; j++) {
				tem1 = A[i];
				tem2 = A[j];
				sum = tem1 + tem2;
				len = 2;
				//寻找与sum匹配的数
				while (Arrays.binarySearch(A, sum) > 0) {
					tem1 = tem2;
					tem2 = sum;
					sum = tem1 + tem2;
					len++;
				}
				max = max >= len ? max : len;
			}
		}
		return max < 3 ? 0 : max;
    }
}
