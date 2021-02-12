import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayListTest {
    private static ArrayList<String> aList = new ArrayList<String>();

    //使用迭代器遍历元素过程中，调用集合的 remove(Object obj) 方法可能会报 java.util.ConcurrentModificationException 异常
    public static void main(String[] args) {

        aList.add("111");
        aList.add("222");
        aList.add("333");
        System.out.println("移除前：" + aList);

        Iterator<String> iterator = aList.iterator();
        //详单与子线程中进行遍历，但是发生改变后，会和主线程对比，指针不一样报错。
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("222".equals(next)) {
                //会报异常
                //aList.remove("333");
                //会删除it.next返回的值。
                iterator.remove();
            }
        }
        System.out.println("移除后：" + aList);
    }

    //JDK 1.8 Iterator forEachRemaining 方法中 调用Iterator 的 remove 方法会报 java.lang.IllegalStateException 异常
    public static void testForEachRemainingIteRemove() {
        final Iterator<String> iterator = aList.iterator();
        iterator.forEachRemaining(new Consumer<String>() {

            public void accept(String t) {
                if ("222".equals(t)) {
                    iterator.remove();
                }
            }
        });
    }
}
