import java.util.Comparator;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * CSIS 2420
 * A04_8Puzzle assignment
 * @author David Jones and Mason Parry
 */
public class Solver {
	
	//fields
	private MinPQ<SearchNode> pq;
	
	private SearchNode solution;
	
    private final Comparator<SearchNode> byHamming = new ByHamming();
    private final Comparator<SearchNode> byManhattan = new ByManhattan();
	
	// used to search through the boards
	private class SearchNode {
        private Board board;
        private int moves;
        private SearchNode prev;
        
        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }
        
    	public boolean equals(Object y)
    	{
    		if(y == null)return false;
    		
    		if(y == this)return true;

    		//if y is SearchNode class return false
    		if(y.getClass() != this.getClass())return false;
    		
    		//cast y as SearchNode
    		SearchNode newNode = (SearchNode)y;
    		
    		if (newNode.board.equals(board)) {
    			return true;
    		} else {
    			return false;
    		}
    	}
	}
	
    private class ByManhattan implements Comparator<SearchNode> {
        public int compare(SearchNode n1, SearchNode n2) {
            return n1.board.manhattan() + n1.moves - n2.board.manhattan() - n2.moves;
        }
    }
	
    private class ByHamming implements Comparator<SearchNode> {
        public int compare(SearchNode n1, SearchNode n2) {
            return n1.board.hamming() - n2.board.hamming();
        }
    }
	
	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	pq = new MinPQ<SearchNode>(byManhattan); //CHANGE THIS TO CHANGE WHICH METHOD IS USED (byManhattan or byHamming)
    	pq.insert(new SearchNode(initial, 0, null)); // insert the initial board in the priority queue
    	boolean solved = false;
    	while(!solved) {
    		SearchNode currentNode = pq.delMin();
            if (currentNode.board.isGoal()) {
                solution = currentNode;
                solved = true;
                break;
            }
          
            for (Board board : currentNode.board.neighbors()) {
	            SearchNode nextNode = new SearchNode(board, currentNode.moves + 1, currentNode);
	            if (nextNode.equals(currentNode.prev)) {
	                continue;
	            }
	            pq.insert(nextNode);
        	}		   
    	}
    }
    
    // min number of moves to solve initial board
    public int moves() {
    	return solution.moves;
    }
    
    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
    	if (solution == null) {
            return null;
        }
        
        Stack<Board> s = new Stack<Board>();
        SearchNode node = solution;
        s.push(node.board);
        while (node.prev != null) {
            s.push(node.prev.board);
            node = node.prev;
        }
        
        return s;
    }
    
    // solve a slider puzzle (given below) 
    public static void main(String[] args) {

	    // create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    
	    long start = System.currentTimeMillis();
	    Board initial = new Board(blocks);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	    
	    StdOut.println((double)(System.currentTimeMillis() - start)/1000);
    }
}
