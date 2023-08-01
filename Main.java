package battleship;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

class Ship {

    int len;
    char letter1;
    char letter2;
    int number1;
    int number2;
    int hits;

    public Ship (int len, char letter1, char letter2, int number1, int number2) {

        this.len = len;
        this.letter1 = letter1;
        this.letter2 = letter2;
        this.number2 = number2;
        this.number1 = number1;
        hits = 0;
    }

    public void takeHit () {
        hits++;
    }

    public boolean isSunk(){

        if(hits == len)
            return true;
        else return false;

    }
    public boolean foundShip(char l, int n) {

        // if this is the ship

        if (l != letter1 && n != number1)
            return false;

        if (l == letter1 && (n > number2 || n < number1) )
            return false;

        if (n == number1 && (l > letter2 || l < letter1))
            return false;

        return true;
    }

}
class Board {

    Object [][] matrix;
    Scanner scanner = new Scanner(System.in);

    Vector <Ship> ships;
    boolean won;

    int shipsHit;
    public Board() {
        matrix = new Object[11][11];
        ships = new Vector<>();
        won = false;
        shipsHit = 0;

        matrix[0] = new Object[]{" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        matrix[1] = new Object[]{"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[2] = new Object[]{"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[3] = new Object[]{"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[4] = new Object[]{"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[5] = new Object[]{"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[6] = new Object[]{"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[7] = new Object[]{"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[8] = new Object[]{"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[9] = new Object[]{"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};
        matrix[10] = new Object[]{"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"};

    }
    public void printMatrix () {



        for (var i = 0; i < 11; i++) {
            for (var j = 0; j < 11; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printEmptyMatrix() {


        System.out.println("""
                  1 2 3 4 5 6 7 8 9 10
                A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~""");

    }

    private boolean checkNeighbours (int i, int j) {

        String error = "\nError! You placed it too close to another one. Try again:\n";

        if (i + 1 < 11) {
            if (j + 1 < 11) {
                if (matrix[i+1][j+1].equals("O")) {
                    System.out.println(error);
                    return false;
                }
            }
            if (j - 1 >= 0) {
                if (matrix[i+1][j-1].equals("O")) {
                    System.out.println(error);
                    return false;
                }
            }
            if (matrix[i+1][j].equals("O")) {
                System.out.println(error);
                return false;
            }
        } else if (i - 1 >= 0) {
            if (j + 1 < 11) {
                if (matrix[i-1][j+1].equals("O")) {
                    System.out.println(error);
                    return false;
                }
            }
            if (j - 1 >= 0) {
                if (matrix[i-1][j-1].equals("O")) {
                    System.out.println(error);
                    return false;
                }
            }
            if (matrix[i-1][j].equals("O")) {
                System.out.println(error);
                return false;
            }
        } else if (j + 1 < 11) {
            if (matrix[i][j + 1].equals("O")) {
                System.out.println(error);
                return false;
            }
        } else if (j - 1 >= 0) {
            if (matrix[i][j - 1].equals("O")) {
                System.out.println(error);
                return false;
            }
        }

        return true;
    }

    private boolean addHorizontal (char letter, int number1, int number2) {

        int row = letter - 64;

        for (var i = number1; i <= number2; i++) {
            if (!checkNeighbours(row, i))
                return false;

            if (matrix[row][i].equals("O")) {
                System.out.println("\nError! Wrong ship location! Try again: \n");
                return false;
            }

        }

        for (var i = number1; i <= number2; i++) {
            matrix[row][i] = "O";
        }

        return true;
    }
    private boolean addVertical (char letter1, char letter2, int number) {

        int first = letter1 - 64;
        int last = letter2 - 64;


        for (var i = first; i <= last; i++) {
            if (!checkNeighbours(i, number))
                return false;
            if (matrix[i][number].equals("O")) {
                System.out.println("\nError! Wrong ship location! Try again: \n");
                return false;
            }
        }

        for (var i = first; i <= last; i++) {
            matrix[i][number] = "O";
        }

        return true;
    }
    public boolean addShip (int length) {


        String first = scanner.next();
        String last = scanner.next();

        System.out.println();

      //  System.out.println("STRINGS: " + first + " " + last);

        char letter1 = first.charAt(0);
        char letter2 = last.charAt(0);

        int number1, number2;

        try {
            String num1 = first.replaceAll("[^0-9]", "");
            String num2 = last.replaceAll("[^0-9]", "");

            number1 = Integer.parseInt(num1);
            number2 = Integer.parseInt(num2);

        } catch (NumberFormatException e) {
            System.out.println("\nError! The input is not valid. It should be A-J + 1-10. Try again: \n");
            return false;
        }



        if (number1 > number2) {
            int aux = number1;
            number1 = number2;
            number2 = aux;
        }

        if (letter1 > letter2) {
            char aux = letter1;
            letter1 = letter2;
            letter2 = aux;
        }
      //  System.out.printf("l1: %c l2 %c n1 %d n2 %d\n", letter1, letter2, number1,number2);

        if (!checkCoordinates(number1, number2, letter1, letter2, length))
            return false;


        if (letter1 == letter2) {
            // add ship
            ships.add(new Ship(length, letter1, letter2, number1, number2));
            return addHorizontal(letter1, number1, number2);
        } else {

            if (number1 != number2) {
                System.out.println("\nError! Wrong coordinates. Try again: \n");
                return false;
            }
            ships.add(new Ship(length, letter1, letter2, number1, number2));
            return addVertical(letter1, letter2, number1);
        }
    }

    public boolean checkCoordinates(int n1, int n2, char l1, char l2, int len) {


        if (!Character.isLetter(l1) || !Character.isLetter(l2)) {
            System.out.println("\nError! The input is not valid. It should be A-J + 1-10. Try again: \n");
            return false;
        }

        if (l1 > 74 || l2 > 74 || l1 < 65 || l2 < 65) {
            System.out.println("\nError! The input is not valid. It should be A-J + 1-10. Try again:\n");
            return false;
        }

        if (n1 > 10 || n1 < 1 || n2 < 1 || n2 > 10) {
            System.out.println("\nError! The input is not valid. It should be A-J + 1-10. Try again: \n");
            return false;
        }

        if (n2 - n1 + 1 != len && l2 - l1 + 1 != len) {
         //   System.out.printf("N2: %d  N1: %d \n",n2, n1);
            System.out.println("\nError! Wrong length for the current ship. Try again: \n");
            return false;
        }

        return true;
    }
    public boolean addAircraftCarrier (boolean firstTime) {

        if (firstTime) {
            // Checks if it is the first time it tries to add the ship

            System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
        }

        return addShip(5);

        // ADD AIRCRAFT + CHECK COORDINATES + ADD SHIP
    }
    public boolean addBattleship (boolean firstTime) {

        if (firstTime) {
            // Checks if it is the first time it tries to add the ship

            System.out.println("Enter the coordinates of the Battleship (4 cells):\n");
        }

        return addShip(4);

        // ADD AIRCRAFT + CHECK COORDINATES + ADD SHIP
    }
    public boolean addCruiser (boolean firstTime) {

        if (firstTime) {
            // Checks if it is the first time it tries to add the ship

            System.out.println("Enter the coordinates of the Cruiser (3 cells):\n");
        }

        return addShip(3);

        // ADD AIRCRAFT + CHECK COORDINATES + ADD SHIP
    }
    public boolean addSubmarine (boolean firstTime) {

        if (firstTime) {
            // Checks if it is the first time it tries to add the ship

            System.out.println("Enter the coordinates of the Submarine (3 cells):\n");
        }

        return addShip(3);

        // ADD AIRCRAFT + CHECK COORDINATES + ADD SHIP
    }
    public boolean addDestroyer (boolean firstTime) {

        if (firstTime) {
            // Checks if it is the first time it tries to add the ship

            System.out.println("Enter the coordinates of the Destroyer (2 cells):\n");
        }

        return addShip(2);

        // ADD AIRCRAFT + CHECK COORDINATES + ADD SHIP
    }


    private boolean isBoat (char let, int num) {

        // checks if a cell has boat in it

        if (matrix[let - 64][num].equals("O"))
            return true;
        else return matrix[let - 64][num].equals("X");

    }


    public boolean takeShot (){

        String string = scanner.next();


        char letter = string.charAt(0);
        String num = string.replaceAll("[^0-9]", "");
        int number =  Integer.parseInt(num);

        if (letter > 74  || letter < 65 || number > 10 || number <= 0){
            System.out.println("\nError! You entered the wrong coordinates!");
            return false;
        }

        if (isBoat(letter, number)) {

            matrix[letter - 64][number] = "X";


            for (Ship ship : ships) {
                if (ship.foundShip(letter,number)) {
                    ship.takeHit();
                    if(ship.isSunk()) {
                        shipsHit++;
                        if (ships.size() == 1 || shipsHit == 5) {
                            System.out.println("\nYou sank the last ship. You won. Congratulations!\n");
                            won = true;
                        }
                        else {
                            System.out.println("\nYou sank a ship! Specify new target!\n");
                        }
                        ships.remove(ship);
                    }
                    else {
                        System.out.println("\nYou hit a ship\n");
                    }
                    break;
                }
            }


        } else {
            matrix[letter - 64][number] = "M";

            System.out.println("\nYou missed!\n");

        }
        return true;

    }

    public boolean isWon (){
        return won;
    }

    public void enterShips() {

        boolean worked;

        worked = addAircraftCarrier(true);
        while (!worked) {
            worked = addAircraftCarrier(false);
        }

        printMatrix();

        worked = addBattleship(true);
        while (!worked) {
            worked = addBattleship(false);
        }

        printMatrix();

        worked = addSubmarine(true);
        while (!worked) {
            worked = addSubmarine(false);
        }
        printMatrix();

        worked = addCruiser(true);
        while (!worked) {
            worked = addCruiser(false);
        }
        printMatrix();

        worked = addDestroyer(true);
        while (!worked) {
            worked = addDestroyer(false);
        }

        printMatrix();

    }

}



public class Main {


    public static void nextPlayer() throws IOException {
        System.out.println("Press Enter and pass the move to another player\n...");

        while(System.in.read() != 10)
            if(System.in.read() == 10)
                break;
    }

    public static void main(String[] args) throws IOException {

        Board board1 = new Board();
        Board board2 = new Board();

        boolean worked;


        System.out.println("Player 1, place your ships on the game field");
        board1.printMatrix();
        board1.enterShips();

        nextPlayer();

        System.out.println("Player 2, place your ships on the game field");
        board2.printMatrix();
        board2.enterShips();


        nextPlayer();

        while (!board1.isWon() && !board2.isWon()) {

            board2.printEmptyMatrix();
            System.out.println("---------------------");
            board1.printMatrix();

            System.out.println("Player 1, it's your turn:\n");
            worked = board2.takeShot();

            while (!worked) {

                board2.printEmptyMatrix();
                System.out.println("---------------------");
                board1.printMatrix();

                worked = board2.takeShot();

            }



            if (board2.isWon()) {
                System.out.println("Congratulations player 1! You won!\n \n \n ");
                break;
            } else {

                nextPlayer();

                board2.printEmptyMatrix();
                System.out.println("---------------------");
                board2.printMatrix();

                System.out.println("Player 2, it's your turn:\n");
                worked = board1.takeShot();

                while (!worked) {
                    board2.printEmptyMatrix();
                    System.out.println("---------------------");
                    board2.printMatrix();
                    worked = board1.takeShot();

                }
                if (board1.isWon()) {
                    System.out.println("Congratulations player 2! You won!\n \n \n ");
                    break;
                } else {
                    nextPlayer();
                }
            }
        }


    }
}
