/**
 * @author simple
 */
public class JMM_Test {
    public static void main(String[] args) throws InterruptedException {
        // 双亲委托机制
        System.out.println(JMM_Test.class.getClassLoader());
        System.out.println(JMM_Test.class.getClassLoader().getParent());
        System.out.println(JMM_Test.class.getClassLoader().getParent().getParent());
        System.out.println(JMM_Test.class.getClassLoader().getParent().getParent().getParent());
    }
}
