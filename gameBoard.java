import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class gameBoard {
	
	public gameBoard (ArrayList<Integer> playerPositions, ArrayList<Integer> cpuPositions) {

	char [][] gameBoard = {{' ', '|', ' ', '|', ' '},	// 2d arrays to set the Layout/Board
			{'-', '+', '-', '+', '-'},						// chars more secure/rigid than String
			{' ', '|', ' ', '|', ' '},
			{'-', '+', '-', '+', '-'},
			{' ', '|', ' ', '|', ' '}};
		
		Scanner scan = new Scanner(System.in);
		while(true) {		//means loop will operate forever
			System.out.print("Enter your placement (1-9): ");
			System.out.println();
			printGameBoard(gameBoard);

			
			int playerPos = scan.nextInt();		// scans inputed number
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {		//player tries placing it in the same place as before || player tries placing it in CPU's place
			System.out.println("Position taken! Pick again");
			playerPos = scan.nextInt();		//repeats the process
			}
		
			
		placePiece(gameBoard, playerPos, "player", playerPositions, cpuPositions);
		String result = checkWinner(playerPositions, cpuPositions);				//new String called result to help announce checkWinner() due to it's return type
		if(result.length() > 0) {					//if result length is can be true (1) (meaning all 3 positions for winning have matched) then display the result.
			System.out.println(result);
			break;									//breaks out from loops!
		}
		
		Random rand = new Random();		//generates random position for cpu
		int cpuPos = rand.nextInt(9)+1;
		while(cpuPositions.contains(cpuPos) || playerPositions.contains(cpuPos)) {		// CPU position tries placing it in the same place it did before || CPU tries placing it in player's position
			System.out.println("Position taken! Pick again robot, dont make me repeat");
			cpuPos = rand.nextInt(9)+1;		//repeats the process
		}
		placePiece(gameBoard, cpuPos, "cpu", playerPositions, cpuPositions);
		
		if(result.length() > 0) {	
			System.out.println(result);
			break;
			}
		}
		printGameBoard(gameBoard);
		}
	
	public void placePiece(char[][] gameBoard, int pos, String user, ArrayList<Integer> playerPositions,
			ArrayList<Integer> cpuPositions) {
		
		char symbol = ' ';		//variable must be initialised for switch statements to work
		
		if(user.equals("player")) {			//user variable differentiates player and cpu
			symbol = 'X';
			playerPositions.add(pos);		//adds the switch/case statement 
		}else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch (pos) {
		case 1:
			gameBoard[0][0] = symbol;		// [][] = index in which X is inputed
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		default:		//for good practise 
			break;
		}
	}
	
	public String checkWinner(ArrayList<Integer> playerPositions, ArrayList<Integer> cpuPositions) {		//String is used to form 'backend'/data methods
		
		List<Integer> topRow = Arrays.asList(1, 2, 3);	//stndrd List - .asList used for iteration/reads
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);

		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);

		List<Integer> cross1 = Arrays.asList(1, 5, 9);
		List<Integer> cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();	//DADDY List - joins all the winning conditions in one
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		
		for(List<?> l : winning) {		//for-each list which is equal to winning
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won!";			//This is why we declare the class as a String in main (because of it's return type which is String).
			} else if (cpuPositions.containsAll(l)) {
				return"You Lost :";	
			} if(playerPositions.size() + cpuPositions.size() == 9) {
				return "Nill nill";
			}
		}
		return "";
	}

	public static void printGameBoard(char[][] gameBoard) { // parameters needed when accessing a variable outside of the method

		for (char[] row : gameBoard) {		// foreach loop used to count each char{}array
			System.out.print(row);
			System.out.println();
		}
	}
}
