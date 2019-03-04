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
          int x=0;
          while(input.hasNextLine()){
            String line = input.nextLine();
            for (int y=0;y<col;y++) {
              maze[x][y]=line.charAt(y);
              s+=maze[x][y];
            }
            x++;
            s+="\n";
          }
      }

    public String toString(){
      String s="";
      for (int i=0;i<maze.length;i++) {
        if (i!=0){
          s+="\n";
        }
        for (int j=0;j<maze[0].length;j++){
          s+=maze[i][j];
        }
      }
      return s;
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
      int a=1;
      int b=1;
      for (int i=1;i<maze.length;i++) {
        for (int j=1;j<maze[0].length;j++){
          if(maze[i][j]=='S'){
            a=i;
            b=j;
            i=maze.length;
            j=maze[0].length;
          }
        }
      }
      //System.out.println(a+" ,"+b);
      int[] offsets=new int[8];
      offsets[0]=0;
      offsets[1]=1;
      offsets[2]=1;
      offsets[3]=0;
      offsets[4]=0;
      offsets[5]=-1;
      offsets[6]=-1;
      offsets[7]=0;
      return solve(a,b,offsets);
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
    private int solve(int row, int col,int[] moves){ //you can add more parameters since this is private
      int steps=0;
        //automatic animation! You are welcome.
        if(animate){
            clearTerminal();
            System.out.println(this);
            wait(40);
        }
        if(maze[row][col]=='E'){
          //System.out.println("Yes");
          setAnimate(false);
          return 0; //so it compiles
        }
        else if(maze[row][col]!='@'&maze[row][col]!='.'&&maze[row][col]!='#'){
          maze[row][col]='@';
          for (int i=0; i<4;i++ ) {
            int x=solve(row+moves[i*2],col+moves[i*2+1],moves);
            if (x!=-1){

              return steps+x;
            }
          }
          maze[row][col]='.';
          return -1;
        }

        else{
          //System.out.println("Yes");
          return -1;
        }
          /*if(maze[row][col]=='#'){
            System.out.println("Failed at#:"+row+","+col);
            System.out.println("Going to try:"+(row+moves[d*2]) +","+(col+moves[d*2+1]));
            return solve(row+moves[d*2],col+moves[d*2+1],direction+1,steps+1,changed+1,moves);
          }*/
          /*if(maze[row][col]=='.'){
            return solve(row+moves[d*2],col+moves[d*2+1],direction,steps+1,changed,moves);
          }*/
          /*if(changed>=4){
            System.out.println("made dot at"+(row-moves[d*2])+","+(col-moves[d*2+1]));
            maze[row-moves[d*2]][col-moves[d*2+1]]='.';
            System.out.println("Going to try"+(row-moves[d*2])+","+(col-moves[d*2+1]));
            System.out.println(this);
            if(maze[row-moves[d*2]][col-moves[d*2+1]]=='@'){
              System.out.println("yess");
              return solve(row-moves[d*2]+moves[((direction+1)%4)*2],col-moves[d*2+1]+moves[((direction+1)%4)*2+1],direction,steps+1,0,moves);
            }
            else if(maze[row-moves[0]][col-moves[1]]=='@'){
              return solve(row-moves[0]+moves[2],col-moves[1]+moves[3],1,steps+1,2,moves);
            }
            else if(maze[row-moves[2]][col-moves[3]]=='@'){
              return solve(row-moves[2]+moves[4],col-moves[3]+moves[5],2,steps+1,2,moves);
            }
            else if(maze[row-moves[4]][col-moves[5]]=='@'){
              return solve(row-moves[4]+moves[6],col-moves[5]+moves[7],3,steps+1,2,moves);
            }
            else {
              return solve(row-moves[6]+moves[0],col-moves[7]+moves[1],0,steps+1,2,moves);
            }
          }

            //System.out.println("row"+row+"col"+(col-1));
            //System.out.println("so");
            //System.out.println("to be "+(row-moves[d*2])+","+col-moves[d*2+1]);
            System.out.println("Failed at:"+row+","+col);
            System.out.println("Going to try:"+(row-moves[(direction%4)*2]+moves[((direction+1)%4)*2])+","+(col-moves[(direction%4)*2+1]+moves[((direction+1)%4)*2+1]));
            System.out.println("changes:"+changed);
            System.out.println(this);
            return solve(row-moves[d*2]+moves[((direction+1)%4)*2],col-moves[d*2+1]+moves[((direction+1)%4)*2+1],direction+1,steps+1,changed+1,moves);*/
        }

        //COMPLETE SOLVE




}
