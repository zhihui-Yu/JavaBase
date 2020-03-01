
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayToList {
	
	public static void main(String[] args) {
		
		//数组转list
		String[] str=new String[] {"hello","world"};
		//方式一：使用for循环把数组元素加进list
		List<String> list=new ArrayList<String>();
		for (String string : str) {
			list.add(string);
		}
		System.out.println(list);
		
		//方式二：
		List<String> list2=new ArrayList<String>(Arrays.asList(str));
		System.out.println(list2);
		
		//方式三：
		//同方法二一样使用了asList()方法。这不是最好的，
		//因为asList()返回的列表的大小是固定的。
		//事实上，返回的列表不是java.util.ArrayList类，而是定义在java.util.Arrays中一个私有静态类java.util.Arrays.ArrayList
		//我们知道ArrayList的实现本质上是一个数组，而asList()返回的列表是由原始数组支持的固定大小的列表。
		//这种情况下，如果添加或删除列表中的元素，程序会抛出异常UnsupportedOperationException。
		//java.util.Arrays.ArrayList类具有 set()，get()，contains()等方法，但是不具有添加add()或删除remove()方法,所以调用add()方法会报错。
		List<String> list3 = Arrays.asList(str);
		//list3.remove(1);
		boolean contains = list3.contains("l");
		System.out.println(contains);
		System.out.println(list3);
		
		//方式四：使用Collections.addAll()
		List<String> list4=new ArrayList<String>(str.length);
		Collections.addAll(list4, str);
		System.out.println(list4);
		
		//方式五：使用Stream中的Collector收集器
		//转换后的List 属于 java.util.ArrayList 能进行正常的增删查操作
		List<String> list5=Stream.of(str).collect(Collectors.toList());
		System.out.println(list5);
	}
}

