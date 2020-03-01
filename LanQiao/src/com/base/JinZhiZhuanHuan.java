package com.base;

import java.util.Scanner;

/**
 * 十六进制转八进制
 * 
 * 思路 16 --> 2 -->8
 * 
 * 例子：
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

		// 存放二进制
		String[] twoArr = new String[n];

		// 存放八进制
		String[] eightArr = new String[n];

		// 放每次八进制的结果
		StringBuilder sb2 = new StringBuilder();

		// 放每次三个数字
		StringBuilder sb3 = new StringBuilder();

		// 16 --> 2
		for (int i = 0; i < sixteenArr.length; i++) {

			twoArr[i] = sixToTwo(sixteenArr[i]);

		}

		// 将二进制转为八进制
		for (int i = 0; i < twoArr.length; i++) {

			// 判断字符是不是3的倍数
			index = twoArr[i].length() % 3 == 0 ? twoArr[i].length() / 3 : (twoArr[i].length() / 3) + 1;

			// 对sb3清空
			sb3.delete(0, sb3.length());

			// sb2清空
			sb2.delete(0, sb2.length());

			// 如果不是刚好，则在前面加入相应的零
			if (index != (twoArr[i].length()/3)) {

				if (twoArr[i].length() % 3 == 1) {

					twoArr[i] = "00" + twoArr[i];

				} else if(twoArr[i].length() % 3 == 2){
					
					twoArr[i] = "0"+ twoArr[i];

				}

			}
			
			sb3.append(twoArr[i]);

			// 计算每次结果放入sb2
			for (int j = 0; j < index; j++) {
				sb2.append(twoToEight(twoArr[i].substring(j * 3, j * 3 + 3)));
			}
			eightArr[i] = sb2.toString();
		}
		// 输出
		for (String string : eightArr) {
			//判断第一位是不是零
			System.out.println(string.charAt(0)=='0'? string.substring(1,string.length()): string);
		}
	}

	/**
	 * 二进制转八进制
	 * 
	 * @param str
	 * @return
	 */
	public static String twoToEight(String str) {
		// 清除sb内的值
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
	 * 十六转二
	 * 
	 * @param str
	 * @return
	 */
	public static String sixToTwo(String str) {
		// 清除sb内的值
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
