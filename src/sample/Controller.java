package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import engine.Bot;
import engine.Gameplay;

import java.util.*;

public class Controller {
    ArrayList<ObservableList<Node>> przyciski = new ArrayList<>();
    ObservableList<Node> dzieci;
    public Bot gra ;
    @FXML
    Label tabliczka;
    @FXML
    GridPane duzyGrid;
    @FXML
    public void initialize(){
        gra = new Bot();
        duzyGrid.setStyle("-fx-border-color: black");
        dzieci = duzyGrid.getChildren();
        for (int i = 0; i < 9; i++) {

            GridPane malyGrid = (GridPane) dzieci.get(i);
            malyGrid.setId("a");
            malyGrid.setStyle("-fx-border-color: black");
            ObservableList<Node> buttony = malyGrid.getChildren();

            for (int j = 0; j < 9; j++) {
                Button przyc = (Button) buttony.get(j);
                przyc.setId("b"+i+j);
                przyc.setOnAction(e ->klikniecie1(przyc));
                przyc.setText("");
                przyc.setStyle("-fx-background-color: transparent;-fx-border-color: black");

            }
            przyciski.add(buttony);
        }





    }
    @FXML
    private void klikniecie(){

        System.out.println("kliknął");
    }




    private void klikniecie1(Button naz){
        int malyKwadrat=9;
        int pole=9;
        boolean czyGrać = true;
       String id = naz.getId();
        //małe 3x3

        malyKwadrat=Integer.parseInt(id.split("")[1]);
        pole=Integer.parseInt(id.split("")[2]);
// &&(malyKwadrat==gra.getLastlyPlayedField()||gra.getLastlyPlayedField()==9)

        if(gra.getLastlyPlayedField()==9||malyKwadrat==gra.getLastlyPlayedField()||gra.getWhoIsSquareWonBy().get(gra.getLastlyPlayedField())!= Gameplay.symbolsOfPlayers.empty)
        {if(!gra.sprawdzWygrana(malyKwadrat)&&!gra.sprawdzPelnosc(malyKwadrat)
                &&gra.tableOfGame.get(malyKwadrat).get(pole)== Gameplay.symbolsOfPlayers.empty)
        {gra.makeMove(malyKwadrat,pole);
        naz.setText("X");
        if (gra.sprawdzWygrana(malyKwadrat)){
            dzieci.get(malyKwadrat).setStyle("-fx-background-color: blue;-fx-border-color: black");
            dzieci.get(malyKwadrat).setId("wygrana");

        }
        for (int i = 0; i < dzieci.size(); i++) {
            GridPane bombelek = (GridPane) dzieci.get(i);
            if(bombelek.getId().equals("ostatni ruch bota")){
                bombelek.setStyle("-fx-background-color: transparent;-fx-border-color: black");
            }
        }
            if(gra.checkIfTableIsWon()== Gameplay.symbolsOfPlayers.cross){
                tabliczka.setText("Wygrał krzyżyk");
                czyGrać=false;
            }
       int[] ruchBota= gra.botMove();
       Button dlaBota=new Button();
       dlaBota=(Button) przyciski.get(ruchBota[0]).get(ruchBota[1]);
       dlaBota.setText("O");
       if(!gra.sprawdzWygrana(ruchBota[1]))
        {dzieci.get(ruchBota[1]).setStyle("-fx-background-color: magenta;-fx-border-color: black");
        dzieci.get(ruchBota[1]).setId("ostatni ruch bota");}
        if (gra.sprawdzWygrana(ruchBota[0])){
            dzieci.get(ruchBota[0]).setStyle("-fx-background-color: red;-fx-border-color: black");
        dzieci.get(ruchBota[0]).setId("wygrane");
        }
            if(gra.checkIfTableIsWon()== Gameplay.symbolsOfPlayers.circle){
                tabliczka.setText("Wygrało kółko");
                czyGrać=false;
            }
        }

        }
        if(!czyGrać){

        }
    }


}




