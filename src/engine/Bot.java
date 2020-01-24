package engine;

import java.util.ArrayList;
import java.util.Random;


public class Bot extends Gameplay {

    public int[] botMove() {
        Random rand = new Random();
        int numberOfSquare = getLastlyPlayedField();
        boolean amIToWork = true;
        if (numberOfSquare == 9||sprawdzPelnosc(numberOfSquare)||sprawdzWygrana(numberOfSquare)) {
            while (amIToWork) {
                int value = rand.nextInt(9);
                if (!sprawdzPelnosc(value)&&!sprawdzWygrana(value)) {
                    numberOfSquare = value;
                    amIToWork = false;
                }
            }

        }
        int numberOfField = 9;
        amIToWork = true;
        while (amIToWork) {
            int value = rand.nextInt(9);
            if (tableOfGame.get(numberOfSquare).get(value)==symbolsOfPlayers.empty) {
                numberOfField = value;
                amIToWork = false;
            }

        }





        tableOfGame.get(numberOfSquare).set(numberOfField, symbolsOfPlayers.circle);
        setLastlyPlayedField(numberOfField);
        if(checkIfSquareIsWon(numberOfSquare,symbolsOfPlayers.circle)){
            whoIsSquareWonBy.set(numberOfSquare,symbolsOfPlayers.circle);

        }


        if (whoIsSquareWonBy.get(numberOfSquare) != symbolsOfPlayers.empty) {
            System.out.println(whoIsSquareWonBy.get(numberOfSquare) + " won the square " + numberOfSquare);
            System.out.println("Congratulations!!!");

        }
        //checkIfSquareIsFull(numberOfSquare);
        if (checkIfTableIsWon() != symbolsOfPlayers.empty) {
            System.out.println(checkIfTableIsWon() + " is the winner!!!");
            System.out.println("Congratulations!!!");

        }

//        if (whoPlays == symbolsOfPlayers.cross) {
//            whoPlays = symbolsOfPlayers.circle;
//        } else {
//            whoPlays = symbolsOfPlayers.cross;
//        }
        return new int[]{numberOfSquare,numberOfField};

    }
}