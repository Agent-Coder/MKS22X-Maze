
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MazeSolver{
  public static void main(String[] args) {
    try{
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
      String[][] pic=new String[row][col];
      String s="";
      while(input.hasNextLine()){
        String line = input.nextLine();
        int x=0;
        for (int y=0;y<col;y++) {
          pic[x][y]=line.substring(y,y+1);
          s+=pic[x][y];
        }
        s+="\n";
        x++;
      }
      System.out.println(s);
  }
  catch(FileNotFoundException e){
    System.out.println("File not found");
    System.exit(1);
  }
  }
}
