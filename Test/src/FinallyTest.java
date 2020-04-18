
public class FinallyTest {
	public static void main(String[] args) {
		test();
	}
	
	public static void test () {
		try {
			int a = 1/0;
			System.out.println("try  run");
		} catch (Exception e) {
			System.out.println("catch  run");
			return ;
		} finally {
			System.out.println("finally  run");
		}
	}
}
