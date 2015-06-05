package com.link;

public class SingleLink<T> {
	
	private SingleLinkNode<T> mHead;
	private int mCount;
	
	
	public SingleLink(){
		mHead=new SingleLinkNode<T>(null, null);
		mHead.next=mHead;
		mCount=0;
	}
	
	public SingleLinkNode<T> getHead(){
		return mHead;
	}
	
	//获取单链表长度
	public int size(){
		return mCount;
	}
	
	//判断列表是否为空
	public boolean isEmpty(){
		return mCount==0;
	}

	//获取尾节点
	public SingleLinkNode getLast(){
		SingleLinkNode<T> last=mHead.next;
		int i=0;
		while (i<mCount-1) {
			last=last.next;
			i++;
		}
		return last;
	}
	
	//头插法插入节点
	public void insertFirst(SingleLinkNode node){
		SingleLinkNode temp=mHead.next;
		mHead.next=node;
		node.next=temp;
		mCount++;
	}
	
	//打印单链表
	public void printSingleLink(){
		SingleLinkNode<T> next=mHead.next;
		int i=0;
		while (i<mCount) {
			System.out.print(next.value+" ");
			next=next.next;
			i++;
		}
		System.out.println();
	}
	
	// 获取第index位置的节点
	public SingleLinkNode getNode(int index){
		if (index>mCount||index<0) {
			return null;
		}
		SingleLinkNode indexNode=mHead.next;
		for (int i =0; i <index-1; i++) {
			indexNode=indexNode.next;
		}
		
		return indexNode;
	}
	
	//删除第index位置的节点
	public void deleteNode(int index){
		if (index>mCount||index<0) {
			return ;
		}
		SingleLinkNode beforeNode=mHead.next;
		SingleLinkNode nextNode=beforeNode.next;
		for (int i = 1; i < index-1; i++) {
			beforeNode=nextNode;
			nextNode=nextNode.next;		
		}
		
		beforeNode.next=nextNode.next;
		nextNode=null;
		mCount--;
	}
	
	//将节点插入到第index位置之前
	public void insertBeforeIndex(int index,SingleLinkNode node){
		if (index>mCount||index<0) {
			return;
		}		
		node.next=getNode(index-1).next;
		getNode(index-1).next=node;	
		mCount++;
	}
	//https://github.com/fxl2015/algorithm.git
	//将节点插入到链表末尾
	public void insertEnd(SingleLinkNode node){
		getLast().next=node;
		mCount++;
	}
	
	public void insertEnd(T key){
		SingleLinkNode<T> node=new SingleLinkNode<>(key, null);
        insertEnd(node);
	}
}




class SingleLinkNode<T>{
   
	public SingleLinkNode<T> next;
	public T value;
	
	public SingleLinkNode(T value,SingleLinkNode<T> next){
		this.value=value;
		this.next=next;
	}
}
