package com.str;
/**
 * URL������дһ�ַ��������ַ����еĿո�ȫ���滻Ϊ%20���ٶ����ַ���β�����㹻�Ŀռ��������ַ�������֪���ַ����ġ���ʵ�����ȡ�
 * @author listener
 *
 */
public class UrlTest {
	public static void main(String[] args) {
		System.out.println(replaceSpaces("nwmog q k  gW  c    H  DYpIE    Lcz         gV    Bj   vkH X g       l   ", 72));
	}
	public static String replaceSpaces(String S, int length) {

		String[] str = S.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if(S.charAt(i) == ' '){
				sb.append("%20");
			}else {
				sb.append(S.charAt(i));
			}
		}
		return sb.toString();
	}
}
