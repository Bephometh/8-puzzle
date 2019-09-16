package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class AStar {
	private Node node;
	private Node curr;
	private Enviroment goal;
	private Enviroment state;
	private ArrayList<Node> openList;
	private ArrayList <Node> closedList;
	private ArrayList<Node> steps;
	private int totalStep;
	private Actuator act;
	
	
	public AStar(int[][] initState){
		this.state = new Enviroment(initState);
		this.node = new Node(null ,this.state,0, 0);
		int[][] endState = {{1,2,3},{4,5,6},{7,8,0}};
		this.goal = new Enviroment(endState);
		this.openList = new ArrayList<Node>();
		this.closedList = new ArrayList<Node>();
		this.steps = new ArrayList<Node>();
		this.totalStep = 0;
		this.act = new Actuator();
		//Add initial state to open list
		this.openList.add(this.node);
	}
	
	public void run() {
		boolean stop = false;
		while(!this.openList.isEmpty() && stop != true) {
			this.totalStep +=1;
			
			//get smallest f
			if(this.openList.size() == 1) {
				//int idx = getSmallest();
				this.curr = this.openList.get(0);
				this.openList.remove(0);
			}
			else {
				int idx = getSmallest();
				this.curr = this.openList.get(idx);
				this.openList.remove(idx);
			}
			
			
			//Add extracted node to closed list
			this.closedList.add(this.curr);
			//Add curr to steps
			this.steps.add(this.curr);
			//generate 4 successor for current node
			ArrayList<int[][]> succsessor = new ArrayList<int[][]>();
			
			
			
			for(int i = 0 ; i < 4; i++) {
				int[][] tempBoard1 = this.act.copyArr(this.curr.getState().getBoard());
				int[][] res;
				if(i == 0) {
					//left
					res = this.act.moveZero(tempBoard1, "left");
					if(res != null) {
						succsessor.add(res);
					}
					
				}
				else if( i == 1) {
					//right
					res = this.act.moveZero(tempBoard1, "right");
					if(res != null) {
						succsessor.add(res);
					}
				}
				else if(i == 2) {
					//top
					res = this.act.moveZero(tempBoard1, "top");
					if(res != null) {
						succsessor.add(res);
					}
				}
				else if(i == 3) {
					//bottom
					res = this.act.moveZero(tempBoard1, "bottom");
					if(res != null) {
						succsessor.add(res);
					}
				}
			}
			
			
			
			
			
			
			int[][] currSc;
			for(int i =0; i < succsessor.size();i++) {
				currSc = succsessor.get(i);
				double succG = this.curr.getGoalCost();//+  1;
				double succH = this.act.heuristic(currSc, this.goal.getBoard());
				double succF = succG + succH;
				Enviroment currEn = new Enviroment(currSc);
				Node succ = new Node(this.curr, currEn, succH, succG);
				
				//check if successor is the goal 
				if(Arrays.deepEquals(currSc, this.goal.getBoard())) {
					this.openList.add(succ);
					stop = true;
					//this.steps.add(this.curr)
					this.steps.add(succ);
					break;
				}
				
				boolean conOpen = false;
				//check if there is a same state with lower f in open
				for(int j = 0; j < this.openList.size(); j++) {
					int[][] boardCheck = this.openList.get(j).getState().getBoard();
					
					if(Arrays.deepEquals(boardCheck, currSc)) {
						if(this.openList.get(j).getF() <= succF) {
							conOpen = true;
							break;
						}
					}
				}
				
				if(conOpen == true) {
					continue;
				}
				
				boolean conClose = false;
				//check if there is a same state with lower f in close
				for(int j = 0; j < this.closedList.size(); j++) {
					int[][] boardCheck = this.closedList.get(j).getState().getBoard();
					
					if(Arrays.deepEquals(boardCheck, currSc)) {
						if(this.closedList.get(j).getF() <= succF) {
							conClose = true;
							break;
						}
					}
				}
				
				if(conClose == true) {
					continue;
				}
				this.openList.add(succ);
			}
			//this.steps.add(this.curr);
		}
	}
	
	/**
	 * Method to find smallest f
	 * @return index of object with smallest f
	 */
	public int getSmallest() {
		int size = this.openList.size();
		ArrayList<Integer> toSkip = new ArrayList();
		int res = 0; //index of with smallest f
		while(true) {
			boolean checker = true;
			//check if all value is the same
			Double temper =this.openList.get(0).getF();
			for(int i = 0; i < size; i++) {
				if(toSkip.contains(i)) {
					continue;
				}
				if(temper !=  this.openList.get(i).getF()) {
					checker = false;
					
					break;
				}
				res = i;
			}
			
			if(checker == false) {
				//get smallest
				Double comp = Double.MAX_VALUE;
				for(int i = 0; i < size; i++) {
					double temp = this.openList.get(i).getF();
					if(toSkip.contains(i)) {
						continue;
					}
					if(temp < comp) {
						res = i;
						comp = temp;
					}
				}
			}
			
				int[][] checkParent = this.act.copyArr(this.openList.get(res).getParentBoard());
				if(Arrays.deepEquals(checkParent, this.curr.getState().getBoard())) {
					break;
				}
				else {
					toSkip.add(res);
				}
			
			
		}
		
		
		
		return res;
	}
	
	public ArrayList<Node> getSteps(){
		return this.steps;
	}
	
	public int getTotalStep() {
		return this.totalStep;
	}
}
