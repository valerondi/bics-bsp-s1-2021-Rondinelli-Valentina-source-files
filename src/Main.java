package src;
import java.util.Scanner;


public class Main {
    public static void main (String args[]) {

        Scanner scanner = new Scanner(System.in);
        int player = 0; 
        
        System.out.println("Welcome to Connect 4.");
        System.out.println("You are Player 1. Player 2 is a super intelligent AI from the future! Go!");

        Board board = new Board(6, 7);

        while (true) {
            boolean turn = true;
            int playedColumn;
            while (turn) {
                player = 1;
                System.out.println(board);
                System.out.print("Your turn, choose which column you want to play: ");
                playedColumn = scanner.nextInt() - 1;

                if (board.drop(playedColumn, player)) {
                    turn = false;
                }
            }

            if (board.full()) {
                System.out.println(board);
                System.out.println("The board is full.");
                break;
            }
            if (board.hasWon(player)) {
                System.out.println(board);
                System.out.println("Player " + player + " has won!");
                break;
            }

            turn = true;
            while (turn) {
                player = 2;

                if (board.dropAI(player)) {
                    turn = false;
                }
            }

            if (board.full()) {
                System.out.println(board);
                System.out.println("The board is full.");
                break;
            }
            if (board.hasWon(player)) {
                System.out.println(board);
                System.out.println("Player " + player + " has won!");
                break;
            }
        }

        scanner.close();
    }
}