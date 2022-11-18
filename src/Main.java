import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Tank playerOne = new Tank(true);
        Tank playerTwo = new Tank(false);
        int currentPlayer = 1;
        boolean isValid = false;
        String input = "";
        String inputTwo = "";
        int tempInt = 0;
        Scanner scanner = new Scanner(System.in);
        Field newGameField = new Field(playerOne, playerTwo);
        System.out.println("\n\nWelcome to Tanks! Each tank has three lives, the goal is to destroy the other tank before they destroy yours!");
        System.out.println("Here is the game-board. U is player one, with X marking recent shot, and W is player two, with K marking recent shot");
        newGameField.printField();
        System.out.println("You will take turns shooting. Each turn you have the option to move your tank five spaces to the left or right.");
        System.out.println("After that, you will have the option to change the degree, power, or to shoot.");
        while (playerOne.lives > 0 && playerTwo.lives > 0) {
            Tank tempTank;
            Tank otherTank;
            if (currentPlayer == 1) {
                tempTank = playerOne;
                otherTank = playerTwo;
            }
            else {
                tempTank = playerTwo;
                otherTank = playerOne;
            }
            System.out.printf("Player %d's turn\nHere is the current board\n", currentPlayer);
            newGameField.printField();
            System.out.printf("Lives remaining - Player One: %d, Player Two: %d\n", playerOne.lives, playerTwo.lives);
            while (!isValid) {
                System.out.println("Which way would you like to move? (left, right, none)");
                input = scanner.next();
                if (input.equals("left") || input.equals("right")) {
                    boolean isNumber = false;
                    while (!isNumber) {
                        System.out.println("How many steps would you like to move? (type 'back' to return to choose the way you would like to move)");
                        inputTwo = scanner.next();
                        if (inputTwo.equals("back")) {
                            isNumber = true;
                        }
                        else if (inputTwo.chars().allMatch( Character::isDigit )) {
                            tempInt = Integer.parseInt(inputTwo);
                            if (tempInt > 5 || tempInt < 1) {
                                System.out.println("Invalid input");
                            }
                            else {
                                tempTank.moveTank(input, tempInt);
                                System.out.println("Here is the field now.");
                                newGameField.printField();
                                isNumber = true;
                                isValid = true;
                            }
                        }
                        else {
                            System.out.println("Invalid input");
                        }
                    }
                }
                else if (input.equals("none")) {
                    isValid = true;
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            isValid = false;
            while (!isValid) {
                System.out.println("Would you like to change the power, degree, or shoot? (power, degree, shoot)");
                int myDegree = tempTank.degree;
                if (!tempTank.isLeft) {
                    myDegree = 180 - myDegree;
                }
                System.out.printf("Current power and degree\nPower: %s, Degree: %d\n", tempTank.power, myDegree);
                input = scanner.next();
                if (input.equals("shoot")) {
                    isValid = true;
                }
                else if (input.equals("power")) {
                    boolean isNumber = false;
                    while (!isNumber) {
                        System.out.println("How much power do you want? (type back to return)");
                        inputTwo = scanner.next();
                        if (inputTwo.equals("back")) {
                            isNumber = true;
                        }
                        else {
                            double myPower = 0.0;
                            try {
                                myPower = Double.parseDouble(inputTwo);
                                if (myPower > 300 || myPower < 1) {
                                    throw new NumberFormatException();
                                }
                                else {
                                    isNumber = true;
                                    tempTank.changePower(myPower);
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input");
                            }
                        }
                    }
                }
                else if (input.equals("degree")) {
                    boolean isNumber = false;
                    while (!isNumber) {
                        System.out.println("What angle do you want? (type back to return)");
                        inputTwo = scanner.next();
                        if (inputTwo.equals("back")) {
                            isNumber = true;
                        }
                        else if (inputTwo.chars().allMatch( Character::isDigit )) {
                            tempInt = Integer.parseInt(inputTwo);
                            if (tempInt > 90 || tempInt < 0) {
                                System.out.println("Invalid input");
                            }
                            else {
                                isNumber = true;
                                tempTank.changeDegree(tempInt);
                            }
                        }
                        else {
                            System.out.println("Invalid input");
                        }
                    }

                }
            }
            isValid = false;
            Fire newFire = new Fire(tempTank);
            int positionOfShot = newFire.fire();
            if (positionOfShot == -1) {
                System.out.println("Way too much power, you hit the wall!");
            }
            else if (positionOfShot == -2) {
                System.out.println("You hit the wall!");
            }
            else {
                tempTank.lastShotPosition = positionOfShot;
                if (positionOfShot == otherTank.position) {
                    otherTank.lostLife();
                    System.out.println("THAT'S A HIT");
                } else if (positionOfShot > otherTank.position) {
                    if (currentPlayer == 1) {
                        System.out.printf("You overshot by %d spaces\n", positionOfShot - otherTank.position);
                    } else {
                        System.out.printf("You undershot by %d spaces\n", positionOfShot - otherTank.position);
                    }
                } else {
                    if (currentPlayer == 1) {
                        System.out.printf("You undershot by %d spaces\n", otherTank.position - positionOfShot);
                    } else {
                        System.out.printf("You overshot by %d spaces\n", otherTank.position - positionOfShot);
                    }
                }
            }
            if (currentPlayer == 1) {
                currentPlayer = 2;
            } else {
                currentPlayer = 1;
            }
        }
        if (currentPlayer == 2) {
            System.out.println("PLAYER ONE WINS");
        }
        else {
            System.out.println("PLAYER TWO WINS");
        }
    }
}
