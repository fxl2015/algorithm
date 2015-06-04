package com.tree;

import java.util.Comparator;
import java.util.Random;

import javax.swing.text.rtf.RTFEditorKit;

/*
 * 
 * 2015-06-03
 * 
 */
public class BinarySearchTreeDemo {

	public static void main(String[] args) {

		BinarySearchTree<Integer> bst=new BinarySearchTree<>();
		Random random=new Random();
		int x=0;
		int[] array={87,57,93,21,56,30,18,76,39,44};
		for (int a: array) {
			System.out.print(a+" ");
			bst.insert(a);
		}
		x=56;
		System.out.println();
	//	bst.preOrder();
	//	System.out.println();
		bst.midOrder();
		System.out.println();
	//	bst.postOrder();
	//	System.out.println();
		BSNode node1=bst.search(x);
	//	System.out.println("bst.search("+x+").leftNode.key="+node.leftNode.key);
	//	System.out.println("bst.search("+x+").rightNode.key="+node.rightNode.key);
		System.out.println("bst.search("+x+").parentNode.key="+node1.parentNode.key);
		BSNode node2=bst.iterativeSearch(x);
		System.out.println("bst.search("+x+").parentNode.key="+node2.parentNode.key);
		BSNode node3=bst.minimum();
        System.out.println("minimum="+node3.key);
        BSNode node4=bst.maximum();
        System.out.println("maximum="+node4.key);
//        BSNode node5=bst.successor(x);
//        System.out.println("bst.successor("+x+").key="+node5.key);
//        BSNode node6=bst.predecessor(x);
//        System.out.println("bst.predecessor("+x+").key="+node6.key);
//        bst.remove(21);
        bst.preOrder();
		System.out.println();
        bst.print();
	}

}

class BSNode<T extends Comparable<T>>{
	T key;//节点的值
	BSNode<T> parentNode;//父节点
	BSNode<T> leftNode;//左子节点
	BSNode<T> rightNode;//右子节点
	
	//节点初始化
	public BSNode(T key,BSNode<T> parentNode,BSNode<T> leftNode,BSNode<T> rightNode){
		this.key=key;
		this.parentNode=parentNode;
		this.leftNode=leftNode;
		this.rightNode=rightNode;
	}
	
	public T getKey(){
		return key;
	}
	
	public String toString(){
		return key+"  ";
	}
}


class BinarySearchTree<T extends Comparable<T>>{
	
	private BSNode<T> parentNode;
	
	//空二叉树
	public BinarySearchTree(){
		parentNode=null;
	}
	
	//前序遍历二叉树
	private void preOrder(BSNode<T> node){
		if (node!=null) {
			System.out.print(node.toString());
			preOrder(node.leftNode);
			preOrder(node.rightNode);
		}
	}
	
	public void preOrder(){
		preOrder(parentNode);
	} 
	
	
	//中序遍历二叉树
	private void midOrder(BSNode<T> node){
        if (node!=null) {
			midOrder(node.leftNode);
			System.out.print(node.toString());
			midOrder(node.rightNode);
		}		
	}
	
	public void midOrder(){
		midOrder(parentNode);
	}
	
	//后序遍历二叉树
	private void postOrder(BSNode<T> node){
		if (node!=null) {
			postOrder(node.leftNode);
			postOrder(node.rightNode);
			System.out.print(node.toString());	
		}
	}
	
	public void postOrder(){
		postOrder(parentNode);
	}
	
	//插入节点
	private void insert(BSNode<T> node){
		if (node!=null) {
			if (parentNode==null) {
				parentNode=node;
				return;
			}
			BSNode<T> tmpNode1=parentNode;//从根节点开始遍历
			BSNode<T> tmpNode2=null;    //保存要插入位置的父节点
			while (tmpNode1!=null) {
				tmpNode2=tmpNode1;
				if ((tmpNode1.key.compareTo(node.key))>=0) {
					tmpNode1=tmpNode1.leftNode;
				}else {
					tmpNode1=tmpNode1.rightNode;
				}
			}
			
			//找到了位置，开始插入节点
			node.parentNode=tmpNode2;
			if ((tmpNode2.key.compareTo(node.key))>=0) {
				tmpNode2.leftNode=node;
			}else {
				tmpNode2.rightNode=node;
			}
		}
	}
	
	public void insert(T key){
		BSNode<T> node=new BSNode(key, null, null, null);
	    insert(node);
	}
	
	
	/*
	 * (递归实现)查找"二叉树x"中键值为key的节点
	 */
	private BSNode<T> search(BSNode<T> node,T key){
		if (node==null) {
			return node;
		}
		int cmp=key.compareTo(node.key);
		if (cmp<0) {
		    return search(node.leftNode, key);
		}else if(cmp>0){
			return search(node.rightNode, key);
		}else {
			return node;
		}
	}
	
	public BSNode<T> search(T key){
		return search(parentNode, key);
	}
	
	/*
	 * (非递归实现)查找"二叉树x"中键值为key的节点
	 */
	private BSNode<T> iterativeSearch(BSNode<T> node,T key){
		while (node!=null) {

			if (node.key.compareTo(key)>0) {
				node=node.leftNode;
			}else if(node.key.compareTo(key)<0){
				node=node.rightNode;
			}else {
				return node;
			}
		}
		return node;
	}
	public BSNode<T> iterativeSearch(T key){
		return iterativeSearch(parentNode, key);
	}
	
