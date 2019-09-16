
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Reynaldi R
 */
public class Tester {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String str = sc.next();
            String goal = sc.next();
            BreadthFS bfs = new BreadthFS(str,goal);
            bfs.doSearch();
            bfs.printSolution(str);
            
            
            
            

    }
}

class BreadthFS {

    public String str = "";
    public String goal = "";

    public LinkedList<String> openList;
    public Map<String, Integer> levelDepth;
    public Map<String, String> stateHistory;

    public int nodes = 0;
    public int limit = 100;
    public int unique = -1;
    public int newValue;
    public int a;

    public String currState;
    public boolean solution = false;

    public BreadthFS(String str, String goal) {
        this.openList = new LinkedList<>();
        this.levelDepth = new HashMap<String, Integer>();
        this.stateHistory = new HashMap<String, String>();
        this.str = str;
        this.goal = goal;
        addToOpenList(str, null);

    }

    public void doSearch() {
        while (!this.openList.isEmpty()) {
            this.currState = openList.removeFirst();
            if (this.currState.equals(goal)) {
                this.solution = true;
                printSolution(this.currState);
                break;
            }

            if (this.levelDepth.get(this.currState) == this.limit) {
                this.solution = false;
                printSolution(this.currState);
            } else {
                this.a = this.currState.indexOf("0");
                while (this.a != 0 && this.a != 3 && this.a != 6) {
                    String nextState = this.currState.substring(0, a - 1) + "0" + this.currState.charAt(a - 1) + this.currState.substring(a + 1);
                    this.nodes++;
                    addToOpenList(nextState, currState);
                    break;

                }

                while (this.a != 0 && this.a != 1 && this.a != 2) {
                    String nextState = this.currState.substring(0, a - 3) + "0" + this.currState.charAt(a - 2) + this.currState.substring(a + 1);
                    addToOpenList(nextState, currState);
                    this.nodes++;
                    break;

                }

                while (this.a != 2 && this.a != 5 && this.a != 8) {
                    String nextState = this.currState.substring(0, a) + this.currState.charAt(a + 1) + "0" + this.currState.substring(a + 2);
                    addToOpenList(nextState, currState);
                    this.nodes++;
                    
                    break;

                }

                while (this.a != 6 && this.a != 7 && this.a != 8) {
                    String nextState = this.currState.substring(0, a) + this.currState.substring(a + 3, a + 4) + this.currState.substring(a + 1, a + 3) + "0" + this.currState.substring(a + 4);
                    addToOpenList(nextState, currState);
                    this.nodes++;
                    break;

                }
            }
        }
        if (solution) {
            System.out.println("solution exist");
        }
    }

    private void addToOpenList(String newState, String oldState) {
        if (!this.levelDepth.containsKey(newState)) {
            this.newValue = oldState == null ? 0 : this.levelDepth.get(oldState) + 1;
            unique++;
            this.levelDepth.put(newState, newValue);
            this.openList.add(newState);
            this.stateHistory.put(newState, oldState);
        }
    }

    void printSolution(String currState) {
        if (solution) {
            System.out.println("Solusi ditemukan dalam " + this.levelDepth.get(currState) + " langkah");

        } else {
            System.out.println("solusi tidak ditemukan");
        }

        String traceState = currState;
        while (traceState != null) {
            System.out.println(traceState + " at " + this.levelDepth.get(traceState));

        }

        try {
            for (int i = 0; i < 9; i++) {
                System.out.println(" " + String.valueOf(traceState.charAt(i)) + " ");
                if ((i + 1) % 3 == 0) {
                    System.out.println();

                }

            }
        }catch(NullPointerException e){
            
        }
        traceState = this.stateHistory.get(traceState);

    }
}
