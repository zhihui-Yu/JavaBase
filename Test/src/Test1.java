import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int temp = in.nextInt();
		int[][] arr = new int[temp][temp+1];
		int count = 0;
		while (temp > 0) {
			arr[count++][0] = in.nextInt();
			temp--;
		}
		/**
		 * �ҵ��������ֶΣ������򶼿���
		 * ���һ�������ò���
		 */
		for (int i = 0; i < arr.length-1; i++) {
			temp = i;
			count = 0;
			//��������,�򱣴����������ֵĺ���
			while(i < arr.length-1 && arr[i+1][0] >= arr[i][0] ){
				arr[temp][++count] = arr[++i][0];
			}
			if(temp != i) {
				//���к���һ���ıȽ�
				i = i + 1;
				//tempΪ��һ�����±�
				temp = i;
				count = 0; 
			}
			//�Ⱥ���С��ʱ��
			while(i < arr.length -1 && arr[i+1][0] <= arr[i][0] ){
				arr[temp][++count] = arr[++i][0];
			}
			/*if(temp != i){
				//���к���һ���ıȽ�
				i = 1 + i;
			}*/
		}
		count = 0;
		//������ȴ���2 ��˵�������������
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][1] != 0){
				count ++;
			}
		}
		in.close();
		System.out.println(count);
	}
}
