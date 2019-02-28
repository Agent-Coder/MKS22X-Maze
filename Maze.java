import java.util.*;
import java.io.*;

public class Maze{


    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
          animate=false;
          File text = new File("Maze.txt");
          Scanner inf = new Scanner(text);
          int row=0;
          int col=0;
          while(inf.hasNextLine()){
              String line = inf.nextLine();

              col=line.length();
              row++;
          }
          Scanner input = new Scanner(text);
          maze=new char[row][col];
          String s="";
          while(input.hasNextLine()){
            String line = input.nextLine();
            int x=0;
            for (int y=0;y<col;y++) {
              maze[x][y]=line.charAt(y);
              s+=maze[x][y];
            }
            s+="\n";
            x++;
          }
      }


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){
      int a=0;
      int b=0;
      for (int i=0;i<maze.length;i++) {
        for (int j=0;j<maze.length;j++){
          if(maze[i][j]=='S'){
            maze[i][j]='@';
            a=i;
            b=j;
          }
        }
      }
      return solve(a,b,0);
            //find the location of the S.


            //erase the S


            //and start solving at the location of the s.

            //return solve(???,???);

    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col,int direction){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }
        if(maze[row][col]!='@'||maze[row][col]!='.'||maze[row][col]!='#'){
          maze[row][col]='@';
          if(direction==1){
            return solve(row,col+1,direction);
          }
            else if(direction==3){
            return solve(row,col-1,direction);
          }
          else if(direction==2){
            return solve(row+1,col,direction);
          }
          else{
            System.out.println(row+" "+col);
            return solve(row-1,col,direction);
         }
        }
        else if(maze[row][col]!='E'){
          return -1; //so it compiles
        }
        else{
          if (maze[row][col]!='#'){
            maze[row][col]='.';
          }

          if(direction==1){
            return solve(row,col-1,direction+1);
          }
          else if(direction==3){
            return solve(row,col+1,direction+1);
            }
          else if(direction==2){
            return solve(row-1,col,direction+1);
          }
          else{
            return solve(row+1,col,1);
          }
        }

        //COMPLETE SOLVE


    }


}
