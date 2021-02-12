
public class MathTest {
    public static void main(String[] args) {
        System.out.println(Math.round(-2.5));
        StringBuilder sb = new StringBuilder(10);
        sb.append("hello");
        //sb.capacity() 因为StringBuffer在为对象分配长度的时候，起始会分配一个字，也就是两个字节长度即（16位），每增加一个字符，长度就会在16的基础上加 12
        System.out.println("capacity: " + sb.capacity() + "----length: " + sb.length());
        System.out.println(sb.reverse());
        String str = "hello";
        String str2 = new String("hello");
        System.out.println(str.hashCode() + "----" + str2.hashCode());
    }
}
