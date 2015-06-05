package com.tree;


/*
 * 
 * 2015-06-05
 * 
 * 赫夫曼树（Huffman Tree），又称最优二叉树，是一类带权路径长度最短的树。
 * 假设有n个权值{w1,w2,...,wn}，如果构造一棵有n个叶子节点的二叉树，
 * 而这n个叶子节点的权值是{w1,w2,...,wn}，则所构造出的带权路径长度最小的二叉树就被称为赫夫曼树。

    这里补充下树的带权路径长度的概念。树的带权路径长度指树中所有叶子节点到根节点的路径长度与该叶子节点权值的乘积之和，
    如果在一棵二叉树中共有n个叶子节点，用Wi表示第i个叶子节点的权值，Li表示第i个也叶子节点到根节点的路径长度，
    则该二叉树的带权路径长度 WPL=W1*L1 + W2*L2 + ... Wn*Ln。

    根据节点的个数以及权值的不同，赫夫曼树的形状也各不相同，赫夫曼树具有如下特性：

    对于同一组权值，所能得到的赫夫曼树不一定是唯一的。
    赫夫曼树的左右子树可以互换，因为这并不影响树的带权路径长度。
    带权值的节点都是叶子节点，不带权值的节点都是某棵子二叉树的根节点。
    权值越大的节点越靠近赫夫曼树的根节点，权值越小的节点越远离赫夫曼树的根节点。
    赫夫曼树中只有叶子节点和度为2的节点，没有度为1的节点。
    一棵有n个叶子节点的赫夫曼树共有2n-1个节点。
*/
public class HuffmanTree {

	private HuffmanNode mRoot;	// 根结点

	/* 
	 * 创建Huffman树
	 *
	 * @param 权值数组
	 */
	public HuffmanTree(int a[]) {
        HuffmanNode parent = null;
		MinHeap heap;

		// 建立数组a对应的最小堆
		heap = new MinHeap(a);
	 
		for(int i=0; i<a.length-1; i++) {   
        	HuffmanNode left = heap.dumpFromMinimum();  // 最小节点是左孩子
        	HuffmanNode right = heap.dumpFromMinimum(); // 其次才是右孩子
	 
			// 新建parent节点，左右孩子分别是left/right；
			// parent的大小是左右孩子之和
			parent = new HuffmanNode(left.key+right.key, left, right, null);
			left.parent = parent;
			right.parent = parent;

			// 将parent节点数据拷贝到"最小堆"中
			heap.insert(parent);
		}

		mRoot = parent;

		// 销毁最小堆
		heap.destroy();
	}

	/*
	 * 前序遍历"Huffman树"
	 */
	private void preOrder(HuffmanNode tree) {
		if(tree != null) {
			System.out.print(tree.key+" ");
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}

	public void preOrder() {
		preOrder(mRoot);
	}

	/*
	 * 中序遍历"Huffman树"
	 */
	private void inOrder(HuffmanNode tree) {
		if(tree != null) {
			inOrder(tree.left);
			System.out.print(tree.key+" ");
			inOrder(tree.right);
		}
	}

	public void inOrder() {
		inOrder(mRoot);
	}


	/*
	 * 后序遍历"Huffman树"
	 */
	private void postOrder(HuffmanNode tree) {
		if(tree != null)
		{
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.key+" ");
		}
	}

	public void postOrder() {
		postOrder(mRoot);
	}

	/*
	 * 销毁Huffman树
	 */
	private void destroy(HuffmanNode tree) {
		if (tree==null)
			return ;

		if (tree.left != null)
			destroy(tree.left);
		if (tree.right != null)
			destroy(tree.right);

		tree=null;
	}

	public void destroy() {
		destroy(mRoot);
		mRoot = null;
	}

	/*
	 * 打印"Huffman树"
	 *
	 * key        -- 节点的键值 
	 * direction  --  0，表示该节点是根节点;
	 *               -1，表示该节点是它的父结点的左孩子;
	 *                1，表示该节点是它的父结点的右孩子。
	 */
	private void print(HuffmanNode tree, int key, int direction) {

		if(tree != null) {

			if(direction==0)	// tree是根节点
				System.out.printf("%2d is root\n", tree.key);
			else				// tree是分支节点
				System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction==1?"right" : "left");

			print(tree.left, tree.key, -1);
			print(tree.right,tree.key,  1);
		}
	}

	public void print() {
		if (mRoot != null)
			print(mRoot, mRoot.key, 0);
	}
}
