package ai;

public class Node {
	public Node parent;
	public int col, row;
	public int gCost, hCost, fCost;
	public boolean solid, open, checked;

	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}
}
