package Model;

import java.util.Map;

public class Enviroment{
	private int[][] Board;
	private Map<Integer[][], Integer> map;
	
	public Enviroment(int[] state){
		int initCounter = 0;
		for(int i = 0; i < Board.length; i++) {
			for(int j = 0; j < Board[0].length; j++) {
				Board[i][j] = state[initCounter];
				initCounter ++;
			}
		}
	}
	
	Enviroment(int[][] board){
		this.Board = board;
	}
	
	public int[][] getBoard(){
		return this.Board;
	}
	
	public String toString() {
		String res ="";
		
		for(int i = 0; i < this.Board.length; i++) {
			for(int j = 0; j < this.Board.length; j++) {
				res += this.Board[i][j]+",";
			}
			res += "\n";
		}
		
		return res;
	}
	
}
