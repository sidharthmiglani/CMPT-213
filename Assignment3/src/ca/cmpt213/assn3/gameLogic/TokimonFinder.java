/**
 * Manages the program according to
 * given command line arguments.
 *
 * This is the main class "TokimonFinder"
 * This class is responsible for taking
 * user inputs and interacting with the
 * UserInterface package.
 *
 * Responsible for changing the grid location.
 *
 */

package ca.cmpt213.assn3.gameLogic;
import ca.cmpt213.assn3.userInterface.*;


import java.util.*;

public class TokimonFinder {
    public int numToki=0;
    public int numFoki=0;
    public int collectedToki=0;
    public boolean cheat;
    public String initialPosition;
    public GridMaker cheat_grid;
    public GridForUser non_cheat_grid;
    int first_initial_pos;
    int second_initial_pos;
    Scanner input = new Scanner(System.in);
    String spellCellInput;

    public String user_input;
    private int spells[] = {0, 0, 0};
    public String spell_user_input;

    public void make_grid(){
        cheat_grid=new GridMaker(numToki,numFoki);
    }
    public void make_non_cheat_grid(){
        non_cheat_grid = new GridForUser(first_initial_pos,second_initial_pos);
    }

    public void position_clash(){
        if(!cheat_grid.actual_values[first_initial_pos][second_initial_pos].isBlank()) {
            if (cheat_grid.actual_values[first_initial_pos][second_initial_pos].equals("$")) {
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] =
                        non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] +
                                cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                collectedToki+=1;
                System.out.println("\n\nCongratulations! You landed on a Tokimon");
                numToki-=1;
                //System.out.println("Total Tokimon left: "+ numToki +"\n\n");
            }
            else{
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] =
                        non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] +
                                cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                cheat_grid.actual_values[first_initial_pos][second_initial_pos]="@"+cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                cheat_grid.printGrid();
                System.out.println("\n\nYou landed on a Fokimon!! Game Over!!");
                System.exit(-1);

            }
        }
    }
    public void ask_user_choice() {
            System.out.println("Please choose one of the following options:");
            System.out.println("W - move up");
            System.out.println("A - move left");
            System.out.println("S - move down");
            System.out.println("D - move right");
            System.out.println("SPELL - use a spell");
            System.out.print("Please enter your choice: ");
            user_input = input.nextLine();
            user_input = user_input.toUpperCase().trim();
            while(!validate_user_choice()){
                System.out.println("Please choose a valid option:");
                System.out.println("W - move up");
                System.out.println("A - move left");
                System.out.println("S - move down");
                System.out.println("D - move right");
                System.out.println("SPELL - use a spell");
                System.out.print("Please enter your choice: ");
                user_input = input.nextLine();
                user_input = user_input.toUpperCase().trim();
            }
            if (user_input.equals("SPELL")) {
                System.out.println("\nChoose one of the following spells: ");
                System.out.println("1. Jump the player to another grid location");
                System.out.println("2. Randomly reveal location of one Tokimons");
                System.out.println("3. Randomly kill one Fokimons ");
                spell_user_input= input.nextLine().trim();
                while(!validate_spell_choice()){
                    System.out.println("\nChoose a valid spell: ");
                    System.out.println("1. Jump the player to another grid location");
                    System.out.println("2. Randomly reveal location of one Tokimons");
                    System.out.println("3. Randomly kill one Fokimons ");
                    spell_user_input= input.nextLine().trim();
                }
                if (spell_user_input.equals("1")) {
                    if (spells[0] == 0) {
                        spells[0] = 1;

                        if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@$")) {
                            non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="$";
                        }
                        else if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@")){
                            non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]=" ";
                        }
                        HashMap<Character, Integer> charTonumber = new HashMap<Character, Integer>();
                        charTonumber.put('A',0);
                        charTonumber.put('B',1);
                        charTonumber.put('C',2);
                        charTonumber.put('D',3);
                        charTonumber.put('E',4);
                        charTonumber.put('F',5);
                        charTonumber.put('G',6);
                        charTonumber.put('H',7);
                        charTonumber.put('I',8);
                        charTonumber.put('J',9);
                        System.out.println("\n\nPlease enter the cell location where you want land: ");
                        spellCellInput=input.nextLine().toUpperCase().trim();
                        while(spellCellInput.length()>3||spellCellInput.length()<2){
                            System.out.println("\n\nEnter a valid cell location: ");
                            spellCellInput=input.nextLine().toUpperCase().trim();
                        }
                        if(spellCellInput.length()==3){
                            while (spellCellInput.charAt(0)<65 || spellCellInput.charAt(0)>74|| spellCellInput.charAt(1)!=49|| spellCellInput.charAt(2)!=48){
                                System.out.println("\n\nEnter a valid cell location: ");
                                spellCellInput=input.nextLine().toUpperCase().trim();
                            }
                            first_initial_pos=charTonumber.get(spellCellInput.charAt(0));
                            second_initial_pos=9;
                        }
                        if(spellCellInput.length()==2){
                            while(spellCellInput.charAt(0)<65 || spellCellInput.charAt(0)>74||spellCellInput.charAt(1)<49 || spellCellInput.charAt(1)>57){
                                System.out.println("\n\nEnter a valid cell location: ");
                                spellCellInput=input.nextLine().toUpperCase().trim();
                            }
                            first_initial_pos=charTonumber.get(spellCellInput.charAt(0));
                            String temp= new StringBuilder().append(spellCellInput.charAt(1)).toString();
                            second_initial_pos=Integer.parseInt(temp)-1;
                        }
                        non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="@";
                        position_clash();
                        non_cheat_grid.printGrid();
                        non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                        System.out.println("Total Tokimons Collected: "+ collectedToki);
                        System.out.println("Total Tokimons left: "+ numToki);
                        int temp=3-(spells[0]+spells[1]+spells[2]);
                        System.out.println("SPELL Left: "+temp);
                        ask_user_choice();
                    }
                    else {
                        System.out.println("Spell has already been used: Please try again\n\n");
                    }
                }
                else if(spell_user_input.equals("2")){
                    if(spells[1] == 0){
                        spells[1] = 1;


                    }
                    else{
                            System.out.println("Spell has already been used: Please try again\n\n");
                    }
                }
                else if (spell_user_input.equals("3")) {
                    if (spells[2] == 0) {
                        spells[2] = 1;
                        //spellUsed = true;
                    }
                    else {
                        System.out.println("Spell has already been used: Please try again\n\n");
                    }
                }

            }
            else if (user_input.equals("W")){
                if(first_initial_pos==0){
                    System.out.println("\n\nCan not go out of game scope!\n\n");
                    ask_user_choice();
                }
                if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@$")) {
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="$";
                }
                else if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@")){
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]=" ";
                }

                first_initial_pos-=1;
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="@";
                position_clash();
                non_cheat_grid.printGrid();
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                System.out.println("Total Tokimons Collected: "+ collectedToki);
                System.out.println("Total Tokimons left: "+ numToki);
                int temp=3-(spells[0]+spells[1]+spells[2]);
                System.out.println("SPELL left: "+temp );
                ask_user_choice();
            }
            else if (user_input.equals("A")){
                if(second_initial_pos==0){
                    System.out.println("\n\nCan not go out of game scope!\n\n");
                    ask_user_choice();
                }
                if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@")) {
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = " ";
                }
                second_initial_pos-=1;
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="@";
                position_clash();
                non_cheat_grid.printGrid();
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                System.out.println("Total Tokimons Collected: "+ collectedToki);
                System.out.println("Total Tokimons left: "+ numToki);
                int temp=3-(spells[0]+spells[1]+spells[2]);
                System.out.println("SPELL left: "+temp );
                ask_user_choice();
            }
            else if(user_input.equals("S")){
                if(first_initial_pos==9){
                    System.out.println("\n\nCan not go out of game scope!\n\n");
                    ask_user_choice();
                }
                if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@$")) {
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = "$";
                }
                else if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@")){
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]=" ";
                }
                first_initial_pos+=1;
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="@";
                position_clash();
                non_cheat_grid.printGrid();
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                System.out.println("Total Tokimons Collected: "+ collectedToki);
                System.out.println("Total Tokimons left: "+ numToki);
                int temp=3-(spells[0]+spells[1]+spells[2]);
                System.out.println("SPELL left: "+temp );
                ask_user_choice();
            }
            else if(user_input.equals("D")){
                if(second_initial_pos==9){
                    System.out.println("\n\nCan not go out of game scope!\n\n");
                    ask_user_choice();
                }
                if(non_cheat_grid.fake_values[first_initial_pos][second_initial_pos].equals("@")) {
                    non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = " ";
                }
                second_initial_pos+=1;
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos]="@";
                position_clash();
                non_cheat_grid.printGrid();
                non_cheat_grid.fake_values[first_initial_pos][second_initial_pos] = cheat_grid.actual_values[first_initial_pos][second_initial_pos];
                System.out.println("Total Tokimons Collected: "+ collectedToki);
                System.out.println("Total Tokimons left: "+ numToki);
                int temp=3-(spells[0]+spells[1]+spells[2]);
                System.out.println("SPELL left: "+temp );
                ask_user_choice();
            }
    }

    public boolean validate_user_choice(){
        if (user_input.equals("SPELL") || user_input.equals("W") || user_input.equals("A")
                || user_input.equals("S") || user_input.equals("D")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validate_spell_choice(){
        if (spell_user_input.equals("1")||spell_user_input.equals("2")||spell_user_input.equals("3")) return true;
        else return false;
    }


    public static void main(String[] args) {
        TokimonFinder myGame = new TokimonFinder();
        Scanner input = new Scanner(System.in);

        //Error checking for command line inputs
        if(args.length>3){
            System.out.println("More than 3 arguments entered !!!");
            System.exit(-1);
        }
        else if(args.length==0){
            myGame.numToki=10;
            myGame.numFoki=5;
            myGame.cheat=false;
        }
        for(int i=0;i<args.length;i++){
            if (args[i].matches("(.*)numToki(.*)")){
                args[i] = args[i].replace("--numToki=", "");
                if(args[i].isBlank()){
                    myGame.numToki=10;
                }
                else if(Integer.parseInt(args[i])<5){
                    System.out.println("Number of Tokimons can't be less than 5! Please retry.");
                    System.exit(-1);
                }
                else{
                    myGame.numToki=Integer.parseInt(args[i]);
                }

            }
            else if(args[i].matches("(.*)numFoki(.*)")){
                args[i] = args[i].replace("--numFoki=", "");
                if(args[i].isBlank()){
                    myGame.numFoki=5;
                }
                else if(Integer.parseInt(args[i])<5){
                    System.out.println("Number of Fokimons can't be less than 5! Please retry.");
                    System.exit(-1);
                }
                else{
                    myGame.numFoki=Integer.parseInt(args[i]);
                }
            }
            else if(args[i].matches("(.*)cheat(.*)")){
                myGame.cheat=true;
            }
        }
        if (myGame.numToki<5){
            myGame.numToki=10;
        }
        if (myGame.numFoki<5){
            myGame.numFoki=5;
        }

        // Taking and validating starting position from the user
        System.out.println("\n\nPlease enter a starting position: For rows enter (A to J) For col enter (1 to 10)");
        myGame.initialPosition = input.nextLine();
        myGame.initialPosition=myGame.initialPosition.toUpperCase();
        char pos1,pos2,pos3;
        HashMap<Character, Integer> charTonumber = new HashMap<Character, Integer>();
        charTonumber.put('A',0);
        charTonumber.put('B',1);
        charTonumber.put('C',2);
        charTonumber.put('D',3);
        charTonumber.put('E',4);
        charTonumber.put('F',5);
        charTonumber.put('G',6);
        charTonumber.put('H',7);
        charTonumber.put('I',8);
        charTonumber.put('J',9);
        while(myGame.initialPosition.length()>3 || myGame.initialPosition.length()<2){
            System.out.println("The starting position is not valid!");
            System.out.println("Please re-enter a starting position: ");
            myGame.initialPosition=input.nextLine().toUpperCase();
        }
        if(myGame.initialPosition.length()==3){
            pos1 = myGame.initialPosition.charAt(0);
            pos2 = myGame.initialPosition.charAt(1);
            pos3 = myGame.initialPosition.charAt(2);
            while(pos1<65 || pos1> 74 || pos2!=49 || pos3!=48){
                System.out.println("The starting position is not valid!");
                System.out.println("Please re-enter a starting position: ");
                myGame.initialPosition=input.nextLine().toUpperCase();
                if(myGame.initialPosition.length()!=3){
                    break;
                }
                myGame.initialPosition=myGame.initialPosition.toUpperCase();
                pos1 = myGame.initialPosition.charAt(0);
                pos2 = myGame.initialPosition.charAt(1);
                pos3 = myGame.initialPosition.charAt(2);
            }
            myGame.first_initial_pos=charTonumber.get(pos1);
            String temp= new StringBuilder().append(pos2).append(pos3).toString();
            myGame.second_initial_pos=Integer.parseInt(temp)-1;

        }
        if (myGame.initialPosition.length()==2) {
            pos1 = myGame.initialPosition.charAt(0);
            pos2 = myGame.initialPosition.charAt(1);
            while (pos1 < 65 || pos1 > 74 || pos2 < 49 || pos2 > 57) {
                System.out.println("The starting position is not valid!");
                System.out.println("Please re-enter a starting position: ");
                myGame.initialPosition = input.nextLine();
                myGame.initialPosition = myGame.initialPosition.toUpperCase();
                pos1 = myGame.initialPosition.charAt(0);
                pos2 = myGame.initialPosition.charAt(1);

            }
            myGame.first_initial_pos=charTonumber.get(pos1);
            myGame.second_initial_pos=pos2-'0'-1;
        }



        myGame.make_grid();
        if(myGame.cheat==true){
            myGame.cheat_grid.printGrid();
        }
        myGame.make_non_cheat_grid();

        myGame.position_clash();
        myGame.non_cheat_grid.printGrid();
        myGame.ask_user_choice();
//        if(!(myGrid.actual_values[position1][position2].isBlank())){
//            userGrid.fake_values[position1][position2]= userGrid.fake_values[position1][position2]+ myGrid.actual_values[position1][position2];
//        }
        //userGrid.printGrid();



    }
}
