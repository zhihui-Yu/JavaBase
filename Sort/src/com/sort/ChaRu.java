package com.sort;

public class ChaRu {
	/**
	 * 
	 * 需要查入的数与前一个数比较，
	 * 大于则不动，小于则与前一个数互换，然后再与前一个数比较
	 * 如下去一直到大于前一个数的位置
	 * 
	 * @param a     传入的需排序的数组
	 * @param start 开始时间
	 * @param end   运行结束时间
	 * @return
	 */
	public static long sort(int[] arr, long start, long end) {
		start = System.currentTimeMillis();
		
		//比较次数为数组长度-1(第一个数不用比较)
		for(int i = 0; i < arr.length - 1; i++){
			//获取当前比较数的位置
			int j = i;
			//获取需要插入的数
			int x = arr[j+1];
			//循环比较 如果 后者小于前者 则换位置
			while (j >= 0 && arr[j] > x){
				//换位置 将大的值放后面
				arr[j+1] = arr[j];
				//继续与前一个数比较
				j--;
			}
			//比较完 将值放入前一个位置
			arr[j+1] = x;
		}
		
		
		end = System.currentTimeMillis();
		return end - start;
	}

}
