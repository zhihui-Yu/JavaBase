package com.sort;

public class GuiBing {
	/**
	 *  归并： 将数组分为两个等长的子数组，一直分
	 *  直到每个数组中只有一个数，最后合并
	 *  左边的第一个和右边的第一个比较 小的放入temp数组的第一个
	 *  然后小的第二个再个另一个第一个比较，小的移过去，这就是合并
	 * @param a
	 * @param start
	 * @param end
	 */
	public static void sort(int[] arr, int start, int end) {
		int mid ;
		if( start < end) {
			//划分子数组
			mid = (start+end)/2;
			//左边子数组再划分
			sort(arr, start, mid);
			//右边子数组再划分
			sort(arr, mid+1, end);
			//归并
			merge(arr, start, mid, end);
		}
		
	}
	
	public static void merge(int[] arr, int start, int mid, int end) {
		//初始化数组来存放合并的数据
		int[] temp = new int[end-start+1];
		//左边数据的起始点
		int left = start;
		//右边数据的起始点
		int right = mid+1;
		//temp数组索引
		int i = 0;
		//左右边的数组已经排序完成，合并
		while (left <= mid && right <= end) {
			//如果在范围内 且 数小的则放入数组
			if (arr[left] < arr[right]) {
				temp[i++] = arr[left++];
			} else {
				temp[i++] = arr[right++];
			}
		}
		//循环结束后 将剩余数据放入数组
		while (left <= mid) {
			temp[i++] = arr[left++];
		}
		while (right <= end) {
			temp[i++] = arr[right++];
		}
		
		//将数组覆盖原来的数组
		for (int j = 0; j < temp.length; j++) {
			arr[start+j] = temp[j];
		}
	}
}
