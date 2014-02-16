package sarel.graphalgorithm.tree;

import java.util.ArrayList;
import java.lang.annotation.*;


public class Btree {

	private Node rootT;
	private Node pivot;        //for rotation 
	
	private static int nodeCount;
	
	public Btree() {
		this.rootT = null;
		this.pivot = null;
		nodeCount = 0;
	}
	
	public boolean addElement(int newKey){
		
		if (this == null){
			System.out.printf("Tree is not Created%n");
			return false;
		}
		
		// if tree is empty , then put new key in root.
		if (this.rootT == null){
			this.rootT = new Node(newKey);
			nodeCount +=1;
			return true;
	}
		else {
			if (true == bTreeAddElement(this.rootT, newKey)){
				nodeCount +=1;
				return true;
			}
			else
				return false;
		}
	}
	private boolean bTreeAddElement(Node n, int newKey) {
		
		// if node key > key add to left tree
		if (n.key > newKey){
			if (n.l == null){
				n.l = new Node(newKey);
				return true;
			}
			return bTreeAddElement(n.l, newKey);
		}
		// if root key < key add to right tree
		if (n.key < newKey){
			if (n.r == null){
				n.r = new Node (newKey);
				return true;
			}
			return bTreeAddElement(n.r, newKey);
		}
		if (n.key == newKey){
			System.out
					.println("key " + newKey + " Already exist - not added");
			return false;
		}
		return true;
	}
	
	
	
	private boolean onNodeIsLeafRemoval(Node father, Node elmToRemove){
		
		if ( elmToRemove.l == null && elmToRemove.r == null ){
			if (father != null){
				if (father.l.hashCode() == elmToRemove.hashCode()){
					father.l = null;
				}
				else if (father.r == elmToRemove){
					father.r = null;
				}
			}
			nodeCount--;
			return true;
		}
		return false;
	}
	
