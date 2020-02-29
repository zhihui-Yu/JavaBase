package com.base;

import java.util.Scanner;

/**
 * ʮ������ת�˽���
 * 
 * ˼· 16 --> 2 -->8
 * 
 * ���ӣ�
 * 	2 
 * 	39 
 * 	123ABC
 * 
 * @author listener
 *
 */
public class JinZhiZhuanHuan {

	private static StringBuilder sb = new StringBuilder();

	private static String[] SIXTEEN_BIN = { "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
			"1001", "1010", "1011", "1100", "1101", "1110", "1111" };

	private static String[] EIGHT_BIN = { "000", "001", "010", "011", "100", "101", "110", "111" };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		String[] sixteenArr = new String[n];

		int index = 0;

		while (index < n) {

			sixteenArr[index++] = sc.next();

		}

		sc.close();

		// ��Ŷ�����
		String[] twoArr = new String[n];

		// ��Ű˽���
		String[] eightArr = new String[n];

		// ��ÿ�ΰ˽��ƵĽ��
		StringBuilder sb2 = new StringBuilder();

		// ��ÿ����������
		StringBuilder sb3 = new StringBuilder();

		// 16 --> 2
		for (int i = 0; i < sixteenArr.length; i++) {

			twoArr[i] = sixToTwo(sixteenArr[i]);

		}

		// ��������תΪ�˽���
		for (int i = 0; i < twoArr.length; i++) {

			// �ж��ַ��ǲ���3�ı���
			index = twoArr[i].length() % 3 == 0 ? twoArr[i].length() / 3 : (twoArr[i].length() / 3) + 1;

			// ��sb3���
			sb3.delete(0, sb3.length());

			// sb2���
			sb2.delete(0, sb2.length());

			// ������Ǹպã�����ǰ�������Ӧ����
			if (index != (twoArr[i].length()/3)) {

				if (twoArr[i].length() % 3 == 1) {

					twoArr[i] = "00" + twoArr[i];

				} else if(twoArr[i].length() % 3 == 2){
					
					twoArr[i] = "0"+ twoArr[i];

				}

			}
			
			sb3.append(twoArr[i]);

			// ����ÿ�ν������sb2
			for (int j = 0; j < index; j++) {
				sb2.append(twoToEight(twoArr[i].substring(j * 3, j * 3 + 3)));
			}
			eightArr[i] = sb2.toString();
		}
		// ���
		for (String string : eightArr) {
			//�жϵ�һλ�ǲ�����
			System.out.println(string.charAt(0)=='0'? string.substring(1,string.length()): string);
		}
	}

	/**
	 * ������ת�˽���
	 * 
	 * @param str
	 * @return
	 */
	public static String twoToEight(String str) {
		// ���sb�ڵ�ֵ
		sb.delete(0, sb.length());

		for (int i = 0; i < 8; i++) {
			if (EIGHT_BIN[i].equals(str)) {
				sb.append(i);
				break;
			}
		}

		return sb.toString();
	}

	/**
	 * ʮ��ת��
	 * 
	 * @param str
	 * @return
	 */
	public static String sixToTwo(String str) {
		// ���sb�ڵ�ֵ
		sb.delete(0, sb.length());
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case '0':
				sb.append(SIXTEEN_BIN[0]);
				break;
			case '1':
				sb.append(SIXTEEN_BIN[1]);
				break;
			case '2':
				sb.append(SIXTEEN_BIN[2]);
				break;
			case '3':
				sb.append(SIXTEEN_BIN[3]);
				break;
			case '4':
				sb.append(SIXTEEN_BIN[4]);
				break;
			case '5':
				sb.append(SIXTEEN_BIN[5]);
				break;
			case '6':
				sb.append(SIXTEEN_BIN[6]);
				break;
			case '7':
				sb.append(SIXTEEN_BIN[7]);
				break;
			case '8':
				sb.append(SIXTEEN_BIN[8]);
				break;
			case '9':
				sb.append(SIXTEEN_BIN[9]);
				break;
			case 'A':
				sb.append(SIXTEEN_BIN[10]);
				break;
			case 'B':
				sb.append(SIXTEEN_BIN[11]);
				break;
			case 'C':
				sb.append(SIXTEEN_BIN[12]);
				break;
			case 'D':
				sb.append(SIXTEEN_BIN[13]);
				break;
			case 'E':
				sb.append(SIXTEEN_BIN[14]);
				break;
			case 'F':
				sb.append(SIXTEEN_BIN[15]);
				break;
			default:
				break;
			}
		}
		return sb.toString();
	}
}
