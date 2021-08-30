import java.util.Scanner;
public class Battleship {
static int hitCounter1 = 0;
static int hitCounter2 = 0;
public static void main(String[] args) {

								Scanner input = new Scanner(System.in);
								String player1 = "PLAYER 1";
								String player2 = "PLAYER 2";
								char[][] player1LocationBoard = {{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'}};
								char[][] player2LocationBoard = {{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'}};
								char[][] player1TargetHistory = {{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'}};
								char[][] player2TargetHistory = {{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'},
																																									{'-', '-', '-', '-', '-'}};


								System.out.println("Welcome to Battleship!");
								System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES:");
								ChooseShipPosition(player1LocationBoard);


								System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES:");
								ChooseShipPosition(player2LocationBoard);
								while (hitCounter1 < 5 && hitCounter2 < 5) {
																hitCounter1 = takeTurn(player1, player2, player1LocationBoard, player2LocationBoard, player1TargetHistory, hitCounter1);
																if (hitCounter1 < 5 && hitCounter2 < 5) {
																								hitCounter2 = takeTurn(player2, player1, player2LocationBoard, player1LocationBoard, player2TargetHistory, hitCounter2);
																}
								}
								if (hitCounter1 == 5) {
																System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT\'S SHIPS!");
																printBattleShip(player1LocationBoard);
																System.out.println();
																printBattleShip(player2LocationBoard);
								}
								else if (hitCounter2 == 5) {
																System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT\'S SHIPS!");
																printBattleShip(player2LocationBoard);
																System.out.println();
																printBattleShip(player1LocationBoard);
								}



}





// Use this method to print game boards to the console.
private static void printBattleShip(char[][] player) {
								System.out.print("  ");
								for (int row = -1; row < 5; row++) {
																if (row > -1) {
																								System.out.print(row + " ");
																}
																for (int column = 0; column < 5; column++) {
																								if (row == -1) {
																																System.out.print(column + " ");
																								} else {
																																System.out.print(player[row][column] + " ");
																								}
																}
																System.out.println("");
								}
}

public static char[][] ChooseShipPosition(char[][] playerLocationBoard){
								Scanner input = new Scanner(System.in);
								for (int counter = 1; counter < 6; counter++ ) {
																System.out.println("Enter ship " + (counter) + " location:");
																int input1 = input.nextInt();
																int input2 = input.nextInt();
																boolean filledSpot = true;

																while (input1 > 4 || input1 < 0 || input2 > 4 || input2 < 0 || filledSpot)  {
																								if (input1 > 4 || input1 < 0 || input2 > 4 || input2 < 0)  {
																																System.out.println("Invalid coordinates. Choose different coordinates.");
																																System.out.println("Enter ship " + (counter) + " location:");
																																input1 = input.nextInt();
																																input2 = input.nextInt();
																																continue;
																								}

																								if (playerLocationBoard[input1][input2] != '@') {
																																playerLocationBoard[input1][input2] = '@';
																																filledSpot = false;

																								}
																								else {
																																System.out.println("You already have a ship there. Choose different coordinates.");
																																System.out.println("Enter ship " + (counter) + " location:");
																																input1 = input.nextInt();
																																input2 = input.nextInt();

																								}
																}
								}
								printBattleShip(playerLocationBoard);

								for (int x = 0; x < 100; x++) {
																System.out.println();
								}

								return playerLocationBoard;
}


public static int takeTurn(String attacker, String defender, char[][] attackerLocationBoard, char[][] defenderLocationBoard, char[][] attackerTargetHistory, int hitCounter) {
								Scanner input = new Scanner(System.in);
								System.out.println(attacker + ", enter hit row/column:");
								int turn = 0;
								while (turn < 1) {
																int input1 = input.nextInt();
																int input2 = input.nextInt();
																if (input1 > 4 || input1 < 0 || input2 > 4 || input2 < 0) {
																								System.out.println("Invalid coordinates. Choose different coordinates.");
																								continue;
																}

																else if ((defenderLocationBoard[input1][input2] == 'X') || (defenderLocationBoard[input1][input2] == 'O')) {
																								System.out.println("You already fired on this spot. Choose different coordinates.");
																								continue;

																}
																else if (defenderLocationBoard[input1][input2] == '@') {
																								defenderLocationBoard[input1][input2] = 'X';
																								attackerTargetHistory[input1][input2] = 'X';
																								System.out.println(attacker + ", HIT PLAYER " + defender + "\'s SHIP!");
																								printBattleShip(attackerTargetHistory);
																								turn++;
																								hitCounter++;

																}
																else {
																								defenderLocationBoard[input1][input2] = 'O';
																								attackerTargetHistory[input1][input2] = 'O';
																								System.out.println(attacker + " MISSED!");
																								printBattleShip(attackerTargetHistory);
																								turn++;
																}



								}
								return hitCounter;

}
}
