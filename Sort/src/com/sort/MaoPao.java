package com.sort;

public class MaoPao {
	/**
	 * 冒泡排序：a[i] > a[i+1] 则a[i]与a[i+1]的数据交换，否则不变
	 * 			每一轮都会选择出一个最值，有i个数字则有i-1轮比较
	 * @param a 传入需要排序的数组
	 * @return
	 */
	public static long sort(int[] a, long start, long end) {
		start = System.currentTimeMillis();
		for (int i = 0; i < a.length; i++) { // 第一层 要比较的轮数
			for (int j = 0; j < a.length - i - 1; j++) { // 第二层 每个数比较几次
				if (a[j] > a[j + 1]) { 					 // 自己不用和自己比 所以减一
					int temp = a[j]; 					 // 每一轮比较完都有一个最值出现，所以减去i
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		end = System.currentTimeMillis();
		return (end - start);
	}
}
