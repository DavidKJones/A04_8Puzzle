import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * CSIS 2420
 * A04_8Puzzle assignment
 * @author David Jones and Mason Parry
 */
public class Board 
{
	private int[][] blocks;
	
	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) 
	{ 	
		if(blocks == null) throw new NullPointerException();
		this.blocks = blocks;
	}
	
	// board size N   									
	public int size() 
	{
		return blocks.length * blocks.length;
	}
	
	// number of blocks out of place
	public int hamming() 
	{
		int count = 0;
		
		//loop through the entire 2d array
		for( int i = 0; i < blocks.length; i++)
		{
			for( int j = 0; j < blocks.length; j++)
			{
				//ignore the blank block
				if(blocks[i][j] == 0)
					continue;
				
				int position = (i * blocks.length) + j + 1;
				
				if(blocks[i][j] != position)
					count++;		
			}
		}
		
		return count;
	}
	
	// sum of Manhattan distances between blocks and goal
	public int manhattan() 
	{
		int count = 0;
		
		//loop through the entire 2d array
		for( int i = 0; i < blocks.length; i++)
		{
			for( int j = 0; j < blocks.length; j++)
			{
				//ignore the blank block
				if(blocks[i][j] == 0)
					continue;
				
				int position = (i * blocks.length) + j + 1;
				
				if(blocks[i][j] != position)
				{
					//used algebra to find the desired position
					int goalX = (position - j - 1)/ blocks.length;
					
					int goalY = 0;
					
					if(i != 0)
					{
						goalY = (position - 1)/(i * blocks.length);
					}
					
					count += Math.abs(goalY - i) + Math.abs(goalX - j);
				}
			}
		}
		
		return count;
	}
	
	// is this board the goal board?
	public boolean isGoal() 
	{
		//loop through the entire 2d array
		for( int i = 0; i < blocks.length; i++)
		{
			for( int j = 0; j < blocks.length; j++)
			{
				//ignore the blank block
				if(blocks[i][j] == 0)
					continue;
				
				int position = (i * blocks.length) + j + 1;
				
				if(blocks[i][j] != position)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	// is this board solvable?
	public boolean isSolvable() 
	{
		int[] temp = new int[(blocks.length * blocks.length) - 1];	
		int position = 0;
		int inversions = 0;
		int blankRow = 0;
		
		//create the single dimensional array from blocks
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks.length; j++)
			{
				if(blocks[i][j] != 0)
				{
					temp[position++] = blocks[i][j];
				}
				else
					blankRow = i;
			}
		}
		
		//loop through the single dimensional array to see if there are any inversions
		for(int i = 0; i < temp.length; i++)
		{
			for(int j = 0; j < temp.length; j++)
			{
				if(temp[i] > temp[j] && i < j)
					inversions++;
			}
		}
		
		//determine if the board is even or odd
		boolean isEven = blocks.length % 2 == 0;
		
		if(isEven)
			return (blankRow + inversions) % 2 == 3;
		else
			return inversions % 2 == 0;

	}
	
	// does this board equal y?
	public boolean equals(Object y)
	{
		if(y == null)return false;
		
		if(y == this)return true;
		
		//if y is Board class return false
		if(y.getClass() != this.getClass())return false;
		
		//cast y as Board
		Board otherBoard = (Board)y;
		
		//if the size of the boards are not the same return false
		if(otherBoard.size() != size())return false;
	
		//if the manhattan for both boards are not the same then return false
		if(otherBoard.manhattan() != manhattan())return false;
		
		//if the hamming for both boards are not the same then return false
		if(otherBoard.hamming() != hamming())return false;
		
		return true;	
	}
	
	// all neighboring boards
	public Iterable<Board> neighbors() 
	{
		Stack<Board> s = new Stack<Board>();
		
		//4 is the max amount of neighbors a block can have
		for( int n = 0; n < 4; n++)
		{
			int[][] temp = new int[blocks.length][blocks.length];
			int row = 0;
			int column = 0;
			
			for(int i = 0; i < blocks.length; i++)
			{
				for(int j = 0; j < blocks.length; j++)
				{
					temp[i][j] = blocks[i][j];
					
					if(temp[i][j] == 0)
					{
						row = i;
						column = j;
					}
				}
			}
			
			//we will have to go through this switch statement four times in order 
			//to test for neighbors on the left, right, above, and below
			switch(n)
			{
				case 0:
					if(row + 1 < blocks.length)
					{
						temp[row][column] = temp[row+1][column];
						temp[row+1][column] = 0;
						s.push(new Board(temp));
					}
					break;
				case 1:
					if(row - 1 >= 0)
					{
						temp[row][column] = temp[row-1][column];
						temp[row-1][column] = 0;
						s.push(new Board(temp));
					}
					break;
				case 2:
					if(column + 1 < blocks.length)
					{
						temp[row][column] = temp[row][column+1];
						temp[row][column+1] = 0;
						s.push(new Board(temp));
					}
					break;
				case 3:
					if(column - 1 >= 0)
					{
						temp[row][column] = temp[row][column-1];
						temp[row][column-1] = 0;
						s.push(new Board(temp));
					}
					break;
				default:
					break;
			}
		}
		
		return s;
	}
	
	// string representation of this board (in the output format specified below)
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		s.append("3x3\n");
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks.length; j++)
			{
				s.append(String.format("%2d", blocks[i][j]));
			}
			s.append("\n");
		}
		
		return s.toString();
	}
	
	// unit tests (not graded)
	public static void main(String[] args) 
	{
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        if(!initial.isSolvable())
        {
        	System.err.print(args[0] + " is not solvable!");     
        	return;
        }
        
        System.out.print("Initial:\n" + initial.toString() + "\nNeighbors:\n");      
        
        for(Board b : initial.neighbors())
        {
        	StdOut.print(b.toString());	
        }
	}
}
