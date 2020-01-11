package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import engine.Bot;
import engine.Gameplay;
public class Controller {

    public Bot gra ;
    @FXML
    Label tabliczka;
    @FXML
    GridPane duzyGrid;
    @FXML
    public void initialize(){
        gra = new Bot();
        ObservableList<Node> dzieci = duzyGrid.getChildren();
        for (int i = 0; i < 9; i++) {
            GridPane malyGrid = (GridPane) dzieci.get(i);
            ObservableList<Node> buttony = malyGrid.getChildren();
            for (int j = 0; j < 9; j++) {
                Button przyc = (Button) buttony.get(j);
                przyc.setId("b"+i+j);
                przyc.setOnAction(e ->klikniecie1(przyc));
            }
        }




    }
    @FXML
    private void klikniecie(){

        System.out.println("kliknął");
    }

    private void klikniecie1(Button naz){
        String id = naz.getId();
        //małe 3x3

        int malyKwadrat=Integer.parseInt(id.split("")[1]);
        int pole=Integer.parseInt(id.split("")[2]);
        gra.makeMove(malyKwadrat,pole);
        naz.setText("X");
        gra.botMove();


    }


}




