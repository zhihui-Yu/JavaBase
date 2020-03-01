/**
 * 深复制:
 * 	原对象的引用对象也实现了克隆
 * 当只有少数的引用对象时适合这个方法。
 * @author listener
 *
 */
public class CloneTest2
{
	public static void main(String[] args) throws Exception
	{
		Teacher teacher = new Teacher();
		teacher.setAge(40);
		teacher.setName("teacher zhang");
		
		Student2 student2 = new Student2();
		student2.setAge(14);
		student2.setName("lisi");
		student2.setTeacher(teacher);
		
		//深拷贝
		Student2 student3 = (Student2)student2.clone();
		//
		System.out.println(student2);
		System.out.println(student3);
		//改变teacher对象的值
		System.out.println("---------改变teacher对象的值--------------");
		teacher.setName("teacher li");//不会又任何影响
		System.out.println(student2);
		System.out.println(student3);
	
	}
 
}
class Student2 implements Cloneable
{
	private int age;
	private String name;
	private Teacher teacher;
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Teacher getTeacher()
	{
		return teacher;
	}
	public void setTeacher(Teacher teacher)
	{
		this.teacher = teacher;
	}
	
	@Override
	public String toString() {
		return "Student2 [age=" + age + ", name=" + name + ", teacher=" + teacher + "]"+"地址："+super.toString();
	}
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		//这一步返回的这个student2还只是一个浅克隆，
		Student2 student2 = (Student2)super.clone();
		//重新设置Teacher的地址
		student2.setTeacher((Teacher)student2.getTeacher().clone());
		//双层克隆使得那个teacher对象也得到了复制
		return student2;
	}
}
class Teacher implements Cloneable
{
	private int age;
	private String name;
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Teacher [age=" + age + ", name=" + name + "]"+"地址："+super.toString();
	}
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
	
}