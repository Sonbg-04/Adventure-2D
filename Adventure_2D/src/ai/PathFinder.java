package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {
	public GamePanel gp;
	public Node[][] n;
	public ArrayList<Node> pathList = new ArrayList<>();
	public ArrayList<Node> openList = new ArrayList<>();
	public Node startNode, goalNode, currentNode;
	public boolean goalReached = false;
	public int step = 0;

	public PathFinder(GamePanel gp) {
		this.gp = gp;
		this.instantiateNodes();
	}

	public void instantiateNodes() {
		this.n = new Node[this.gp.maxWorldCol][this.gp.maxWorldRow];
		int col = 0, row = 0;
		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			this.n[col][row] = new Node(col, row);
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void resetNodes() {
		int col = 0;
		int row = 0;

		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {
			this.n[col][row].open = false;
			this.n[col][row].checked = false;
			this.n[col][row].solid = false;
			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}

		// RESET OTHER SETTINGS
		this.openList.clear();
		this.pathList.clear();
		this.goalReached = false;
		this.step = 0;
	}

	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity ent) {
		this.resetNodes();

		// SET START AND GOAL NODE
		this.startNode = this.n[startCol][startRow];
		this.currentNode = this.startNode;
		this.goalNode = this.n[goalCol][goalRow];
		this.openList.add(this.currentNode);

		int col = 0;
		int row = 0;

		while (col < this.gp.maxWorldCol && row < this.gp.maxWorldRow) {

			// SET SOLID NODE
			// CHECK TILE
			int tileNum = this.gp.tmg.mapTileNum[this.gp.currentMap][col][row];
			if (this.gp.tmg.tile[tileNum].collision == true) {
				this.n[col][row].solid = true;
			}

			// CHECK INTERACTIVE TILES
			for (int i = 0; i < this.gp.iTile[1].length; i++) {
				if (this.gp.iTile[this.gp.currentMap][i] != null && this.gp.iTile[this.gp.currentMap][i].destructible == true) {
					int itcol = this.gp.iTile[this.gp.currentMap][i].worldX / this.gp.tileSize;
					int itrow = this.gp.iTile[this.gp.currentMap][i].worldY / this.gp.tileSize;
					this.n[itcol][itrow].solid = true;
				}
			}

			// SET COST
			this.getCost(this.n[col][row]);

			col++;
			if (col == this.gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void getCost(Node n) {

		// G COST
		int xDistance = Math.abs(n.col - this.startNode.col);
		int yDistance = Math.abs(n.row - this.startNode.row);
		n.gCost = xDistance + yDistance;

		// H COST
		int x1Distance = Math.abs(n.col - this.goalNode.col);
		int y1Distance = Math.abs(n.row - this.goalNode.row);
		n.hCost = x1Distance + y1Distance;

		// F COST
		n.fCost = n.gCost + n.hCost;

	}

	public boolean Search() {
		while (this.goalReached == false && this.step < 100) {
			int col = this.currentNode.col;
			int row = this.currentNode.row;

			// CHECK THE CURRENT NODE
			this.currentNode.checked = true;
			this.openList.remove(this.currentNode);

			// OPEN THE UP NODE
			if (row - 1 >= 0) {
				this.OpenNode(this.n[col][row - 1]);
			}

			// OPEN THE LEFT NODE
			if (col - 1 >= 0) {
				this.OpenNode(this.n[col - 1][row]);
			}

			// OPEN THE DOWN NODE
			if (row + 1 < this.gp.maxWorldRow) {
				this.OpenNode(this.n[col][row + 1]);
			}

			// OPEN THE RIGHT NODE
			if (col + 1 < this.gp.maxWorldCol) {
				this.OpenNode(this.n[col + 1][row]);
			}

			// FIND THE BEST NODE
			int bestNodeI = 0;
			int bestNodeFcost = 999;
			for (int i = 0; i < this.openList.size(); i++) {
				if (this.openList.get(i).fCost < bestNodeFcost) {
					bestNodeI = i;
					bestNodeFcost = this.openList.get(i).fCost;
				} else if (this.openList.get(i).fCost == bestNodeFcost) {
					if (this.openList.get(i).gCost < this.openList.get(bestNodeI).gCost) {
						bestNodeI = i;
					}
				}
			}
			if (this.openList.size() == 0) {
				break;
			}

			this.currentNode = this.openList.get(bestNodeI);
			if (this.currentNode == this.goalNode) {
				this.goalReached = true;
				this.trackthePath();
			}
			this.step++;
		}
		return this.goalReached;
	}

	public void OpenNode(Node n) {
		if (n.open == false && n.checked == false && n.solid == false) {
			n.open = true;
			n.parent = this.currentNode;
			this.openList.add(n);
		}
	}

	public void trackthePath() {
		Node current = this.goalNode;
		while (current != this.startNode) {
			this.pathList.add(0, current);
			current = current.parent;
		}
	}
}
