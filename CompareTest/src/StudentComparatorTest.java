import java.util.Arrays;
import java.util.Comparator;

public class StudentComparatorTest {

	public static void main(String[] args) {
		// 定义初始化长度
		final int STUDENT_NUM = 4;
		Student[] allStudents = new Student[STUDENT_NUM];

		// 初始化学生数据
		allStudents[0] = new Student("00001", "a");
		allStudents[1] = new Student("00003", "b");
		allStudents[2] = new Student("00002", "c");
		allStudents[3] = new Student("00004", "d");

		// 批量初始化年龄和成绩
		for (int i = 0; i < allStudents.length; i++) {
			allStudents[i].setAge(i * 10);
		}
		for (int i = 0; i < allStudents.length; i++) {
			allStudents[i].setScore(99 - i * 1.5);
		}

		// 按姓名升序排序
		Arrays.sort(allStudents, new ComparatorWithNameUP());
		// 显示学生信息
		System.out.println("按姓名升序排序结果：");
		System.out.println("学号" + "\t姓名" + "\t年龄" + "\t成绩");
		for (int i = 0; i < allStudents.length; i++) {
			System.out.print(allStudents[i]);
		}

		// 按姓名降序排序
		Arrays.sort(allStudents, new ComparatorWithNameDown());
		// 显示学生信息
		System.out.println("按姓名降序排序结果：");
		System.out.println("学号" + "\t姓名" + "\t年龄" + "\t成绩");
		for (int i = 0; i < allStudents.length; i++) {
			System.out.print(allStudents[i]);
		}

		// 按成绩降序排序
		Arrays.sort(allStudents, new ComparatorWithScoreDown());
		// 显示学生信息
		System.out.println("按成绩降序排序结果：");
		System.out.println("学号" + "\t姓名" + "\t年龄" + "\t成绩");
		for (int i = 0; i < allStudents.length; i++) {
			System.out.print(allStudents[i]);
		}

		// 按成绩升序排序
		Arrays.sort(allStudents, new ComparatorWithScoreUp());
		// 显示学生信息
		System.out.println("按成绩升序排序结果：");
		System.out.println("学号" + "\t姓名" + "\t年龄" + "\t成绩");
		for (int i = 0; i < allStudents.length; i++) {
			System.out.print(allStudents[i]);
		}
	}
}

// 按姓名进行升序排序的外部类，用Comparator接口
class ComparatorWithNameUP implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
}

// 按姓名进行降序排序的外部类，用Comparator接口
class ComparatorWithNameDown implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		return arg1.getName().compareTo(arg0.getName());
	}
}

// 按成绩降序
class ComparatorWithScoreDown implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		if (arg1.getScore() > arg0.getScore())
			return 1;
		else {
			if (arg1.getScore() == arg0.getScore())
				return 0;
			else
				return -1;
		}
	}

}

// 按成绩升序
class ComparatorWithScoreUp implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		if (arg0.getScore() > arg1.getScore())
			return 1;
		else {
			if (arg0.getScore() == arg1.getScore())
				return 0;
			else
				return -1;
		}
	}

}
