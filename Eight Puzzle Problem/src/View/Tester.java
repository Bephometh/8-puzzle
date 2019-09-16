package View;

import java.util.ArrayList;
import java.util.Scanner;
import Model.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[][] initState = new int[3][3];
		System.out.println("Masukkan initial state :");
		
		for(int i = 0; i < initState.length; i++) {
			for(int j = 0; j < initState.length; j++) {
				initState[i][j] = sc.nextInt();
			}
			
		}
		
		AStar star = new AStar(initState);
		
		star.run();
		
		ArrayList<Node> res = star.getSteps();
		int totalSteps = star.getTotalStep();
		System.out.println("Required steps : "+totalSteps);
		res.forEach((n) -> 
		{ System.out.println("[\n"+n.getState().toString()+"]");
		});
	}

}
