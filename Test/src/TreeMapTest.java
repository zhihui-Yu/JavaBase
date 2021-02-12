import java.util.Set;
import java.util.TreeSet;

public class TreeMapTest {
    public static void main(String[] args) {
        // 构造一个可用的TreeSet对象：传入对象实现 Comparable 接口 （按照年龄排序）
        Set<User> userSet = new TreeSet<>();
        userSet.add(new User(5, 32, "阿布1"));
        userSet.add(new User(12, 25, "阿布2"));
        userSet.add(new User(9, 36, "阿布3"));
        userSet.add(new User(20, 34, "阿布4"));
        userSet.add(new User(15, 48, "阿布5"));
        for (User u : userSet) {
            System.out.println(u.toString());
        }
    }

    // static 内部类，可以在main方法中 直接使用new User(...)初始化，否则就要：new TreeSetTest().new User(...) 才行
    static class User implements Comparable<User> {
        Integer id;

        Integer age;

        String name;

        public User(Integer id, int age, String name) {
            super();
            this.id = id;
            this.age = age;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ", age=" + age + ", name=" + name + "]";
        }

        @Override
        public int compareTo(User arg0) {
            return this.id.compareTo(arg0.getId());
        }
    }

}
