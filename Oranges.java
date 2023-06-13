/* You are given an m x n grid where each cell can have one of three values:
i)- 0 representing an empty cell,
ii)- 1 representing a fresh orange
iii)- 2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten. Return the minimum 
number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
* Eg 1 : grid = [[2,1,1],[1,1,0],[0,1,1]]        Time = 4  
* Eg 2 : grid = [[2,1,1],[0,1,1],[1,0,1]]        Time = -1 
* Eg 3 : grid = [[0,2]]                          Time = 0  
*/
import java.util.*;
public class Oranges
{
      private int time = 0;
      public int TimeElapsed(int grid[][])
      {
            Queue<int[]> RottenCoordinates = new LinkedList<int[]>();    //* Queue to store Coordinates -> O(N x M)
            int fresh = 0;
            for(int i = 0; i < grid.length; i++)     //! Grid Traversal -> O(N x M)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        if(grid[i][j] == 2)     // If the Orange is rotten...
                              RottenCoordinates.add(new int[]{i, j});     // Store its coordinates...
                        else if(grid[i][j] == 1)     // If the Orange is fresh...
                              fresh++;
                  }
            }
            if(fresh == 0)    return 0;      // If there are no fresh Oranges...
            while(!RottenCoordinates.isEmpty())      //! Breadth First Search -> O(N x M)
            {
                  int queueSize = RottenCoordinates.size();     // Finding the Queue size...
                  for(int i = 0; i < queueSize; i++)      // Iterating for every element in ith time...
                  {
                        int temp[] = RottenCoordinates.poll();    // Finding the coordinate of the first Rotten Orange...
                        assert temp != null;     // Asserting that coordinates are not null...
                        if((temp[0] > 0) && (grid[temp[0] - 1][temp[1]] == 1))
                        {     // Checking if there is a fresh Orange up a Rotten Orange...
                              RottenCoordinates.add(new int[]{temp[0] - 1, temp[1]});
                              grid[temp[0] - 1][temp[1]] = 2;      // Rot the Orange...
                              fresh--;
                        }
                        if((temp[0] < grid.length - 1) && (grid[temp[0] + 1][temp[1]] == 1))
                        {     // Checking if there is a fresh Orange down a Rotten Orange...
                              RottenCoordinates.add(new int[]{temp[0] + 1, temp[1]});
                              grid[temp[0] + 1][temp[1]] = 2;      // Rot the Orange...
                              fresh--;
                        }
                        if((temp[1] > 0) && (grid[temp[0]][temp[1] - 1] == 1))
                        {     // Checking if there is a fresh Orange left a Rotten Orange...
                              RottenCoordinates.add(new int[]{temp[0], temp[1] - 1});
                              grid[temp[0]][temp[1] - 1] = 2;      // Rot the Orange...
                              fresh--;
                        }
                        if((temp[1] < grid[0].length - 1) && (grid[temp[0]][temp[1] + 1] ==  1))
                        {     // Checking if there is a fresh Orange right a Rotten Orange...
                              RottenCoordinates.add(new int[]{temp[0], temp[1] + 1});
                              grid[temp[0]][temp[1] + 1] = 2;      // Rot the Orange...
                              fresh--;
                        }
                  }
                  time++;     // Incrementing the time after all the Oranges of the current Queue are checked...
            }
            return fresh == 0 ? time - 1 : -1;    // Ternary Statement...
      }
      public static void main(String args[])
      {
            Scanner sc = new Scanner(System.in);
            int row, col;
            System.out.print("Enter number of Rows : ");
            row = sc.nextInt();
            System.out.print("Enter number of Columns : ");
            col = sc.nextInt();
            int grid[][] = new int[row][col];
            for(int i = 0; i < grid.length; i++)
            {
                  for(int j = 0; j < grid[0].length; j++)
                  {
                        System.out.print("Enter value of "+(i+1)+" row and "+(j+1)+" column : ");
                        grid[i][j] = sc.nextInt();
                  }
            }
            Oranges oranges = new Oranges();          // Object creation...
            System.out.print("Time Elapsed to make all Oranges Rotten : "+oranges.TimeElapsed(grid));
            sc.close();
      }
}



//! Time Complexity -> O(N x M)
//* Space Complexity -> O(N x M)