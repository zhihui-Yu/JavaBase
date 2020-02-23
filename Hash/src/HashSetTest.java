import java.util.HashSet;

/**
 * 存储的元素不能重复
 * 重写对象的equals和hashCode很重要
 * @author listener
 *
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<Person>();
        Person p1 = new Person("zhangsan", 22);
        Person p2 = new Person("zhangsan", 22);

        set.add(p1);
        set.add(p2);

        System.out.println(set.size());

    }
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null != obj && obj instanceof Person) {
            Person p = (Person) obj;
            if (name.equals(p.name) && age == p.age) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
