package com.demo001;



/*
 * 
 * 写一个函数判断两个字符串是否是变位词。
 * 变位词(anagrams)指的是组成两个单词的字符相同，但位置不同的单词。
 * 比如说， abbcd和abcdb就是一对变位词。该题目有两种做法：
 * */
public class Strings02 {

	public static void main(String[] args) {

		boolean r=anagram("abcdef","abcfed");
		System.out.println("r="+r);
	}

	private static boolean anagram(String str1, String str2) {
		if (str1.length()!=str2.length()) {
			return false;
		}
		boolean result=true;
		int[] check=new int[256];
		for (int i = 0; i < str1.length(); i++) {
			check[str1.charAt(i)-0]+=1;
			check[str2.charAt(i)-0]-=1;
		}
		
		for (int i = 0; i < check.length; i++) {
			System.out.print(check[i]);
			if (check[i]!=0) {
				result= false;
				break;
			}
		}
		
		return result;
	}

}
