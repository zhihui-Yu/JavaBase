import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 深拷贝 之 序列化
 * @author listener
 *
 */
public class CloneTest3
{
	public static void main(String[] args) throws Exception
	{
		//创建对象
		Teacher3 teacher3 = new Teacher3();
		teacher3.setAge(23);
		teacher3.setName("李老师");
		
		Student3 student3 = new Student3();
		student3.setAge(20);
		student3.setName("王五");
		student3.setTeacher3(teacher3);
		
		//拷贝
		Student3 student4 = (Student3)student3.deepCopy();
		
		System.out.println("----------拷贝完-----------");
		System.out.println(student3);
		System.out.println(student4);
		
		System.out.println("-----------修改值------------");
		
		student4.getTeacher3().setAge(60);
		student4.getTeacher3().setName("赵老师");
		
		System.out.println(student3);
		System.out.println(student4);
		
	}
	
 
}
class Teacher3 implements Serializable
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
		return "Teacher3 [age=" + age + ", name=" + name + "]"+"地址："+super.toString();
	}
	
}

class Student3 implements Serializable
	{
		private static final long serialVersionUID = 1L;
		private int age;
		private String name;
		private Teacher3 teacher3;
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
		public Teacher3 getTeacher3()
		{
			return teacher3;
		}
		public void setTeacher3(Teacher3 teacher3)
		{
			this.teacher3 = teacher3;
		}
		
		@Override
		public String toString() {
			return "Student3 [age=" + age + ", name=" + name + ", teacher3=" + teacher3 + "]"+"地址："+super.toString();
		}
		
		//使得序列化student3的时候也会将teacher序列化
		public Object deepCopy()throws Exception
		{
			//将当前这个对象写到一个输出流当中，，因为这个对象的类实现了Serializable这个接口，所以在这个类中
			//有一个引用，这个引用如果实现了序列化，那么这个也会写到这个输出流当中
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream  oos = new ObjectOutputStream(bos);
			oos.writeObject(this);
			
			//这个就是将流中的东西读出类，读到一个对象流当中，这样就可以返回这两个对象的东西，实现深克隆
			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			return ois.readObject();
		}
	}