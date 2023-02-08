import java.util.LinkedList;
import java.util.List;

public class LinkListRemoveTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        /**
         * 反着删除(OK) --> 	因为第一次删除(最后一个)时，数组为123了， 
         * 					第二次删除(倒数第二) 	数组为 12
         * 					第三次删除(倒数第三个) 数组为 1
         * 	保证每次删除都有值可以被删掉。
         */
		/*for (int i = list.size()-1; i >= 0; i--) {
			list.remove(i);
		}*/
        /**
         * 正着删除(删不干净) -->   每次删除都是返回一个重新复制出来的数组
         * 				删除第一个时候，数组变为 234  但是删除第二时候  
         * 				数组会变成 24  删除第三个时，数组长度不大于3 所有没动静
         */
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list.toString());
    }
}
