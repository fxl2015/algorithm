package com.link;

import java.util.Random;

public class SingleLinkTest {

	public static void main(String[] args) {
		
		test();

	}

	private static void test() {
		
		SingleLink<Integer> sLink=new SingleLink<>();
		Random r=new Random();
		for (int i = 0; i < 10; i++) {
			SingleLinkNode<Integer> node=new SingleLinkNode<Integer>(r.nextInt(100),null);
			sLink.insertFirst(node);
		}
        
	//	sLink.printSingleLink();
		
	//	System.out.println("sLink.getNode(5)="+sLink.getNode(5).value);
	//	sLink.deleteNode(51);
		sLink.printSingleLink();
	//	System.out.println("sLink.getNode(5)="+sLink.getNode(5).value);
		sLink.insertBeforeIndex(3,new SingleLinkNode(10000, null));
		sLink.printSingleLink();
		System.out.println(sLink.getLast().value);
		sLink.insertEnd(new SingleLinkNode(100000, null));

		sLink.printSingleLink();
	}

}
