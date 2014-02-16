package sarel.graphalgorithm.tree;

public class Node {
	
	int key;        // value of node
	int bf;			//balancing factor 
	int level;

	static final int NODE_PRINT_SIZE = 5;
	static int objCounter;
	
	Node l;
	Node r;
	
	public int getKey() {
		return key;
	}

	public Node(int key) {
		this.key = key;
		this.bf = 0;
		this.level = 0;

		this.l = null;
		this.r = null;
		objCounter++;
	}

	public static int getObjCounter() {
		return objCounter;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int childrenCount()
	{
		int maxChildren = 2;
		
		if (this.r == null)
			maxChildren--;
		if (this.l == null)
			maxChildren--;
		
		return maxChildren;
		
	}
	
	
}