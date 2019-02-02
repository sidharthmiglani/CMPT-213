/**
 * This Helper class provides us a constructor
 * which is used in our main function to
 * print Main menu and Title of the game.
 */
public class Helper {
    Helper(String title,String [] options) {
        for(int i =0; i<title.length()+5; i++)
            {
                System.out.print("*");
            }
        System.out.println("\n* "+title+" *");
        for(int i =0; i<title.length()+5; i++)
            {
                System.out.print("*");
            }
            System.out.println("");
        for(int i=0;i<13;i++)
            {
                System.out.print("*");
            }
        System.out.println("");
        System.out.println("* Main Menu *");
        for(int i=0;i<13;i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i=0;i<options.length;i++){
            System.out.println((i+1)+". "+options[i]);
        }
    }
}
