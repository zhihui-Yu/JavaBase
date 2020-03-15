import java.util.HashSet;
import java.util.TreeSet;

/**
 * 存储的元素不能重复
 * 重写对象的equals和hashCode很重要
 * @author listener
 *
 */
public class HashSetTest {
    public static void main(String[] args) {
    	//hashSet 乱序
        HashSet<Person> set = new HashSet<Person>();
        Person p1 = new Person("zhangsan", 22);
        Person p2 = new Person("zhangsan1", 21);
        Person p3 = new Person("zhangsan2", 20);
        Person p4 = new Person("zhangsan4", 23);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);
        for (Person person : set) {
        	System.out.println(person);
		}
        //TreeSet 需要对象实现comparable接口 默认升序 可自定义排序
        TreeSet<Person> ts = new TreeSet<>();
        ts.add(p1);
        ts.add(p2);
        ts.add(p3);
        ts.add(p4);
        for (Person person : ts) {
        	System.out.println(person);
		}
    }
}
class Person implements Comparable<Person>{
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
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
    public int hashCode() {
        return this.name.hashCode();
    }

	@Override
	public int compareTo(Person o) {
		return -((Integer)this.age).compareTo((Integer)o.getAge());
	}
}
