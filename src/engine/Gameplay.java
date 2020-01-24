package engine;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gameplay {

    public enum symbolsForWinningPossibilities {player, none}

    public ArrayList<ArrayList<symbolsForWinningPossibilities>> winningASquarePossibilities;

    public enum symbolsOfPlayers {circle, cross, empty;}


    ;

    //    all 81 fields are meant to be tableOfGame
    //    tableOfGame is to be read that way:
    //    first coordinate is number of square
    //    second coordinate is number of field

    public ArrayList<ArrayList<symbolsOfPlayers>> tableOfGame;

    // each of 9 squares 3x3 is meant to be square

    private ArrayList<Boolean> isSquareWon;
    protected ArrayList<symbolsOfPlayers> whoIsSquareWonBy;

    // each of 81 fields is meant to be a field
    //    is lastlyPlayedField equal to 9 means that we have first turn or the
    //    last turn ended by winning a square

    private int lastlyPlayedField = 9;

    private ArrayList<Boolean> isSquareFull;
    //    if whoPlays == 1 then X plays, is whoPlays==2 then O plays
    protected symbolsOfPlayers whoPlays = symbolsOfPlayers.cross;


    public Gameplay() {
        tableOfGame = new ArrayList<>();
        clearTableOfGame();
        isSquareFull = new ArrayList<>(9);

        whoIsSquareWonBy = new ArrayList<>(9);
        winningASquarePossibilities = new ArrayList<ArrayList<symbolsForWinningPossibilities>>(8);

        for (int i = 0; i < 9; i++) {
            isSquareFull.add(false);
            whoIsSquareWonBy.add(symbolsOfPlayers.empty);
            winningASquarePossibilities.add(new ArrayList<symbolsForWinningPossibilities>(9));
            for (int j = 0; j < 9; j++) {
                winningASquarePossibilities.get(i).add(symbolsForWinningPossibilities.none);
            }
        }
        for (int i = 0; i < 3; i++) {
            winningASquarePossibilities.get(i).set(0 + 3 * i, symbolsForWinningPossibilities.player);
            winningASquarePossibilities.get(i).set(1 + 3 * i, symbolsForWinningPossibilities.player);
            winningASquarePossibilities.get(i).set(2 + 3 * i, symbolsForWinningPossibilities.player);
        }
        for (int i = 0; i < 3; i++) {
            winningASquarePossibilities.get(3 + i).set(i, symbolsForWinningPossibilities.player);
            winningASquarePossibilities.get(3 + i).set(i + 3, symbolsForWinningPossibilities.player);
            winningASquarePossibilities.get(3 + i).set(i + 6, symbolsForWinningPossibilities.player);
        }
        winningASquarePossibilities.get(6).set(0, symbolsForWinningPossibilities.player);
        winningASquarePossibilities.get(6).set(4, symbolsForWinningPossibilities.player);
        winningASquarePossibilities.get(6).set(8, symbolsForWinningPossibilities.player);
        winningASquarePossibilities.get(7).set(2, symbolsForWinningPossibilities.player);
        winningASquarePossibilities.get(7).set(4, symbolsForWinningPossibilities.player);
        winningASquarePossibilities.get(7).set(6, symbolsForWinningPossibilities.player);
    }

    public char printPlayer(symbolsOfPlayers player) {

        if (player == symbolsOfPlayers.cross) {
            return 'X';
        } else if (player == symbolsOfPlayers.circle) {
            return 'O';
        } else {
            return ' ';
        }
    }

    public ArrayList<ArrayList<symbolsOfPlayers>> getTableOfGame() {
        return tableOfGame;
    }

    // clearTableOfGame shall be used at the beginning of game
    // and then if player wishes to play another round

    public void clearTableOfGame() {

        tableOfGame = new ArrayList<>(9);
        int d = 0;

        for (int i = 0; i < 9; i++) {
            tableOfGame.add(new ArrayList<symbolsOfPlayers>(9));
            for (int j = 0; j < 9; j++) {
                tableOfGame.get(i).add(symbolsOfPlayers.empty);
            }
        }


    }

    // method printLine is used only by method printTableOfGame
    private void printLine(int numberOfSquare, int numberOfLine) {

        for (int i = 3 * numberOfLine; i < 3 * numberOfLine + 3; i++) {
            System.out.print(printPlayer(tableOfGame.get(numberOfSquare).get(i)) + " ");

        }
    }

    public void printTableOfGame() {
// i = # of row on screen
//
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                for (int j = 0; j < 25; j++) {
                    System.out.print("-");
                }
                System.out.println();
            }
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                printLine((((int) Math.floor(i / 3))) * 3 + j, i % 3);
                System.out.print("| ");
            }
            System.out.println();

        }
        for (int j = 0; j < 25; j++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public int getLastlyPlayedField() {
        return lastlyPlayedField;
    }

    public void setLastlyPlayedField(int lastlyPlayedField) {
        this.lastlyPlayedField = lastlyPlayedField;
    }

    public ArrayList<symbolsOfPlayers> getWhoIsSquareWonBy() {
        return whoIsSquareWonBy;
    }

    public ArrayList<Boolean> getIsSquareWon() {
        return isSquareWon;
    }

    //
    private int getNumber() {
        Scanner scan = new Scanner(System.in);
        int number = -1;
        boolean amIToWork = true;
        while (amIToWork) {
            try {
                int scannedValue = scan.nextInt();
                if (scannedValue >= 0 && scannedValue <= 8) {
                    number = scannedValue;
                    amIToWork = false;
                }
            } catch (InputMismatchException e) {
                scan.nextLine();
                continue;
            }
        }
        return number;
    }

    public boolean checkNumberOfSquare(int numberOfSquare) {
        if (!isSquareFull.get(numberOfSquare) && whoIsSquareWonBy.get(numberOfSquare) == symbolsOfPlayers.empty ) {
            if(getLastlyPlayedField()==9) {
                return true;}
            else if (getLastlyPlayedField()==numberOfSquare){
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean checkNumberOfField(int numberOfSquare, int numberOfField) {
        if (tableOfGame.get(numberOfSquare).get(numberOfField) == symbolsOfPlayers.empty) {
            return true;
        } else {
            return false;
        }
    }

    public void makeMove(int numberOfSquare,int numberOfField) {
//        int numberOfSquare = lastlyPlayedField;
//        System.out.println(whoPlays+"'s turn");
//        if (numberOfSquare == 9 || !checkNumberOfSquare(numberOfSquare)) {
//            boolean amIToWork = true;
//            while (amIToWork) {
//                System.out.println("You can choose any square you want");
//                System.out.println("Enter number of square");
//                int value = getNumber();
//                if (checkNumberOfSquare(value)) {
//                    numberOfSquare = value;
//                    amIToWork = false;
//                }
//            }
//        }
//
//        int numberOfField = 9;
//
//        boolean amIToWork = true;
//        while (amIToWork) {
//            System.out.println("You have to play on " + numberOfSquare + " square");
//            System.out.println("Enter number of field:");
//            int value = getNumber();
//            if (checkNumberOfField(numberOfSquare, value)) {
//                numberOfField = value;
//                amIToWork = false;
//            }
//
//        }

        tableOfGame.get(numberOfSquare).set(numberOfField,symbolsOfPlayers.cross);

        setLastlyPlayedField(numberOfField);


//        if(sprawdzWygrana(numberOfField)){
//            setLastlyPlayedField(9);
//        }
//        setLastlyPlayedField(numberOfField);
//        if (whoIsSquareWonBy.get(numberOfSquare) != symbolsOfPlayers.empty) {
//            System.out.println(whoIsSquareWonBy.get(numberOfSquare) + " won the square " + numberOfSquare);
//            System.out.println("Congratulations!!!");
//            setLastlyPlayedField(9);
//        }
//
        if(checkIfSquareIsWon(numberOfSquare,symbolsOfPlayers.cross)){
            whoIsSquareWonBy.set(numberOfSquare,symbolsOfPlayers.cross);
        }
//        if(sprawdzWygrana(numberOfField)||sprawdzPelnosc(numberOfField)){
//            setLastlyPlayedField(9);
//        }
//        else
//        {setLastlyPlayedField(numberOfField);}
        if (checkIfTableIsWon() != symbolsOfPlayers.empty) {
            System.out.println(checkIfTableIsWon() + " is the winner!!!");
            System.out.println("Congratulations!!!");

        }

//        if (whoPlays == symbolsOfPlayers.cross) {
//            whoPlays = symbolsOfPlayers.circle;
//        } else {
//            whoPlays = symbolsOfPlayers.cross;
//        }



    }

    //    this method will check if move made is a winning one
//    is to be used before changing whoPlays to the opponent
    public boolean checkIfSquareIsWon(int numberOfSquare,symbolsOfPlayers gracz) {

        for (int i = 0; i < winningASquarePossibilities.size(); i++) {
            int numberOfMetPoints = 0;
            for (int j = 0; j < 9; j++) {
                if (winningASquarePossibilities.get(i).get(j) == symbolsForWinningPossibilities.player & tableOfGame.get(numberOfSquare).get(j) == gracz) {
                    numberOfMetPoints++;
                    if (numberOfMetPoints == 3) {
                        return true;

                    }
                }
            }
        }
        return false;
    }
    public boolean sprawdzWygrana(int numberOfSquare) {

        if (whoIsSquareWonBy.get(numberOfSquare) != symbolsOfPlayers.empty) {
           return true;
        }
        return false;
    }

    public boolean sprawdzPelnosc(int numberOfSquare) {

        for (int i = 0; i < 9; i++) {

            if (tableOfGame.get(numberOfSquare).get(i) == symbolsOfPlayers.empty) {
                return false;
            }
        }
        return true;
    }
    public void checkIfSquareIsFull(int numberOfSquare) {
        int numberOfFullFields = 0;
        for (int i = 0; i < 9; i++) {

            if (tableOfGame.get(numberOfSquare).get(i) == symbolsOfPlayers.empty) {
                break;
            } else {
                numberOfFullFields++;
            }
        }
        if (numberOfFullFields == 9) {
            isSquareFull.set(numberOfSquare, true);
            lastlyPlayedField = 9;
        }
    }

    public symbolsOfPlayers checkIfTableIsWon() {
        ArrayList<Boolean> isMet = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            isMet.add(false);
        }


        for (int i = 0; i < winningASquarePossibilities.size(); i++) {
            int numberOfMetPoints = 0;
            for (int j = 0; j < 9; j++) {
                if (winningASquarePossibilities.get(i).get(j) == symbolsForWinningPossibilities.player & whoIsSquareWonBy.get(j) == whoPlays) {
                    isMet.set(numberOfMetPoints, true);
                    numberOfMetPoints++;
                    if (numberOfMetPoints == 3) {
                        return whoPlays;
                    }
                }
            }
        }
        return symbolsOfPlayers.empty;
    }
}
