/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sarel.graphalgorithm.tree;

/**
 *
 * @author Sarel
 */


public class TreeBuilder {
	
	
	public TreeBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int log2OfNum(int num){
		return (int) (Math.log(num)/Math.log(2)) + 1 ;
	}
	private int appLog2OfNumUsingShift(int num){
		int appLog = 0;
		while (num > 0){
			num >>=1;
			appLog++;
		}
		return appLog;
	}
	private static int sizeOfFullBstThatHas(int elmCntInTree){

		if (elmCntInTree < 2 ){
			return elmCntInTree;
		}
		else{
			int maxSize = 1;
			while (elmCntInTree > 0){
				elmCntInTree >>=1;
				maxSize <<=1;;
			}
			return maxSize -1;
		}
	}
	
	
	private static void constructBstInOrder(Btree t, String[] nodes){
		
		int numOfNodesInTree = nodes.length;
		int maxNumOfNodeInFullTree = sizeOfFullBstThatHas(numOfNodesInTree);
		
		if (numOfNodesInTree == 1){
			t.addElement(Integer.parseInt(nodes[0]));
			//System.out.printf("Add node %d%n",Integer.parseInt(nodes[0]) );
			return;
		}
		
		int numInLeftSubTree = maxNumOfNodeInFullTree/2;
		int numInRightSubTree = numOfNodesInTree - numInLeftSubTree -1;
		
		
//	   // System.out.printf("Nodes %d   Max Nodes %d   Nodes in SubLeft %d   Nodes in SubRight %d%n",
//	    				   numOfNodesInTree,  
//	    				   maxNumOfNodeInFullTree,
//	    				   numInLeftSubTree,
//	    				   numInRightSubTree);
		

		// add the root of the tree
		t.addElement(Integer.parseInt(nodes[numInLeftSubTree]));
	    //System.out.printf("Root--- Add node %d%n",Integer.parseInt(nodes[numInLeftSubTree]) );
	  
		
		//get the left sub tree nodes
		String[] leftTree = new String[numInLeftSubTree];
		//System.out.printf("LeftTree --- %n" );
		for (int i = 0; i < numInLeftSubTree ; i++){
			leftTree[i] = nodes[i];
			//System.out.printf("%s ",leftTree[i] );
		}
		//System.out.printf("%n");
		constructBstInOrder(t, leftTree);

		// get the right subtree
		if (numInRightSubTree > 0 ){
			String[] rightTree = new String[numInRightSubTree];
			//System.out.printf("Right Tree --- %n" );
			for (int i = 0; i < numInRightSubTree; i++){
				rightTree[i] = nodes[numInLeftSubTree+1+i];
				//System.out.printf("%s ",rightTree[i] );
			}
			//System.out.printf("%n");
            constructBstInOrder(t, rightTree);
		}
	}
	public static  void constructTreeFromTraversalList(Btree t,  Traversal type, String combinedNodeList){
		
		String[] splitNodeList = combinedNodeList.split(",");
		
		
		
		switch (type){
			case IN_ORDER:{
		
				constructBstInOrder(t, splitNodeList);			
			}
				
			case PRE_ORDER:{
				
			}
				
			case POST_ORDER:{
				
			}
		}
	}


	
	
	
	
	
	
	

}
