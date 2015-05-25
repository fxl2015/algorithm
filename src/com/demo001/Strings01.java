package com.demo001;



/*
 * 
 * 设计算法并写出代码移除字符串中重复的字符，不能使用额外的缓存空间。注意： 可以使用额外的一个或两个变量，但不允许额外再开一个数组拷贝。
 * 如果根本就不允许你再开一个数组，只能用额外的一到两个变量。
 * 那么，你可以依次访问 这个数组的每个元素，每访问一个，就将该元素到字符串结尾的元素中相同的元素去掉( 比如置为’’).时间复杂度为O(n2 )
 * */
public class Strings01 {

	public static void main(String[] args) {

		String s=removeDuplicates2("aaaaaaaaaatttddddddddd");
		System.out.println("*****"+s);
	//	removeDuplicates2("");
	}

	
	//如果可以开一个固定大小，与问题规模(即字符串长度)无关的数组，
	//那么可以用一个数组来 表征每个字符的出现(假设是ASCII字符，则数组大小为256)，
	//这样的话只需要遍历一遍字符 串即可，时间复杂度O(n)。
	private static String removeDuplicates2(String string) {
		boolean check[]=new boolean[256];
		char[] chars=string.toCharArray();
		for (int i = 0; i < string.length(); i++) {
			if (check[string.charAt(i)-0]) {
				chars[i]=' ';
			}else {
				check[string.charAt(i)-0]=true;
			}
		}
		return new String(chars).replaceAll(" ","");
	}

	private static String removeDuplicates1(String string) {
		char[] chars=string.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			for (int j = i+1; j < chars.length; j++) {
				if (string.charAt(i)==string.charAt(j)) {
					chars[j]=' ';
				}
			}
		}
		
		return new String(chars).replaceAll(" ","");
	}

	
	
}
