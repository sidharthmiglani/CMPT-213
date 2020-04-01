/**
 * This class is responsible
 * for making a random grid
 * according to the inputs from arguments.
 *
 */
package ca.cmpt213.assn3.userInterface;

import java.util.Random;

public class GridMaker {
    public final int NUM_ROWS=10;
    public final int NUM_COLS=10;
    public String[][] actual_values = new String [NUM_ROWS][NUM_COLS];

    // Assigns Toki's and Foki's randomly on the grid
    public GridMaker(int numToki,int numFoki) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                actual_values[i][j] = " ";
            }
        }
        Random rand = new Random();
        for (int i = 0; i < numToki; i++) {
            int randomRow = rand.nextInt(10);
            int randomCol = rand.nextInt(10);
            while (!(actual_values[randomRow][randomCol].isBlank())) {
                randomRow = rand.nextInt(10);
                randomCol = rand.nextInt(10);
            }
            actual_values[randomRow][randomCol] = "$";
        }
        for (int i = 0; i < numFoki; i++) {
            int randomRow = rand.nextInt(10);
            int randomCol = rand.nextInt(10);
            while (!(actual_values[randomRow][randomCol].isBlank())) {
                randomRow = rand.nextInt(10);
                randomCol = rand.nextInt(10);
            }
            actual_values[randomRow][randomCol] = "X";
        }
    }
    public void printGrid() {
        char count = 'A';
        System.out.println("Cheat Grid: ");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(count + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(actual_values[i][j] + " ");
            }
            System.out.println();
            count++;
        }
    }
}
