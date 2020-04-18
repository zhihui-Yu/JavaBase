package Array;


/**
 * 在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author listener
 *
 */
public class Array {
	public static void main(String[] args) {
	}
	public boolean Find(int target, int[][] array) {
		//获取行
		int rows = array.length;
		//获取列
		int cols = array[0].length;
		//从左下角开始 遍历
		int i = rows - 1, j = 0;
		//如果target大于array[i][j] 则向左移动
		//如果target小于array[i][j] 则向上移动
		//如果相等则返回true
		while (i >= 0 && j < cols) {
			if (target < array[i][j])
				i--;
			else if (target > array[i][j])
				j++;
			else
				return true;
		}
		return false;
	}
}
class Test extends Array{
	public Test(){
		super();
		System.out.println("");
	}
	Test(String id){
		this();
	}
}
