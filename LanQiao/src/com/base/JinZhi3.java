package com.base;

import java.util.Scanner;

/**
 * 10 --> 16
 * 
 * 256/16 = 16 ... 0 16/16 = 1 .....0 1/16 = 0......1
 * 
 * 
 * @author listener
 *
 */
public class JinZhi3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int six = sc.nextInt();

		sc.close();

		StringBuilder sb = new StringBuilder();
		
		if(six == 0){
			System.out.println(0);
			return ;
		}
		
		// ����
		int temp = 0;
		//��
		int l = 0;
		
		while (six > 16) {
			
			//��
			l = six / 16;

			//����
			temp = six - l*16;

			// ������λ
			six = l;

			// �ж��ַ�
			if (temp < 10) {
				sb.append(temp);
			} else {
				sb.append(getNum(temp));
			}
		}
		//��ȡ������  ps����������ѭ��û�ߣ�����l������0
		l = six%16;
		
		//�����������
		sb.append(l<10 ? l : getNum(l));
		
		//����պ�����  ��Ҫ��ĩβ��1
		if(l==0){
			sb.append("1");
		}
		//�������
		System.out.println(sb.reverse().toString());

	}

	public static String getNum(int temp) {
		switch (temp) {
		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		default:
			return "";
		}
	}
}
