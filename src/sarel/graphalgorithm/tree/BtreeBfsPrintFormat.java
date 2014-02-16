/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sarel.graphalgorithm.tree;

/**
 *
 * @author Sarel
 */
public class BtreeBfsPrintFormat{
	String nodeSpace;
	
	String prefixSpace;
	String inlinelSpace;
	
	int prefixMultiplier;
	int inlineMultiplier;
	
	int treeHight;
	int thisLevel;
	

	public BtreeBfsPrintFormat(int level, int treeHight) {
		
		nodeSpace = "     ";
		prefixSpace =" ";
		inlinelSpace = prefixSpace;
	
		prefixMultiplier = 1;
		inlineMultiplier = 1;
		
		// calculate prefix offset
		if ( treeHight - (2 + level ) < 0){
			return;
		}
	
		prefixMultiplier >>= (treeHight- (2 +level));
		prefixMultiplier -=1;
		while(prefixMultiplier-- != 0){
			prefixSpace.concat(nodeSpace);
		}
		
		inlineMultiplier >>= (treeHight -2);
		inlineMultiplier -=1;
		while(inlineMultiplier-- != 0){
			inlinelSpace.concat(nodeSpace);
		}
	}

	public String getPrefixSpace() {
		return prefixSpace;
	}

	public String getInlinelSpace() {
		return inlinelSpace;
	}
}
