package com.base;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * ������� 123321��һ���ǳ����������������߶��ʹ��ұ߶���һ���ġ� ����һ��������n�� �����������������λ����λʮ�������������λ����֮�͵���n��
 * 
 * ���� ���� 55 �����899998 989989 998899
 * 
 * ���˳�� ��С����
 * 
 * @author listener
 *
 */
public class SpecialNumber {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int sum = sc.nextInt();

		sc.close();

		// ���ֳ���
		int len = 0;

		// �����ֵļ���
		Set<Integer> set = new TreeSet<>();

		// �ж��ǲ��ǻ���
		boolean flag = false;

		// ������ĵĺ�
		int numSum = 0;

		for (int i = 10000; i < 1000000; i++) {

			String str = String.valueOf(i);

			len = str.length() - 1;

			// �ж������ǲ��ǻ���
			for (int j = 0; j < len - 1; j++) {
				// ��β���
				if (str.charAt(j) == str.charAt(len - j)) {
					flag = true;
				} else {
					// ��һ�β���Ⱦ� �˳�
					flag = false;
					break;
				}
			}

			// �ǻ��Ļ�Ҫ�ж���������ǲ��ǵ�������ֵ
			if (flag) {

				// ���
				numSum = 0;
				// �������������
				for (int j = 0; j <= len; j++) {
					numSum += getInt(str.charAt(j));
				}
				
				//System.out.println(numSum);
				
				// �ж����ֺ��ǲ������
				if (numSum != sum) {
					flag = false;
				}
			}
			//��� ������
			if (flag) {
				set.add(Integer.parseInt(str));
			}
		}
		// ���
		for (Integer in : set) {
			System.out.println(in);
		}
	}

	public static int getInt(char c) {
		switch (c) {
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		default:
			return 0;
		}
	}
}
