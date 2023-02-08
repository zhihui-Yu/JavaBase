
public class HashTest {
    /**
     * p --> People@15db9742 p2 --> People@6d06d69c
     * 当p = p2时，二者的指定对象的索引也一样了，所以比较索引值时 二者是相等的
     *
     * @param args
     */
    public static void main(String[] args) {
        People p = new People(23);
        People p2 = new People(23);
        p = p2;
        System.out.println(p);
        System.out.println(p2);
        System.out.println(p2 == p);
        System.out.println(p2.equals(p));
    }

    public static String show() {
        return "stre";
    }
}

class People {
    public int age;

    public People(int age) {
        super();
        this.age = age;
    }

    public People() {
        super();
        // TODO Auto-generated constructor stub
    }
}