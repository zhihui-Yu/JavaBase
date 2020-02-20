
public class Demo1 {
	public static void main(String[] args) {
		Class<?> test = Demo1.class;
		ClassLoader classLoader = test.getClassLoader();
		//引用加载器
		System.out.println(classLoader.getParent().getParent());
		//拓展加载器
		System.out.println(classLoader.getParent());
		//app加载器
		System.out.println(classLoader);
	}
	
}
