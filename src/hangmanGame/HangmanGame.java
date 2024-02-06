package hangmanGame;

import java.util.Scanner;


public class HangmanGame {
	String theWord;
	int thePoints;
	String currentWord;
	StringBuilder mutableCurrentWord;
	boolean gameOver = false;
	Scanner scanner = new Scanner(System.in);
	
	public HangmanGame(String theWord, int thePoints) {
		this.theWord = theWord.toUpperCase();
		this.thePoints = thePoints;
		this.currentWord = "_ ".repeat(theWord.length());// _ _ _ _ _ _ ...
		this.mutableCurrentWord = new StringBuilder(currentWord);
		}
	public void theGame() {
		
		displayInfos();
		while((!gameOver)) {
			
			char theLetter = takeLetter();
			isLetterInWord(theLetter);
			if (thePoints < 0) {
				loseGame();
				break;
			}
			if(isWantGuess()) {
				if(isGuessCorrect()) {
					winGame();
				}
				else {
					loseGame();
				}
			}
			isAllLettersShown();
			
		}
	}
	private char takeLetter() {
		char theLetter = 'ð';
			System.out.println("Please enter a letter: ");
			if(!scanner.hasNextInt()) {
				String theEntered = scanner.next();
				if (theEntered.length() == 1) {
					theLetter = theEntered.toUpperCase().charAt(0);
				}
				else {
					System.out.println("You should enter just one character!! ");
					takeLetter();
				}
			}
			else {
				scanner.next();
				System.out.println("You should enter a letter not a number!! ");
				takeLetter();
			}
			
		
		return theLetter;	
	}
	private boolean isWantGuess() {
		
			System.out.println("If you want guess the word please enter 'Y'\n"
					+ "If you dont want guess enter another key");
			String theEntered = scanner.next();	
			if(theEntered.toUpperCase().equals("Y")) {
				
				return true;
			}
			else {
				return false;
			}
		}

	private boolean isGuessCorrect() {
		
			System.out.println("Enter your guess: ");
			String theGuess = scanner.next();
			if(theGuess.toUpperCase().equals(theWord)) {
				return true;			
			}
			else {
				return false;
			}
		}
		
	
	private boolean isLetterInWord(char theLetter) {
		if (theWord.contains(String.valueOf(theLetter))) {
			decryptTheLetters(theLetter);
			displayCurrentWord();
			return true;
		}
		else {
            System.out.println("The letter is not in the word :((");
            decrementThePoints();
            displayCurrentWord();
            
			return false;
		}
	}
	private void decrementThePoints() {
		thePoints--;
		if (thePoints >=0) {
			System.out.println("Your remain points is: "+ thePoints);
		}
		
	}
	private void decryptTheLetters(char theLetter) {
		for(int i =0; i < theWord.length(); i++) {
			int indexOfCurrentWord = i * 2;
			char currentChar = theWord.charAt(i);
			if (theLetter == currentChar){
				mutableCurrentWord.setCharAt(indexOfCurrentWord, currentChar);
			displayCurrentWord();
				
			}
		}
	}
	private void displayCurrentWord() {
		if (thePoints >=0) {
			System.out.println("Now your word is: " + mutableCurrentWord);
		}
			
	}
	private void displayRemainPoints() {
		System.out.println("Your remain points: " + thePoints);
	}
	private void displayInfos() {
		displayCurrentWord();
		displayRemainPoints();
	}
	private boolean isAllLettersShown() {
		
		if (mutableCurrentWord.toString().contains("_")) {
			
			return false;
		}
		else {
			System.out.println("All characters shown!!");
			winGame();
			return true;
		}
		
	}
	private void loseGame() {
		System.out.println("Ohh...You lose the game see you at another game...");
		gameOver = true;
	}
	private void winGame() {
		System.out.println("Amazing!!! You win the game see you at another game..");
		gameOver = true;
	}
	
}
