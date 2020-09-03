import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();		//static allows global accessibility for the class
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		gameBoard gameBoard = new gameBoard(playerPositions, cpuPositions);

	}
}