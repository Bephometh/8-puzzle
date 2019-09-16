package Model;

import java.util.Arrays;

public class Actuator {
	
	/**
	 * Mehtpd to move zero around the board
	 * @param board the board 0 is located
	 * @param move direction of where 0 wanted to move
	 * @return new board with 0's new position
	 */
	public int[][] moveZero(int[][] boards, String move) {
		int[][] board = boards;
		int idxX = 0;
		int idxY = 0;
		
		for(int i = 0; i < board.length; i++) {
			for(int j =0; j < board.length; j++) {
				if(board[i][j] == 0) {
					idxX = j;
					idxY = i;
					break;
				}
			}
		}
		
		if(move.equalsIgnoreCase("left")) {
			boolean pass = moveable(idxX, idxY, idxX-1, idxY);
			
			if(pass == true) {
				int temp = board[idxY][idxX];
				board[idxY][idxX] = board[idxY][idxX-1];
				board[idxY][idxX-1] = temp;
				
				return board;
			}
			else {
				return null;
			}
		}
		else if(move.equalsIgnoreCase("right")) {
			boolean pass = moveable(idxX, idxY, idxX+1, idxY);
			
			if(pass == true) {
				int temp = board[idxY][idxX];
				board[idxY][idxX] = board[idxY][idxX+1];
				board[idxY][idxX+1] = temp;
				
				return board;
			}
			else {
				return null;
			}
		}
		else if(move.equalsIgnoreCase("bottom")) {
			boolean pass = moveable(idxX, idxY, idxX, idxY+1);
			
			if(pass == true) {
				int temp = board[idxY][idxX];
				board[idxY][idxX] = board[idxY+1][idxX];
				board[idxY+1][idxX] = temp;
				
				return board;
			}
			else {
				return null;
			}
		}
		else if(move.equalsIgnoreCase("top")) {
			boolean pass = moveable(idxX, idxY, idxX, idxY-1);
			
			if(pass == true) {
				int temp = board[idxY][idxX];
				board[idxY][idxX] = board[idxY-1][idxX];
				board[idxY-1][idxX] = temp;
				
				return board;
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
	
	public int[][] copyArr(int[][] boards){
		int[][] copied = new int[boards.length][boards.length];
		
		for(int i = 0; i < boards.length; i++) {
			for(int j = 0; j < boards.length; j++) {
				copied[i][j] = boards[i][j];
			}
		}
		
		return copied;
	}
	
	/**
	 * Method to check if the zero is moveable
	 * @param currX 0 x start position
	 * @param currY 0 y start position
	 * @param newX 0 x new position
	 * @param newY 0 y new position
	 * @return if the move is valid
	 */
	public boolean moveable(int currX, int currY, int newX, int newY) {
		if(newX == -1 || newY == -1 || newX > 2 || newY > 2) {
			return false;
		}
		else {
			return true;
		}
		
		
	}
	
	public double heuristic(int[][] board, int[][] comparator) {
		double res = 0;
		//number out of row
		for(int i = 0; i < comparator.length; i++) {
			for(int j = 0; j < comparator.length; j++) {
				//double compD = comparator[i][j];
				int boardD = board[i][j];
				// Check row
				if(i == 0) {
					if( boardD  >= 1 && boardD <= 3) {
						
					}
					else {
						res += 1;
					}
				
					
				}
				else if(i == 1) {
					if(boardD >= 4 && boardD <= 6 ) {
						
					}
					else {
						res += 1;
					}
					
				}
				else if( i == 2) {
					if(boardD >= 7 || boardD == 0) {
						
					}
					else {
						res += 1;
					}
					
						
				}
				
				
			}
		}
		//number out of coloumn
		for(int i = 0; i < comparator.length; i++) {
			for(int j = 0; j < comparator.length; j++) {
				//double compD = comparator[i][j];
				int boardD = board[j][i];
				if(i == 0) {
					if(boardD == 1 ) {
						continue;
					}
					else if( boardD == 4 ) {
						continue;
					}
					else if( boardD == 7) {
						continue;
					}
					else {
						res += 1;
					}
						
				}
				else if(i == 1) {
					if(boardD == 2.0) {
						continue;
					}
					else if( boardD == 5 ) {
						continue;
					}
					else if( boardD == 8) {
						continue;
					}
					else {
						res += 1;
					}
				}
				else if( i == 2) {
					if(boardD == 3 ) {
						continue;
					}
					else if( boardD == 6 ) {
						continue;
					}
					else if(boardD == 0) {
						continue;
					}
					else {
						res += 1;
					}
				}
				
			}
		}
		
		return res;
	}
}
