import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo2 {
	public static String publicStaticString;
	protected static String protectedStaticString;
	static String staticString;
	private static String privateStaticString;
	public  String publicString;
	protected String protectedString;
	String string;
	private String privateString;
	private static Class<?> c1;
	
	
	public static void main(String[] args) throws Exception {
		// 第一种方式：
		c1 = Class.forName("Demo2");
			
		// 第二种方式：
		// java中每个类型都有class 属性.
		c1= Demo2.class;

		// 第三种方式：
		// java语言中任何一个java对象都有getClass 方法
		Demo2 e= new Demo2();
		c1 = e.getClass(); // c3是运行时类 (e的运行时类是Employee)
		
		System.out.println("---------获取所有自身属性------------");
		for (Field string : c1.getDeclaredFields()) {
			System.out.println(string);
		}
		System.out.println("---------获取自身public属性------------");
		for (Field string : c1.getFields()) {
			System.out.println(string);
		}
		System.out.println("---------获取所有自身方法------------");
		for (Method method : c1.getDeclaredMethods()) {
			System.out.println(method);
		}
		System.out.println("---------获取所以属于该类的public方法------------");
		//所有的类都会继承Object 所以会自动实现equals、wait、notify等方法
		for (Method method : c1.getMethods()) {
			System.out.println(method);
		}
	}
	
	public void show1 () {
		System.out.println("public show()");
	}
	protected void show6 () {
		System.out.println("protected show()");
	}
	private void show2 () {
		System.out.println("private show()");
	}
	public static void show3 () {
		System.out.println("public static show()");
	}
	protected static void show4 () {
		System.out.println("protected static show()");
	}
	private static void show5 () {
		System.out.println("private static show()");
	}	
	static void show7 () {
		System.out.println("private static show()");
	}
}
