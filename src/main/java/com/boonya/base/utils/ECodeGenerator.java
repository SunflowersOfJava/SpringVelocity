package com.boonya.base.utils;

import java.util.ArrayList;
import java.util.List;

public class ECodeGenerator {

	/**
	 * 根据面单起始号等信息，生成固定个数的电子面单
	 * 
	 * @MethodName: generate
	 * @Description:
	 * @param startfix
	 *            起始面单与结束面单相同的字符串
	 * @param totalLength
	 *            面单号的总长度
	 * @param startNumber
	 *            面单开始的索引
	 * @param stepNumber
	 *            面单步长(间隔)
	 * @param specialNumber
	 *            特殊数字
	 * @param count
	 *            生成面单的个数
	 * @return
	 * @throws
	 */
	public List<String> generate(String startfix, int totalLength,
			long startNumber, long stepNumber, int specialNumber, int count) {
		long totalCount = 0;
		int currentCount = 0;
		long index = 0;
		// 纯数字或字符串形式的长度匹配
		int st = startfix.length() == 0 ? totalLength : totalLength
				- startfix.length();
		String FORMAT_STR = "%0" + st + "d";
		List<String> list = new ArrayList<String>();
		for (long i = 0; i <= count; i++) {
			if (currentCount == count) {
				break;
			}
			if (i == 0) {
				index = startNumber;
				// 打印起始面单
				totalCount++;
				String code = startfix + String.format(FORMAT_STR, index);
				list.add(code);
				System.out.println(code);
			} else {
				if (currentCount < count) {
					long val = 0;
					String str = index + "";
					String lastNumber = str.substring(str.length() - 1,
							str.length());
					if (Integer.parseInt(lastNumber) == specialNumber) {
						val = index - specialNumber;
					} else {
						val = index;
					}
					index = val;
				}
				totalCount++;
				String code = startfix + String.format(FORMAT_STR, index);
				list.add(code);
				// 打印当前面单
				System.out.println(code);
			}
			index += stepNumber;
			currentCount++;
		}
		System.out.println("共计:" + totalCount);
		return list;
	}

	/**
	 * 根据面单起始号、面单结束号生成面单号码
	 * 
	 * @MethodName: generate
	 * @Description:
	 * @param wesbStartNo
	 *            面单起始号
	 * @param wesbEndNo
	 *            面单结束号
	 * @param stepNumber
	 *            面单步长(间隔)
	 * @param specialNumber
	 *            特殊数字
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> generate(String wesbStartNo, String wesbEndNo,
			int stepNumber, int specialNumber) throws Exception {
		List<String> list = new ArrayList<String>();
		long totalCount = 0;
		String SAME_STR = ""; // 相同部分的字符
		int SAME_LEN = 0; // 相同部分的长度
		String FORMAT_STR = "";// 标准格式化
		int count = (wesbStartNo.length() + wesbEndNo.length()) / 2;
		for (int i = 0; i < count; i++) {
			int s = wesbStartNo.charAt(i);
			int d = wesbEndNo.charAt(i);
			if (s == d) {
				SAME_LEN++;
			} else {
				SAME_STR = wesbStartNo.substring(0, SAME_LEN);
				break;
			}
		}
		if (SAME_LEN == 0) {
			throw new Exception("面单号开头不一致");
		}
		String s1 = wesbStartNo.substring(SAME_LEN, count);
		String d1 = wesbEndNo.substring(SAME_LEN, count);
		long start = 0, end;
		try {
			start = Long.parseLong(s1);
			end = Long.parseLong(d1);
		} catch (Exception e) {
			throw new Exception("非数字");
		}
		if (start >= end) {
			throw new Exception("面单起始号大于或等于面单结束号");
		}
		// =========验证面单号码数据-----------结束-----
		int st = SAME_LEN > 0 ?( wesbStartNo.length() - SAME_LEN):count ;// 纯数字或字符串形式的长度匹配
		FORMAT_STR = "%0" + st + "d";

		for (long i = start; i <= end;) {
			if (i == start || i == end) {
				String code = SAME_STR + String.format(FORMAT_STR, i);
				list.add(code);
				System.out.println(code);
				totalCount++;
			} else if (i < end) {
				long val = 0;
				String str = i + "";
				String lastNumber = str.substring(str.length() - 1,
						str.length());
				if (Integer.parseInt(lastNumber) == specialNumber) {
					val = i - specialNumber;
				} else {
					val = i;
				}
				i = val;
				totalCount++;
				String code = SAME_STR + String.format(FORMAT_STR, i);
				list.add(code);
				System.out.println(code);
			}
			i += stepNumber;
		}
		System.out.println("共计:" + totalCount);
		return list;
	}
	
	/**
	 * 查找计算的面单号码是否在数据库已存在
	 * 
	 * @MethodName: isExistInDB 
	 * @Description: 
	 * @return
	 * @throws
	 */
	public static boolean isExistInDB(List<String> memCodes,List<String> dbCodes){
		if(memCodes==null||dbCodes==null){
			return false;
		}
		String []  memCodesArray =new String[memCodes.size()];
		String []  dbCodesArray =new String[dbCodes.size()];
		
		for (int i = 0; i < memCodesArray.length; i++) {
			memCodesArray[i]=memCodes.get(i);
		}
		for (int i = 0; i < dbCodesArray.length; i++) {
			dbCodesArray[i]=dbCodes.get(i);
		}
		
		for (String code : memCodesArray) {
			int result=binarySearch(dbCodesArray, code);
			if(result>0){
				System.out.println(code);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查找计算的面单号码是否在数据库已存在
	 * 
	 * @MethodName: isExistInDB 
	 * @Description: 
	 * @return
	 * @throws
	 */
	public static boolean isExistInDB(String code ,List<String> dbCodes){
		if(dbCodes==null){
			return false;
		}
		
		String []  dbCodesArray =new String[dbCodes.size()];
		
		for (int i = 0; i < dbCodesArray.length; i++) {
			dbCodesArray[i]=dbCodes.get(i);
		}
		
		int result=binarySearch(dbCodesArray, code);
		if(result>0){
			System.out.println(code);
			return true;
		}
		return false;
	}
	
	/**
	 * 二分查找
	 * 
	 * @MethodName: binarySearch
	 * @Description:
	 * @param a
	 *            : is object array
	 * @param x
	 *            : need to search object
	 * @return
	 * @throws
	 */
	public static <T extends Comparable<? super T>> int binarySearch(T[] a, T d) {
		int start = 0, end = a.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (a[mid].compareTo(d) < 0) {
				start = mid + 1;
			} else if (a[mid].compareTo(d) > 0) {
				end = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @MethodName: main 
	 * @Description: 
	 * @param args
	 * @throws
	 */
	public static void main(String[] args) {
		String s="201703070000000";
		String e="201703070000114";
		String n="201703070000125";
		
		List<String> memCodes=new ArrayList<String>();// 号段生成的号码
		memCodes.add(e);
		
		
		List<String> dbCodes=new ArrayList<String>();// 数据库存储的号码
		dbCodes.add(s);
		dbCodes.add(e);
		
		System.out.println("电子面单号段是否已使用完："+(memCodes.size()==dbCodes.size())); //生成的面单号码个数和已使用的相等 则表示面单号已使用完
		
		
		System.out.println(ECodeGenerator.isExistInDB(memCodes, dbCodes));// 验证号码集合是否存在重复
		
		System.out.println(ECodeGenerator.isExistInDB(n, dbCodes));// 验证号码是否存在重复
		
	}


}
