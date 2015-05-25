package com.demo001;



/*
 * 
 * 实现一个算法来判断一个字符串中的字符是否唯一(即没有重复).不能使用额外的数据结构。 (即只使用基本的数据结构)
 * 
 * */
public class Chars {

	public static void main(String[] args) {

		boolean r1=isUniqueChars1("zxcvbnm");
		System.out.println("r1="+r1);
		boolean r2=isUniqueChars2("zxcvbnmZ");
		System.out.println("r2="+r2);
		boolean r3=isUniqueChars3("zxcvbnmZ*^%$*");
		System.out.println("r3="+r3);
	}

	
	//按位来操作，int型32位，可以在不区分大小写的情况下对应26个字母
	private static boolean isUniqueChars1(String string) {
		char[] chars=string.toCharArray();
		int checker=0;//用于记录每个字母对应位上的出现的情况，默认为0
		for (int i = 0; i < chars.length; i++) {
			int a=string.charAt(i)-'a';//'a'=97,'A'=65	
			if ((checker&(1<<a))>0) return false;//对应位做与操作，如果已经出现过则结束
			checker|=(1<<a);//给对应位赋值为1
		}
		return true;
	}

	
	//方法一是在不考虑大小写的情况下，本方法兼容大小写，用long型64位对应大小写共计52个字母
	private static boolean isUniqueChars2(String string) {
		char[] chars=string.toCharArray();
		long checker=0,a=0;//用于记录每个字母对应位上的出现的情况，默认为0
		for (int i = 0; i < chars.length; i++) {
			if ((string.charAt(i)-0)>=97) {
				a=string.charAt(i)-'a'+26;
			}else {
				a=string.charAt(i)-'A';
			}
			if ((checker&(1<<a))>0) return false;//对应位做与操作，如果已经出现过则结束
			checker|=(1<<a);//给对应位赋值为1
		}
		return true;
	}
	
	//方法3,兼容ASCII字符集,
	private static boolean isUniqueChars3(String string) {
		char[] chars=string.toCharArray();
		boolean check[]=new boolean[256];//用于记录每个字母对应位上的出现的情况，默认为0
		for (int i = 0; i < chars.length; i++) {
			int a=string.charAt(i);
			if (check[a]) return false; 
			check[a]=true;
		}
		return true;
	}
}
