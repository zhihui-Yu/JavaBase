
//类内部实现Comparable接口
public class Student implements Comparable<Student> {
	private String ID;
	private String name;
	private int age;
	private Double score;

	// 定义两个构造方法
	public Student(String ID, String name) {
		this(ID, name, 0, 0);
	}

	public Student(String ID, String name, int age, double score) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getScore() {
		return score;
	}

	public void setID(String newID) {
		ID = newID;
	}

	public void setName(String newName) {
		name = newName;
	}

	public void setAge(int newAge) {
		if (newAge < 0)
			newAge = 0;
		age = newAge;
	}

	public void setScore(double newScore) {
		if ((newScore < 0) || (newScore > 100))
			newScore = 0;
		score = newScore;
	}

	// 重写对象字符串表示方法
	@Override
	public String toString() {
		String info = new String();
		info = ID + '\t' + name + '\t' + age + '\t' + score + '\n';
		return info;
	}

	@Override
	public int compareTo(Student arg0) {
		System.out.println("======================");
		// 此种写法为升序，若改为arg0.ID.compareTo(this.ID)则为降序
		return this.ID.compareTo(arg0.ID);
	}

	// 按成绩升序
	// @Override
	// public int compareTo(Student arg0) {
	// return this.score.compareTo(arg0.score);
	// }

}
