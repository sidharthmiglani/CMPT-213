import java.util.*;

/**
 * Our main class demonstrates a Tokimon ArrayList, functions for output according to user choice
 * and a main function.
 */
public class Main {
    String name,type;
    double size;
    ArrayList<Tokimon> tokiList = new ArrayList<Tokimon>();

    Scanner input=new Scanner(System.in);

    final String TITLE = "Tokimon Tracker by Sidharth Miglani";

    final String [] OPTIONS_ARRAY={"List Tokimons",
            "Add a new Tokimon","Remove a Tokimon","Change Tokimon strength",
            "DEBUG: Dump objects (toString)","Exit"};

    public void addTokimon(){
        Scanner input= new Scanner(System.in);
        System.out.print("Enter Tokimon's name: ");
        name=input.nextLine();
        System.out.print("Enter Tokimon's type: ");
        type=input.nextLine();
        System.out.print("Enter Tokimon's size: ");
        size=input.nextDouble();
        input.nextLine();
        Tokimon temp= new Tokimon(name,size,type);
        tokiList.add(temp);
        displayMenu();
    }

    public void printTokiList(){
        System.out.println("*********************\n* List of Tokimons: *\n*********************");
        for (int i=0;i<tokiList.size();i++) {
            System.out.println((i + 1) + ". " + tokiList.get(i).getName() + ", "+ tokiList.get(i).getSize() + "m, " + tokiList.get(i).getType()+" ability, "+tokiList.get(i).strength+" strength");
        }
    }

    public void exit(){System.out.println("BYE!"); System.exit(0);}

    public void removeTokimon(){
        printTokiList();
        System.out.print("Choose the Tokimon you would like to remove or to cancel enter 0: ");
        Scanner input=new Scanner(System.in);
        int user_choice=input.nextInt();input.nextLine();
        if (user_choice==0){
            displayMenu();
        }
        while (user_choice>tokiList.size() || user_choice<0){
            System.out.print("Please enter a valid Tokimon: ");
            user_choice=input.nextInt();input.nextLine();
        }
        tokiList.remove(user_choice-1);
        System.out.println("Tokimon "+ user_choice +" has been removed. \n");
        displayMenu();
    }

    public void change_Tokimon_Strength(){
        printTokiList();
        System.out.print("\nChoose the Tokimon for which you want to change the strength or enter 0 to cancel: ");
        int user_choice=input.nextInt();input.nextLine();
        if(user_choice==0){displayMenu();}
        while(user_choice>tokiList.size() || user_choice<0){
            System.out.print("Please choose a valid Tokimon: ");
            user_choice= input.nextInt();input.nextLine();
        }
        System.out.print("By how much? (must be between 0 and 100): ");
        int new_strength=input.nextInt();input.nextLine();
        while(new_strength<0 || new_strength>100){
            System.out.print("Please enter a strength b/w 0 and 100: ");
            new_strength=input.nextInt();input.nextLine();
        }
        Tokimon temp = tokiList.get(user_choice-1);
        temp.strength=new_strength;
        System.out.println(temp.getName() + " has now strength: " +new_strength);
        displayMenu();
    }

    public void displayMenu(){
        System.out.println("*************\n"+"* Main Menu *\n"+"*************");
        for(int i=0;i<OPTIONS_ARRAY.length;i++){
            System.out.println((i+1)+". "+OPTIONS_ARRAY[i]);
        }
        ask_user_choice();
    }

    public void ask_user_choice(){
        System.out.print("Enter your choice>.. ");
        int user_choice = input.nextInt();input.nextLine();
        while(user_choice<1 || user_choice >6){
            System.out.print("Please enter a valid choice> ");
            user_choice=input.nextInt();input.nextLine();
        }
        switch (user_choice){
            case 1: printTokiList();displayMenu();break;
            case 2: addTokimon();break;
            case 3: removeTokimon();break;
            case 4: change_Tokimon_Strength();break;
            case 5: for(int i=0; i<tokiList.size();i++){
                        System.out.println(tokiList.get(i));
                    }
                    displayMenu();
                    break;
            case 6: exit();
        }
    }

    public static void main(String[] args){
        Main myGame = new Main();
        Helper myHelper = new Helper(myGame.TITLE,myGame.OPTIONS_ARRAY);
        myGame.ask_user_choice();
    }
}
