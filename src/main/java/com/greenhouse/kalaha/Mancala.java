package com.greenhouse.kalaha;

public class Mancala {

	private final int[] pits;
	private int currentPlayer;

	public Mancala() {
		pits = new int[14];

		for (int i = 0; i < pits.length; i++){
			if(i == 6 || i == 13)
				continue;
			pits[i] = 4;
		}
		currentPlayer = 0;

	}

	public String play(int input) {

		if(isGameOver())
			return "Game's over! Check status to see who won";

		boolean repeat = false;
		if(!isValidMove(input))
			return "The input passed isn't valid, please try again!!!";

		if(currentPlayer == 1)
			input++;

		int cubes = pits[input-1];
		pits[input-1] = 0;
		int lastPit = cubes + input;
		int turningIndex = lastPit % pits.length;

		for(int i = 0; i < cubes; i++){
			if(currentPlayer == 0 && input == pits.length - 1)
				continue;
			else if(currentPlayer == 1 && input == (pits.length / 2 - 1))
				continue;

			if(currentPlayer == 0)
				pits[(input + i) % 13]++;
			else pits[(input + i) % 14]++;

		}

		if(currentPlayer == 0 && turningIndex == 7)
			repeat = true;
		else if(currentPlayer == 1 && turningIndex == 0)
			repeat = true;

		if((turningIndex != 0 || turningIndex != pits.length / 2) && pits[turningIndex - 1] == 1){
			int valuesTillOne = 12 - (turningIndex - 1);
			if(currentPlayer == 0)
				pits[6] += pits[valuesTillOne] + 1;
			else pits[13] += pits[valuesTillOne] + 1;

			pits[valuesTillOne] = 0;
		}

		boolean isGameDone = isGameOver();

		if(!isGameDone && !repeat) {
			currentPlayer = currentPlayer == 0 ? 1 : 0;
			return "Your turn now: Player " + (currentPlayer + 1);
		}
		else if (!isGameDone)
			return "Please go again: Player " + (currentPlayer + 1);

		else return "Game's over. You Won, Player " + (getWinner() + 1);
	}

	public int getWinner() {
		if(pits[6] > pits[13])
			return 0;
		else if (pits[6] < pits[13]) {
			return 1;
		}
		else return -2;
	}

	public boolean isGameOver(){
		if(currentPlayer == 0) {
			for (int i = 0; i < 6; i++)
				if (pits[i] != 0)
					return false;
		}else {
			for (int i = 7; i < 13; i++)
				if (pits[i] != 0)
					return false;
		}
		return true;
	}

	public boolean isValidMove(int pitNumber) {
		boolean valid;

		if(currentPlayer == 0 && pitNumber > 0 && pitNumber < 7 && pits[pitNumber - 1] != 0)
			valid = true;
		else
			valid = currentPlayer == 1 && pitNumber > 6 && pitNumber < 13 && pits[pitNumber] != 0;

		if(!valid)
			System.out.println("Input passed isn't valid, please try again");

		return valid;
	}

	public int[] getPits() {
		return pits;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public String toString() {

		String player1 = "";
		int player1Store = 0;
		String player2 = "";
		int player2Store = 0;
		for(int i = 0; i < getPits().length; i++){
			if(i < 6)
				player1 += pits[i] + " ";
			else if(i == 6)
				player1Store = pits[i];
			else if (i < 13)
				player2 += pits[i] + " ";
			else player2Store = pits[i];
		}

		return "Mancala Stats{ " +
				"\nPlayer 1 Pits Cubes Count: " + player1 +
				"\n\tPlayer 1 Store: " + player1Store +
				"\n\nPlayer 2 Pits Cubes Count: " + player2 +
				"\n\tPlayer 2 Store: " + player2Store +
				"\n\n Current Player: " + (getCurrentPlayer() + 1) + " }";
	}
}