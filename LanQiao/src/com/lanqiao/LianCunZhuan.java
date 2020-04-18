package com.lanqiao;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * 问题描述 2015年，全中国实现了户户通电。作为一名电力建设者，小明正在帮助一带一路上的国家通电。 这一次，小明要帮助 n 个村庄通电，其中 1
 * 号村庄正好可以建立一个发电站，所发的电足够所有村庄使用。 现在，这 n
 * 个村庄之间都没有电线相连，小明主要要做的是架设电线连接这些村庄，使得所有村庄都直接或间接的与发电站相通。
 * 小明测量了所有村庄的位置（坐标）和高度，如果要连接两个村庄，小明需要花费两个村庄之间的坐标距离加上高度差的平方，形式化描述为坐标为 (x_1, y_1)
 * 高度为 h_1 的村庄与坐标为 (x_2, y_2) 高度为 h_2 的村庄之间连接的费用为
 * sqrt((x_1-x_2)*(x_1-x_2)+(y_1-y_2)*(y_1-y_2))+(h_1-h_2)*(h_1-h_2)。 在上式中 sqrt
 * 表示取括号内的平方根。请注意括号的位置，高度的计算方式与横纵坐标的计算方式不同。 由于经费有限，请帮助小明计算他至少要花费多少费用才能使这 n
 * 个村庄都通电。
 * 
 * @author listener
 *
 */
public class LianCunZhuan {
	static class Villege implements Comparable<Villege> {
		double x;
		double y;
		double h;

		@Override
		public int compareTo(Villege o) {
			if (x == o.x) {
				if (y == o.y) {
					return (int) (h - o.h);
				}
				return (int) (y - o.y);
			}
			return (int) (x - o.x);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		TreeSet<Villege> set = new TreeSet<>();
		while (count-- > 0) {
			Villege v = new Villege();
			v.x = sc.nextDouble();
			v.y = sc.nextDouble();
			v.h = sc.nextDouble();
			set.add(v);
		}

		double ans = 0.0;

		Object[] array = set.toArray();
		for (int i = 0; i < array.length - 1; i++) {
			ans += getMoney((Villege) array[i], (Villege) array[i + 1]);
		}
		System.out.println(String.format("%.2f", ans));
		sc.close();
	}

	public static double getMoney(Villege v1, Villege v2) {
		return Math.sqrt(Math.pow((v1.x - v2.x), 2) + Math.pow((v1.y - v2.y), 2)) + Math.pow((v1.h - v2.h), 2);
	}
}
