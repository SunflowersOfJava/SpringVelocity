package com.boonya.base.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

/**
 * 字符组合算法类
 * 
 * @packge com.boonya.combination.Combination
 * @date 2015年11月20日 上午11:09:17
 * @author pengjunlin
 * @comment 组合算法
 * @update  添加注释
 */
public class Combination {

	private ArrayList<String> combList = new ArrayList<String>();

	/**
	 * mn组合配对
	 * 
	 * @MethodName: mn
	 * @Description:
	 * @param array
	 * @param n
	 * @throws
	 */
	public void mn(String[] array, int n) {
		int m = array.length;
		if (m < n)
			throw new IllegalArgumentException("Error   m   <   n");
		BitSet bs = new BitSet(m);
		for (int i = 0; i < n; i++) {
			bs.set(i, true);
		}
		do {
			printAll(array, bs);
		} while (moveNext(bs, m));

	}

	/**
	 * * 1、start 第一个true片段的起始位，end截止位 2、把第一个true片段都置false
	 * 3、数组从0下标起始到以第一个true片段元素数量减一为下标的位置都置true 4、把第一个true片段end截止位置true
	 * 
	 * @MethodName: moveNext
	 * @Description:
	 * @param bs
	 *            数组是否显示的标志位
	 * @param m
	 *            数组长度
	 * @return boolean 是否还有其他组合
	 * @throws
	 */
	private boolean moveNext(BitSet bs, int m) {
		int start = -1;
		while (start < m)
			if (bs.get(++start))
				break;
		if (start >= m)
			return false;

		int end = start;
		while (end < m)
			if (!bs.get(++end))
				break;
		if (end >= m)
			return false;
		for (int i = start; i < end; i++)
			bs.set(i, false);
		for (int i = 0; i < end - start - 1; i++)
			bs.set(i);
		bs.set(end);
		return true;
	}

	/**
	 * 输出生成的组合结果
	 * 
	 * @MethodName: printAll
	 * @Description:
	 * @param array
	 *            数组
	 * @param bs
	 *            数组元素是否显示的标志位集合
	 * @throws
	 */
	private void printAll(String[] array, BitSet bs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++)
			if (bs.get(i)) {
				sb.append(array[i]).append(',');
			}
		sb.setLength(sb.length() - 1);
		combList.add(sb.toString());
	}

	public ArrayList<String> getCombList() {
		return combList;
	}

	/**
	 * 测试main函数入口
	 * 
	 * @MethodName: main
	 * @Description:
	 * @param args
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {
		Combination comb = new Combination();

		comb.mn(new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z" }, 6);
		System.out.println("a-z排列方式共：" + comb.getCombList().size());
		String[] list = comb.getCombList().get(0).split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			sb.append(list[i]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		System.out.println("组合值：" + sdf.format(new Date()) + sb);
	}

}
