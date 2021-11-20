package src;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final int winCheck = 4;
    private int rows, columns;
    private int[][] array;

    public Board (int rows, int columns) {
      this.rows = rows;
      this.columns = columns;
      this.array = new int[rows][columns];
    }

    @Override
    public String toString() {
      String str = "";

      for(int i = 0; i < array.length; i++){
        for(int j = 0; j < array[0].length; j++){
          if(array[i][j] == 0)
            str += "\u00B7" + " ";
          else if(array[i][j] == 1)
            str += "\u25CB" + " ";
          else if(array[i][j] == 2)
            str += "\u25CF" + " ";
        }
        str += "\n";
      }

      return str;
    }

    public boolean drop(int playedColumn, int player) {
      for (int i = rows - 1; i >= 0; i--){
        if (array[i][playedColumn] == 0){
          array[i][playedColumn] = player;
          return true;
        }
      }
      
      return false;
    }

    public boolean full(){
      int counter = 0;
  
      for (int i = 0; i < columns; i++){
        if (array[0][i] != 0){
          counter += 1;
        }
      }
  
      return counter == columns;
    }

    public boolean hasWon(int player){
      return hasWonColumn(player) || hasWonRow(player) || hasWonAscendingDiag(player) || hasWonDescendingDiag(player);
    }
  
    public boolean hasWonColumn(int player){ //The indices are reversed so that we can parse the array vertically.
      int counter;
  
      for(int i = 0; i < columns; i++){
        counter = 0;
  
        for(int j = 0; j < rows; j++){
  
          if (array[j][i] == player){
            counter++;
            if (counter == winCheck){
              return true;
            }
          }
  
          else{
            counter = 0; //If player discs aren't back-to-back, clears the counter.
          }
        }
      }
      return false;
    }
  
    public boolean hasWonRow(int player){ //Parses the array horizontally.
      int counter;
  
      for(int i = 0; i < rows; i++){
        counter = 0;
  
        for(int j = 0; j < columns; j++){
  
          if (array[i][j] == player){
            counter++;
            if (counter == winCheck){
              return true;
            }
          }
  
          else{
            counter = 0;
          }
        }
      }
      return false;
    }
  
    public boolean hasWonDescendingDiag(int player){
      int counter;
      for (int i = 0; i + winCheck - 1 < rows; i++){ //We manage the boundaries here so that we do not get OutOfBounds error.
        for (int j = 0; j + winCheck - 1 < columns; j++){ //We can set the boundaries by imagining how much space the diagonal line will take.
          counter = 0;
          for(int x = 0; x < winCheck; x++){ //The third loop allows us to travel the array diagonally after a valid point has been picked in the previous nested loop.
            if (array[i + x][j + x] == player){
              counter++;
              if (counter == winCheck){
                return true;
              }
            }
            else {
              counter = 0; //Follows the exact same algorithm as the hasWonRows() and hasWonColumns().
            }
          }
        }
      }
      return false;
    }
  
    public boolean hasWonAscendingDiag(int player){
      int counter;
      for (int i = 0; i + winCheck - 1 < rows; i++){ //The boundaries are set the same way for i.
        for (int j = columns - 1; j > columns - winCheck - 1; j--){ //But j is reversed so that we can parse the array in the opposite diagonal. j-- because we want to go "up" in the array.
          counter = 0;
          for(int x = 0; x < winCheck; x++){ //Allows us to go in separate directions in terms of i and j.
            if (array[i + x][j - x] == player){
              counter++;
              if (counter == winCheck){
                return true;
              }
            }
            else {
              counter = 0;
            }
          }
        }
      }
      return false;
    }

    public boolean dropAI(int player) {
        for (int i = 0; i < columns; i++) {
            if (array[0][i] == 0) {
                for (int j = 1; j < rows; j++) {
                    if (array[j][i] != 0) {
                        if (array[j][i] != player) {
                            break;
                        } 
                        if (array[j][i] == player) {
                            if (drop(i, player))
                                return true;
                        }
                    }
                }
            }  
        }

        ArrayList<Integer> possibleRandom = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < columns; i++)
            possibleRandom.add(i);

        while (!possibleRandom.isEmpty()) {
            int randCol = possibleRandom.get(rand.nextInt(possibleRandom.size()));
            if (drop(randCol, player))
                return true;
            else
                possibleRandom.remove(randCol);
        }

        return false;
    }






















 /* public boolean verticalAICheck( ){
      
      int min = 0;
      int max = 6; 

      int randomCol = (int)(Math.random() * (max))+min;

      int counter;
      int emptyCol;

      
  
      return counter == columns;
      for(int i = 0; i < columns; i++){
        for(int j = 0; j < rows; j++){
          counter = 0;
          if (array[i][j] == 0) {




            if(array[i][j] == 2) {
              counter = 1;
              board.drop(i, 2);
              counter += 1; 

              if (counter == winCheck){
                return true;
              }
            }  else if (array[i][j] == 1)
                continue;
          } else {
              board.drop(randomCol, 2);

          }
  

      return true;
        }
      }
    } */
}