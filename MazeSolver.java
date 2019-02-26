
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
      String[][] pic=new String[row][col];
      while(inf.hasNextLine()){
        String line = inf.nextLine();
        int x=0;
        for (int y=0;y<col;y++) {
          System.out.println(line);
          pic[x][y]=line.substring(y,y+1);
          System.out.println(pic[x][y]);
        }
        x++;
      }
  }
  catch(FileNotFoundException e){
    System.out.println("File not found");
    System.exit(1);
  }
  }
}
