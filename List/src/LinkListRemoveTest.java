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
		 * ����ɾ��(OK) --> 	��Ϊ��һ��ɾ��(���һ��)ʱ������Ϊ123�ˣ� 
		 * 					�ڶ���ɾ��(�����ڶ�) 	����Ϊ 12
		 * 					������ɾ��(����������) ����Ϊ 1
		 * 	��֤ÿ��ɾ������ֵ���Ա�ɾ����
		 */
		/*for (int i = list.size()-1; i >= 0; i--) {
			list.remove(i);
		}*/
		/**
		 * ����ɾ��(ɾ���ɾ�) -->   ÿ��ɾ�����Ƿ���һ�����¸��Ƴ���������
		 * 				ɾ����һ��ʱ�������Ϊ 234  ����ɾ���ڶ�ʱ��  
		 * 				������� 24  ɾ��������ʱ�����鳤�Ȳ�����3 ����û����
		 */
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
		System.out.println(list.toString());
	}
}
