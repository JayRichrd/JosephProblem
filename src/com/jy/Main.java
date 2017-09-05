package com.jy;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入n:");
		int n = scanner.nextInt();
		System.out.print("请输入步长m:");
		int m = scanner.nextInt();

		System.out.println("==========方法一==========");
		System.out.print("依次输出的数:");
		int last1 = lastRemaining1(n, m);
		System.out.println();
		System.out.println("最后剩余的数:" + last1);

		System.out.println("==========方法二==========");
		int last2 = lastRemaining2(n, m);
		System.out.println("最后剩余的数:" + last2);

		scanner.close();
	}

	/**
	 * 约瑟夫环
	 * 
	 * @param n
	 *            总共的元素个数
	 * @param m
	 *            删除的步长
	 * @return 返回最后剩余的1个元素
	 */
	public static int lastRemaining1(int n, int m) {
		if (n < 1 || m < 1)
			return -1;
		// 因为需要进行频繁的删除操作
		// 所以这里用底层使用链表结构的LinkedList
		List<Integer> circleList = new LinkedList<Integer>();
		// 填充链表
		for (int i = 0; i < n; i++)
			circleList.add(i);
		// 待删除元素的索引
		int removeIndex = 0;

		while (circleList.size() > 1) {
			// 不带累加获取待删除元素的索引
			// 由于是圆环，操作列表元素总和后需要从头开始
			// 这里需要注意的是每次循环会删除一个元素，后面的元素会填充这个删除的位置
			// 因此这次的删除点就是下次的起点，否则下次的起点应该是这次的删除点加1
			removeIndex = (removeIndex + m - 1) % circleList.size();
			// System.out.print(circleList.get(removeIndex) + " ");
			circleList.remove(removeIndex);
		}
		// 返回最后剩余的一个数
		return circleList.get(0);
	}

	/**
	 * 约瑟夫环
	 * 
	 * @param n
	 *            总共的元素个数
	 * @param m
	 *            删除的步长
	 * @return 返回最后剩余的1个元素
	 */
	public static int lastRemaining2(int n, int m) {
		if (n < 1 || m < 1)
			return -1;
		// 最后剩余的元素
		int last = 0;
		// 从第2个元素开始，使用递归公式f(n,m) = (f(n-1,m) + m) % n
		for (int i = 2; i <= n; i++)
			last = (last + m) % i;
		return last;
	}

}