	private boolean onNodeHavsOneChildRemoval(Node father, Node elmToRemove){
		// if elmToRemove have only one child, then we can link father with the child and remove reference to elmToRemove - hence remove it
		int childCount = 0;
		Node childNode  = null;
		
		if (elmToRemove.r != null){
			childCount +=1;
			childNode = elmToRemove.r;
		}
		if (elmToRemove.l != null){
			childCount +=1;
			childNode = elmToRemove.l;
		}
		
		if (childCount == 1){
			// find if elmToRemove is left or right child of father
			if (father.l.hashCode() == elmToRemove.hashCode()){
				father.l = childNode;
			}
			else if (father.r.hashCode() == elmToRemove.hashCode()){
				father.r = childNode;
			}
			nodeCount--;
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean onNodeHaveTwoChildrenRemoval(Node father, Node elmToRemove) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean removeNode(Node father, Node elmToRemove){
		System.out.printf("found Element to remove %d at reference %d%n",elmToRemove.getKey(), elmToRemove.hashCode());
		System.out.printf("Father HashCode is %d  Node Elm HashCode is %d%n", father.hashCode(), elmToRemove.hashCode());
		
		if (true == onNodeIsLeafRemoval(father, elmToRemove))
			return true;
		
		if (true == onNodeHavsOneChildRemoval(father, elmToRemove))
			return true;
		if (true == onNodeHaveTwoChildrenRemoval(father, elmToRemove) )
			return true;
		
		return false;
	}
	
	

	public boolean removeElement(int key){
		Node i = this.rootT;
		Node f = this.rootT;
		
		// find key Node (if exist) and its parent
		while (i != null){
			if (i.getKey() == key){
				return removeNode(f, i);
			}
			f = i;
			if (key > f.getKey()){
				// need to search the right sub tree
				i = f.r;
			}
			else { // key < f.getKey() --> need to search the left sub tree
				i = f.l;
			}
		}
		System.out.printf("Key %d is not in tree - nothing to remove%n", key);
		return false;
	}
	public int maxTreeHight(){
		return maxTreeHight(this.rootT);
	}
	
	private int maxTreeHight(Node n){
		int lh = 0;
		int rh = 0;
		
		if (n == null){
			return  0;
		}
		lh = 1 + maxTreeHight(n.l);
		rh = 1 + maxTreeHight(n.r);
		
		if (lh >= rh){
			return lh;
		}
		return rh ;
	}

	
	public void printTreePreOrder()
	{
		printTreePreOrder(this.rootT);
		System.out.println("\nprintTreePreOrder\n");
	}
	private void printTreePreOrder(Node r)
	{
		if (r == null)
			return;
		
		System.out.print("< "+r.key+" > ");
		printTreePreOrder(r.l);
		printTreePreOrder(r.r);
	}
	
	public void printTreePostOrder()
	{
		printTreePostOrder(this.rootT);
		System.out.println("\nprintTreePostOrder\n");
		
	}
	
	
	private void printTreeInOrder(Node r)
	{
		if (r == null)
			return;
		
		printTreeInOrder(r.l);
		System.out.print("< "+r.key+" > ");
		printTreeInOrder(r.r);
	}
	
	public void printTreeInOrder()
	{
		printTreeInOrder(this.rootT);
		System.out.println("\nprintTreeInOrder\n");
	}
	
	private void printTreePostOrder(Node r){
		if (r == null)
			return;
		
		printTreePostOrder(r.l);
		printTreePostOrder(r.r);
		System.out.print("< "+r.key+" > ");
		
	}
	
	public void bfsTreePrint(){
		ArrayList<Node> thisLevel = new ArrayList<Node>();
		thisLevel.add(this.rootT);
		int treeHight = maxTreeHight();
	
		printTreeLevel(thisLevel, 0, treeHight);
	}
	
	
	public void countNodesWithNoSibling()
	{
		
		System.out.printf("Number of single child Nodes in the tree is: %d%n", countNodesWithNoSibling(this.rootT, true));
		
	}
	
	private int countNodesWithNoSibling(Node n, boolean thisNodeHaveNoSiblings){
		
		int total = 0;
		
		if (thisNodeHaveNoSiblings){
			total++;
		}
		if ((n.l == null)&& (n.r == null)){
			// end the search 
			return total;
		}
		else if ((n.l == null) && (n.r != null)){
			return total + countNodesWithNoSibling(n.r, true);
		}
		else if ((n.l != null)&& (n.r == null)){
			return total + countNodesWithNoSibling(n.l, true);
		}
		else { // ((n.l != null) && (n.r != null))]
			return (total +  countNodesWithNoSibling(n.l, false) + countNodesWithNoSibling(n.r, false));
		}
	}

	public void printTreeLevel(ArrayList<Node> thisLevel, int level, int treeHight){
		int size = thisLevel.size();
		boolean nextLevelHaveElements = false;
		String formatString;
		Node n;
		boolean thisIsRoot = (level == 0) ? true : false;
		int firstNodeOffset= 1;
		int betweenNodesOffset = 1;
		String formatStringFirstNode;
		
		if (treeHight == level +1)
		{
			// printing the lowest level of the tree - first node offset is 0
			firstNodeOffset = 0;
			formatStringFirstNode = "<%2$" + 3 + "s>";	
		}
		else
		{
			firstNodeOffset <<=(treeHight - (level+1));
			firstNodeOffset -=1;
			formatStringFirstNode = "%" + (firstNodeOffset*Node.NODE_PRINT_SIZE) + "s<" + "%" + 3 + "s>"; 
		}
		if (!thisIsRoot){
			betweenNodesOffset <<= (treeHight - level);
			betweenNodesOffset -=1;
		}
		String formatStringBetweetweenNode = "%" + (betweenNodesOffset*Node.NODE_PRINT_SIZE) + "s<" + "%" + 3 + "s>"; 

		ArrayList<Node> nextLevel = new ArrayList<Node>();
		
		// print all element in this level		
		for (int i = 0; i < size; i++) {
			if (i == 0)
				formatString = formatStringFirstNode;	
			else
				formatString = formatStringBetweetweenNode;
		
			// print the elements in this level
			n = thisLevel.get(i);
			if(n == null){
				System.out.format(formatString," ","   ");
				nextLevel.add(null);
				nextLevel.add(null);
			}
			else{
				System.out.format(formatString," ",n.getKey());
				// explore next level by pushing current level descendant L & R vertices to nextLevel queue 	
				nextLevel.add(n.l);
				nextLevel.add(n.r);
				if (n.l != null || n.r != null)
					nextLevelHaveElements = true;
			}	
		}
		System.out.format("%n");
		
		if (nextLevelHaveElements){
			printTreeLevel(nextLevel, level+1, treeHight);
		}
	}
	

	

	
	public static void main(String[] args) {
		Btree t = new Btree();
		
	t.addElement(100);
		
		t.addElement(50);
		t.addElement(70);

		t.addElement(500);
    	t.addElement(25);
		t.addElement(10);
	//	t.addElement(15);
	//	t.addElement(7);
	//	t.addElement(654);
		
//	    t.removeElement(7);
//	    t.removeElement(10);
		
	    
		
		
//		t.printTreeInOrder();
//		t.printTreePostOrder();
//		t.printTreePreOrder();
		
		t.bfsTreePrint();
//		t.countNodesWithNoSibling();
	    
//	    String inOrderTree = "1,2,3,4,7";
//	    TreeBuilder.constructTreeFromTraversalList(t, Traversal.IN_ORDER, inOrderTree);
//	    
//	    t.bfsTreePrint();
//	    t.printTreePostOrder();
	   
    
	}
}
	

