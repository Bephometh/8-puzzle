package Model;

public class Node implements Comparable<Node>{
	private Node parent;
	private Enviroment state;
	private double heuristic; //uses manhattan distance
	private double goalCost;
	private double f; //Value of heuristic sum with goalcost;
	
	
	public Node(Node parent, Enviroment state, double heuristic, double goalCost){
		this.parent = parent;
		this.state = state;
		this.heuristic = heuristic;
		this.goalCost = goalCost;
		f = this.goalCost + this.heuristic;
	}
	
	public double getF() {
		return f;//= this.goalCost + this.heuristic;
	}
	
	public Enviroment getState() {
		return this.state;
	}
	
	public double getGoalCost() {
		return this.goalCost;
	}
	
	public int[][] getParentBoard(){
		return this.parent.getState().getBoard();
	}
	
	
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		
		return  (int) ((this.goalCost + this.heuristic) - (o.goalCost+o.heuristic));
	}
	
}
