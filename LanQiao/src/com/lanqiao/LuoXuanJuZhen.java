package com.lanqiao;

import java.util.Scanner;

/**
 * 对于一个 n 行 m 列的表格，我们可以使用螺旋的方式给表格依次填上正整数，我们称填好的表格为一个螺旋矩阵。 例如，一个 4 行 5 列的螺旋矩阵如下：
 * 1  2  3  4  5 
 * 14 15 16 17 6 
 * 13 20 19 18 7 
 * 12 11 10 9  8
 * 
 * @author listener
 *
 */
public class LuoXuanJuZhen {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int[][] arr = new int[r][c];
		int row = sc.nextInt();
		int col = sc.nextInt();
		
		sc.close();
		
		int count = r*c;
		
		int value = 1;
		int rows = 0;
		int cols = 0;
		int i = 1;
		arr[rows][cols] = value;
		while(count > value ){
			if(arr[row-1][col-1]!=0)
				break;
			while( cols < c-i){
				arr[rows][++cols] = ++value;
			}

			while( cols == c-i && rows < r-i) {
				arr[++rows][cols] = ++value;
			}
			while( cols > i-1 && rows == r-i ){
				arr[rows][--cols] = ++value;
			}
			while(cols == i-1 && rows > i) {
				arr[--rows][cols] = ++value;
			}
			i++;
		}
		
		System.out.println(arr[row-1][col-1]);
	}
}
