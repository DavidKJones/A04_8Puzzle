/******************************************************************************
 *  Name: Mason Parry
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name: David Jones   
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 4: Slider Puzzle


/******************************************************************************
 *  Explain briefly how you implemented the Board data type.
 *****************************************************************************/

*The Board data type was implemented with a private constant field 2d int array.
*The Board was also implemented with functions that could figure out the number of
 blocks out of place, the distance a value is from a desired block position, the size of the array,
 ,generate potential neighbors, to check if another object is equal to the board, and 
 detect whether if the board is solvable.

/******************************************************************************
 *  Explain briefly how you represented a search node
 *  (board + number of moves + previous search node).
 *****************************************************************************/







/******************************************************************************
 *  Explain briefly how you detected unsolvable puzzles.
 *
 *  What is the order of growth of the running time of your isSolvable()
 *  method as function of the board size n? Recall that with order-of-growth
 *  notation, you should discard leading coefficients and lower-order terms,
 *  e.g., n log n or n^3.

 *****************************************************************************/

Description: First it converts through the entire 2d array into a 1d array ignoring the block
             with a value of 0. However, we keep track of the row the value 0 is in. 
             It then iterates through the 1d array and checks to see if there
             are any inversions and increment the number of inversions. Then it decides whether
             the board has a lengh of N that is even or odd and does different calculations based
             on the scenario. It then returns if the board is solvable(true) or not solvable(false). 

Order of growth of running time: O(n^2)



/******************************************************************************
 *  For each of the following instances, give the minimum number of moves to
 *  solve the instance (as reported by your program). Also, give the amount
 *  of time your program takes with both the Hamming and Manhattan priority
 *  functions. If your program can't solve the instance in a reasonable
 *  amount of time (say, 5 minutes) or memory, indicate that instead. Note
 *  that your program may be able to solve puzzle[xx].txt even if it can't
 *  solve puzzle[yy].txt even if xx > yy.
 *****************************************************************************/


                 min number          seconds
     instance     of moves     Hamming     Manhattan
   ------------  ----------   ----------   ----------
   puzzle28.txt      36                         0
   puzzle30.txt      40                         3
   puzzle32.txt      38                        58
   puzzle34.txt 
   puzzle36.txt 
   puzzle38.txt 
   puzzle40.txt 
   puzzle42.txt 



/******************************************************************************
 *  If you wanted to solve random 4-by-4 or 5-by-5 puzzles, which
 *  would you prefer: a faster computer (say, 2x as fast), more memory
 *  (say 2x as much), a better priority queue (say, 2x as fast),
 *  or a better priority function (say, one on the order of improvement
 *  from Hamming to Manhattan)? Why?
 *****************************************************************************/

*A better priority function because the manhattan() and hamming() functions are generally a
 performance of O(n^2) which can be very slow, espcecially when we are constantly calling them.
 manhattan is better than hamming, because it gives more info than hamming, due to calculating
 distance rather than just calculating number of values in the wrong position. 




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

Limitations: The programming can be very inefficient with larger boards.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

*We had help from our professor to get a better understanding of A* algorithm.


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/



/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

*David wrote the code for Board.java and Mason wrote the code for Solver.java.
*We both helped each other when needed and looked over each others code.



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/