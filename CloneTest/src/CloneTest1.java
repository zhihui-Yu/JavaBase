/**
 * 浅拷贝
 * 		拷贝对象的引用对象成员变量的地址也拷贝，导致当原对象修改引用对象变量时，克隆对象的引用对象变量也跟着改变了。
 * @author listener
 *
 */
public class CloneTest1 {
	public static void main(String[] args) throws CloneNotSupportedException {
		
		Mark mark = new Mark(100, 99);
		User user = new User("user", 22, mark);
		
		User userClone = (User) user.clone();
		
		System.out.println("原user：" + user);
		System.out.println("克隆的user：" + userClone);
		
		// 修改引用类型的mark属性
		user.mark.setMath(60);
		
		System.out.println("修改后的原user：" + user);
		System.out.println("修改后的克隆user：" + userClone);
		
		//来看看他们的地址
	}
}

class Mark {
	private int chinese;
	private int math;

	public Mark(int chinese, int math) {
		this.chinese = chinese;
		this.math = math;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "Mark{" + "chinese=" + chinese + ", math=" + math + '}'+"地址："+super.toString();
	}
}

class User implements Cloneable {
	private String name;
	private int age;
	public Mark mark;

	public User(String name, int age, Mark mark) {
		this.name = name;
		this.age = age;
		this.mark = mark;
	}

	@Override
	public String toString() {
		
		return "User{" + "name='" + name + '\'' + ", age=" + age + ", mark=" + mark + '}'+"地址："+super.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}