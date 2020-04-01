/**
 * This is responsible for creating a
 * grid that user sees when not in cheat mode.
 *
 * The grid is then compared to the actual grid
 * to perform movement changes.
 */
package ca.cmpt213.assn3.userInterface;

public class GridForUser {
    public final int NUM_ROWS=10;
    public final int NUM_COLS=10;
    public String[][] fake_values = new String [NUM_ROWS][NUM_COLS];

    public GridForUser(int first,int second){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fake_values[i][j] = "~";
            }
        }
        fake_values[first][second]="@";
    }
    public void printGrid(){
        char count = 'A';
        System.out.println("Game Grid: ");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print(count + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print(fake_values[i][j] + " ");
            }
            System.out.println();
            count++;
        }
    }
}
