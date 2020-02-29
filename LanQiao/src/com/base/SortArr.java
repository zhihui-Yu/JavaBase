package com.base;

import java.util.Arrays;
import java.util.Scanner;

/**
 * ����һ������Ϊn�����У���������а���С�����˳�����С�1<=n<=200
 * @author listener
 *
 */
public class SortArr {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//��ȡ���鳤��
		int n = sc.nextInt();
		//��������
		int[] arr = new int[n];
		//����������ݲ�������
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		//�ر���
		sc.close();
		//Arrays.sort(arr);
		sort(arr, 0, arr.length-1);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
	//����
	// 6 2 3 1
	public static void sort(int[] arr,int start, int end){
		int top = start;
		int last = end;
		
		//���ǰ���ָ����ں��棬˵��������
		if(start > end) {
			return;
		}
		//����
		int temp = arr[start];
		
		//û�н���  �ı�ָ���λ��
		while (start < end) {
			//��ǰ������� ����
			while (start < end && temp < arr[end]){
				end = end - 1;
			}
			//ѭ����������ʾ������
			//�ڷ�Χ�ڵĻ�����˵���н�������
			if(start < end ){
				arr[start] = arr[end];
				start = start + 1;
			}
			
			//�Ӻ���ǰ��
			while (start < end && temp > arr[start]) {
				start ++;
			}
			//����н���
			if(start < end ){
				arr[end] = arr[start];
				end --;
			}
		}
		arr[end] = temp;
		//��һ���������� ����������
		sort(arr, top, start-1);
		sort(arr, start+1, last);
	}
}
