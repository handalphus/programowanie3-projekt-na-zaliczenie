package engine;

import java.util.ArrayList;
import java.util.Random;


public class Bot extends Gameplay {

    public void botMove() {
        Random rand = new Random();
        int numberOfSquare = getLastlyPlayedField();
        boolean amIToWork = true;
        if (numberOfSquare == 9||!checkNumberOfSquare(numberOfSquare)) {
            while (amIToWork) {
                int value = rand.nextInt(9);
                if (checkNumberOfSquare(value)) {
                    numberOfSquare = value;
                    amIToWork = false;
                }
            }

        }
        int numberOfField = 9;
        amIToWork = true;
        while (amIToWork) {
            int value = rand.nextInt(9);
            if (checkNumberOfField(numberOfSquare, value)) {
                numberOfField = value;
                amIToWork = false;
            }

        }
        tableOfGame.get(numberOfSquare).set(numberOfField, whoPlays);
        checkIfSquareIsWon(numberOfSquare);
        setLastlyPlayedField(numberOfField);
        if (whoIsSquareWonBy.get(numberOfSquare) != symbolsOfPlayers.empty) {
            System.out.println(whoIsSquareWonBy.get(numberOfSquare) + " won the square " + numberOfSquare);
            System.out.println("Congratulations!!!");
            setLastlyPlayedField(9);
        }
        checkIfSquareIsFull(numberOfSquare);
        if (checkIfTableIsWon() != symbolsOfPlayers.empty) {
            System.out.println(checkIfTableIsWon() + " is the winner!!!");
            System.out.println("Congratulations!!!");
            return ;
        }

        if (whoPlays == symbolsOfPlayers.cross) {
            whoPlays = symbolsOfPlayers.circle;
        } else {
            whoPlays = symbolsOfPlayers.cross;
        }


    }
}