	/* 
	 * 查找最小结点：返回parentNode为根结点的二叉树的最小结点。
	 */
	private BSNode<T> minimum(BSNode<T> node){
		if (node==null) {
			return node;
		}
		BSNode<T> tmpNode1=node;
		BSNode<T> tmpNode2=null;
		while (tmpNode1!=null) {
            tmpNode2=tmpNode1;
			tmpNode1=tmpNode1.leftNode;
		}
		return tmpNode2;
	}
	
	public BSNode<T> minimum(){
		return minimum(parentNode);
	}
	
	/* 
	 * 查找最大结点：返回parentNode为根结点的二叉树的最大结点。
	 */
	private BSNode<T> maximum(BSNode<T> node){
		if (node==null) {
			return node;
		}
		
		BSNode<T> tmpNode1=node;
		BSNode<T> tmpNode2=null;
		while (tmpNode1!=null) {
            tmpNode2=tmpNode1;
            tmpNode1=tmpNode1.rightNode;
		}
		
		return tmpNode2;
	}
	
	public BSNode<T> maximum(){
		return maximum(parentNode);
	}
	
	
	
	/* 
	 * 查找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
	 */
	private BSNode<T> successor(BSNode<T> x) {
		if (x==null) {
			return x;
		}
		// 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
		if (x.rightNode!=null) {
			return minimum(x.rightNode);
		}
		
		// 如果x没有右孩子。则x有以下两种可能：
		// (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
		// (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
		BSNode<T> parentNode=x.parentNode;
		while ((parentNode!=null)&&(x==parentNode.rightNode)) {
             parentNode=x;
             parentNode=parentNode.parentNode;
		}
		return parentNode;
	}
	
	public BSNode<T> successor(T key){
		return successor(search(key));
	}
	
	/* 
	 * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
	 */
	private BSNode<T> predecessor(BSNode<T> x) {
		if (x==null) {
			return x;
		}
		// 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
		if (x.leftNode!=null) {
			return maximum(x.leftNode);
		}
		
		
		// 如果x没有左孩子。则x有以下两种可能：
	    // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
		// (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
		BSNode<T> parentNode=x.parentNode;
		while ((parentNode!=null)&&(x==parentNode.leftNode)) {
            x=parentNode;
            parentNode=parentNode.parentNode;
		}
		return parentNode;
	}
	
	public BSNode<T> predecessor(T key){
		return predecessor(search(key));
	}
	
	/* 
	 * 删除结点(z)，并返回被删除的结点
	 *
	 * 参数说明：
	 *     bst 二叉树
	 *     z 删除的结点
	 */
	private BSNode<T> remove(BinarySearchTree<T> bst,BSNode<T> z){
		BSNode<T> x=null;
		BSNode<T> y=null;
        
		//如果z的左右子树至少有一个为空,则y保存z
		if ((z.leftNode==null)||(z.rightNode==null)) {
			y=z;
		}else {//如果z的左右子树都不为空,则y保存z的直接前驱节点
			y=successor(z);
		}
		
		//如果y的左子树不等于null,则x保存y的左子树
		if (y.leftNode!=null) {
			x=y.leftNode;
		}else {//如果y的左子树等于null,则x保存y的右子树
			x=y.rightNode;
		}
		
		//如果x不等于null,则把x的父节点保存y的父节点的值
		if (x!=null) {
			x.parentNode=y.parentNode;
		}
		
		//如果y的父节点的值为空,则把x作为该二叉树的根节点
		if (y.parentNode==null) {
			bst.parentNode=x;
		}else if(y==y.parentNode.leftNode){//如果y等于y的父节点的左子树,则把x作为y的父节点的左子树
			y.parentNode.leftNode=x;
		}else {//如果y等于y的父节点的右子树,则把x作为y的父节点的右子树
			y.parentNode.rightNode=x;
		}
		
		//如果y不等于z,则把z的key赋值给y的key
		if (y!=z) {
			z.key=y.key;
		}
		
		//返回删除的节点
        return y;		
	}
	
	/* 
	 * 删除结点(z)，并返回删除结果  true/false
	 *
	 * 参数说明：
	 *     tree 二叉树的根结点
	 *     z 删除的结点
	 */
	public boolean remove(T key){
		BSNode<T> z,node;
		boolean flag=false;
		z=search(parentNode, key);
		if (z!=null) {
			node=remove(this, z);
			if (node!=null) {
				node=null;
				flag=true;
			}
		}
		return flag;
	}
	
	/*
	 * 打印"二叉查找树"
	 *
	 * key        -- 节点的键值 
	 * direction  --  0，表示该节点是根节点;
	 *               -1，表示该节点是它的父结点的左孩子;
	 *                1，表示该节点是它的父结点的右孩子。
	 */
	private void print(BSNode<T> tree, T key, int direction) {

	    if(tree != null) {

	        if(direction==0)    // tree是根节点
	            System.out.printf("%2d is root\n", tree.key);
	        else                // tree是分支节点
	            System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

	        print(tree.leftNode, tree.key, -1);
	        print(tree.rightNode,tree.key,  1);
	    }
	}

	public void print() {
	    if (parentNode != null)
	        print(parentNode, parentNode.key, 0);
	}
	
	/*
	 * 销毁二叉树
	 */
	private void destroy(BSNode<T> tree) {
	    if (tree==null)
	        return ;

	    if (tree.leftNode != null)
	        destroy(tree.leftNode);
	    if (tree.rightNode != null)
	        destroy(tree.rightNode);

	    tree=null;
	}

	public void clear() {
	    destroy(parentNode);
	    parentNode = null;
	}
}