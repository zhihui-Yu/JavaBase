import java.util.Arrays;

public class CompareTest {
    public static void main(String[] args) {
        // 定义初始化长度
        final int STUDENT_NUM = 4;
        Student[] allStudents = new Student[STUDENT_NUM];

        // 初始化
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

        // 按学号升序排序
        Arrays.sort(allStudents);

        // 显示学生信息
        System.out.println("学号" + "\t姓名" + "\t年龄" + "\t成绩");
        for (int i = 0; i < allStudents.length; i++) {
            System.out.print(allStudents[i]);
        }

    }
}
