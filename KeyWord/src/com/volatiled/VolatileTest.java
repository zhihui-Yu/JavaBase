package com.volatiled;

/**
 * volatile �ɼ��ԣ������ԣ�û��ԭ����
 * 
 * ��һ���߳��޸ĸùؼ������εı���ʱ�������̻߳�����֪���µ�ֵ���ɼ���(�洢���ڴ���)
 * ��ֹ���������������ֹ�Ѹñ����������ñȸñ���֮ǰִ�е�һ����ִ�У�֮���Ҳһ���ڸñ���֮��ִ��:������
 * û��ԭ����: �����߳�1��ȡ��ֵ��׼����1ʱ���߳�2Ҳ��ȡֵ��1�������߳�2�ύ�ıȽ��磬�����ڴ��ֵ��1���߳�1Ҳ�ύ�ˣ���ʱ�ڴ��ֵ����1��
 * @author listener
 *
 */
public class VolatileTest {
	volatile int i;

	public void addI() {
		i++;
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileTest test = new VolatileTest();
		for (int n = 0; n < 1000; n++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.addI();
				}
			}).start();
		}
		Thread.sleep(10000);// �ȴ�10�룬��֤�������ִ�����
		System.out.println(test.i);
	}
}
