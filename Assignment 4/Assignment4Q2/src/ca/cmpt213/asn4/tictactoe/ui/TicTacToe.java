/**
 * This class is responsible for
 * launching the application,
 * creating UserInterface, and
 * updating the images as the user clicks
 *
 * It also checks if the diagonal,
 * a row, or a column has same elements
 * to declare a winner or a draw.
 */
package ca.cmpt213.asn4.tictactoe.ui;
import ca.cmpt213.asn4.tictactoe.game.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application{
    public int turn=0;
    public Label myLable = new Label("");
    public int myArray [][] = new int[4][4];
    public static void main(String[] args) {
        launch(args);
    }
    public GridPane gridpane = new GridPane();

    public void gridmethod(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                Image img = new Image("file:blank.png");
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(100);
                imgView.setFitWidth(100);
                gridpane.add(imgView,i,j);
                imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageClickHandler());
            }
        }
    }
    public void stopGame(){
        for (int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                myArray[i][j]=3;
    }
    public void xWon(){
        stopGame();
        myLable.setText("X won!");
        gridpane.add(myLable,2,4);
    }
    public void yWon(){
        stopGame();
        myLable.setText("O won!");
        gridpane.add(myLable,2,4);
    }
    public void checkDiag(){
        if(((myArray[0][0]==myArray[1][1])&&(myArray[1][1]==myArray[2][2]))&&(myArray[2][2]==myArray[3][3])){
            if(myArray[1][1]==1){
                xWon();
            }
            else if(myArray[1][1]==2){
                yWon();
            }
        }
        else if(((myArray[3][0]==myArray[2][1])&&(myArray[2][1]==myArray[1][2]))&&(myArray[1][2]==myArray[0][3])){
            if(myArray[3][0]==1){
                xWon();
            }
            else if(myArray[3][0]==2){
                yWon();
            }
        }
    }
    public void checkRows(){
        if(((myArray[0][0]==myArray[1][0])&&(myArray[1][0]==myArray[2][0]))&&(myArray[2][0]==myArray[3][0])){
            if(myArray[3][0]==1){
                xWon();
            }
            else if(myArray[3][0]==2){
                yWon();
            }
        }

        if(((myArray[0][1]==myArray[1][1])&&(myArray[1][1]==myArray[2][1]))&&(myArray[2][1]==myArray[3][1])){
            if(myArray[0][1]==1){
                xWon();
            }
            else if(myArray[0][1]==2){
                yWon();
            }
        }
        if(((myArray[0][2]==myArray[1][2])&&(myArray[1][2]==myArray[2][2]))&&(myArray[2][2]==myArray[3][2])){
            if(myArray[0][2]==1){
                xWon();
            }
            else if(myArray[0][2]==2){
                yWon();
            }
        }
        if(((myArray[0][1]==myArray[1][1])&&(myArray[1][1]==myArray[2][1]))&&(myArray[2][1]==myArray[3][1])){
            if(myArray[1][1]==1){
                xWon();
            }
            else if(myArray[1][1]==2){
                yWon();
            }
        }
        if(((myArray[0][3]==myArray[1][3])&&(myArray[1][3]==myArray[2][3]))&&(myArray[2][3]==myArray[3][3])){
            if(myArray[0][3]==1){
                xWon();
            }
            else if(myArray[0][3]==2){
                yWon();
            }
        }

    }
    public void checkCols(){
        if(((myArray[0][0]==myArray[0][1])&&(myArray[0][1]==myArray[0][2]))&&(myArray[0][2]==myArray[0][3])){
            if(myArray[0][2]==1){
                xWon();
            }
            else if(myArray[0][2]==2){
                yWon();
            }
        }
        if(((myArray[1][0]==myArray[1][1])&&(myArray[1][1]==myArray[1][2]))&&(myArray[1][2]==myArray[1][3])){
            if(myArray[1][0]==1){
                xWon();
            }
            else if(myArray[1][0]==2){
                yWon();
            }
        }
        if(((myArray[2][0]==myArray[2][1])&&(myArray[2][1]==myArray[2][2]))&&(myArray[2][2]==myArray[2][3])){
            if(myArray[2][0]==1){
                xWon();
            }
            else if(myArray[2][0]==2){
                yWon();
            }
        }
        if(((myArray[3][0]==myArray[3][1])&&(myArray[3][1]==myArray[3][2]))&&(myArray[3][2]==myArray[3][3])){
            if(myArray[3][0]==1) xWon();
            else if(myArray[3][0]==2) yWon();
        }
    }
    public void checkDraw(){
        int x=0;
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(myArray[i][j]==1){
                    x++;
                }
                else if(myArray[i][j]==2){
                    x++;
                }
            }
        }
        if(x==16){
            myLable.setText("It's a Draw!");
            gridpane.add(myLable,2,4);
        }

    }
    public void checkWinner(){
        checkDiag();
        checkRows();
        checkCols();
        checkDraw();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tic-Tac-Toe");
        Button reset = new Button("NEW GAME");
        reset.setOnAction(new ButtonClickHandler());
        reset.setMaxSize(100, 50);
        gridpane.add(reset,3,6);
        gridpane.setHgap(10);
        gridpane.setVgap(10);
        gridpane.setAlignment(Pos.TOP_CENTER);
        gridmethod();
        gridpane.setPadding(new Insets(10));
        Scene scene = new Scene(gridpane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    class ImageClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e){
            ImageView source = (ImageView)e.getSource();
            Integer colIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            int row = rowIndex.intValue();
            int col = colIndex.intValue();
            if(turn%2==0){
                if(myArray[row][col]!=0){
                    return;
                }
                ((ImageView)e.getSource()).setImage(new Image("file:x.png"));
                turn++;
                myArray[row][col]=1;
                checkWinner();
            }
            else {
                if(myArray[row][col]!=0){
                    return;
                }
                ((ImageView)e.getSource()).setImage(new Image("file:o.png"));
                turn++;
                myArray[row][col]=2;
                checkWinner();
            }
        }
    }
    class ButtonClickHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            turn=0;
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    myArray[i][j]=0;
                }
            }
            myLable.setText("");
            gridmethod();

        }
    }
}
