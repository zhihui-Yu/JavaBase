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
		 * 找到连续的字段，升序降序都可以
		 * 最后一个可以用不着
		 */
		for (int i = 0; i < arr.length-1; i++) {
			temp = i;
			count = 0;
			//如果后面大,则保存数到该数字的后面
			while(i < arr.length-1 && arr[i+1][0] >= arr[i][0] ){
				arr[temp][++count] = arr[++i][0];
			}
			if(temp != i) {
				//进行后面一个的比较
				i = i + 1;
				//temp为后一个的下标
				temp = i;
				count = 0; 
			}
			//比后面小的时候
			while(i < arr.length -1 && arr[i+1][0] <= arr[i][0] ){
				arr[temp][++count] = arr[++i][0];
			}
			/*if(temp != i){
				//进行后面一个的比较
				i = 1 + i;
			}*/
		}
		count = 0;
		//如果长度大于2 则说明后面有添加数
		for (int i = 0; i < arr.length; i++) {
			if(arr[i][1] != 0){
				count ++;
			}
		}
		in.close();
		System.out.println(count);
	}
}
