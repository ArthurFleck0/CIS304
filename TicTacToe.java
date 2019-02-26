//Amad Asim
//Homework #15
//Completed on 6/3/2015

import java.util.Scanner;

public class TicTacToe
{
	public static char PLAYER_ONE = 'X';
	public static char PLAYER_TWO = 'O';
	public static char BLANK = '.';
	public static char[][] board = new char[3][3];
	public static int moves = 0;
	public static char current;
	
	public static void main(String[] args)
	{
		int x;
		current = PLAYER_ONE;
		
		Scanner kb = new Scanner(System.in);	
		System.out.print("Please choose the number of players (0, 1, or 2): ");
		x =  kb.nextInt();
		
		clearBoard();
		System.out.println("Board is clear.");
				
		if (x == 2)
		{
			while (win(current) == false && moves <= 9)
			{
				printBoard();
				
				int row;
				int col;
				char temp = current;
				
				System.out.println(current + "'s turn.");

				System.out.print("Select a row (0, 1, 2): ");
				row = kb.nextInt();
				System.out.print("Select a col (0, 1, 2): ");
				col = kb.nextInt();
				
				while (isLegalMove(row, col) == false)
				{
					System.out.println("Invaild move.");
					System.out.print("Row (0, 1, 2): ");
					row = kb.nextInt();
					System.out.print("Col (0, 1, 2): ");
					col = kb.nextInt();
				}
				makeMove(row, col, current);
				moves++;
				if (win(current) == true)
				{
					victory(current);
				}
				else if (staleMate())
				{
					tieMessage();
				}
				else
				{
					System.out.println(moves);
					current = otherPlayer(temp);
				}
			}
		}
		if(x==0){
			
		}
		if(x==1){
			while (win(current) == false && moves <= 9){
			printBoard();
			
			int row;
			int col;
			char temp = current;
			
			System.out.println(current + "'s turn.");

			System.out.print("Select a row (0, 1, 2): ");
			row = kb.nextInt();
			System.out.print("Select a col (0, 1, 2): ");
			col = kb.nextInt();
			
			while (isLegalMove(row, col) == false)
			{
				System.out.println("Invaild move.");
				System.out.print("Row (0, 1, 2): ");
				row = kb.nextInt();
				System.out.print("Col (0, 1, 2): ");
				col = kb.nextInt();
			}
			makeMove(row, col, current);
			moves++;
			if (win(current) == true)
			{
				victory(current);
			}
			else if (staleMate())
			{
				tieMessage();
			}
			else
			{
				System.out.println(moves);
			}
			int[] moves=chooseMove(current);
			board[moves[0]][moves[1]]=otherPlayer(temp);
			moves++;
		 }
		}
		if(x==0){
			while (win(current) == false && moves <= 9){
				printBoard();
				
				int row;
				int col;
				char temp = current;
				int[] moves=chooseMove(current);
				board[moves[0]][moves[1]]=otherPlayer(temp);
				if (win(current) == true)
				{
					victory(current);
				}
				else if (staleMate())
				{
					tieMessage();
				}
				else
				{
					System.out.println(moves);
				}
				moves++;
				current=otherPlayer(temp);
			}
		}
	}
	
	public static void tieMessage()
	{
		System.out.println("That's a tie.");
	}
	
	public static void victory(char player)
	{
		String a = "";
		if (player == PLAYER_ONE)
		{
			a = "Player One";
		}
		else if (player == PLAYER_TWO)
		{
			a = "Player Two";
		}
		System.out.println("Congratulations!");
		System.out.println("You've won, " + a + "!");
	}
	
	public static void clearBoard()
	{
		for (int row = 0; row < 3; row++)
		{
			for (int col = 0; col < 3; col++)
			{
				board[row][col] = BLANK;
			}
		}
	}
	
	public static char otherPlayer(char player)
	{
		if (player == PLAYER_ONE)
		{
			return PLAYER_TWO;
		}
		else
		{
			return PLAYER_ONE;
		}
	}
	
	public static void makeMove(int row, int col, char player)
	{
		board[row][col] = player;
		win(player);
	}
	
	public static boolean isLegalMove(int row, int col)
	{
		if (board[row][col] == BLANK)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean staleMate()
	{	if(moves==9&&!win(PLAYER_TWO)&&!win(PLAYER_ONE))
				return true;
		else
		return false;
	}
	
	public static boolean win(char player)
	{
		char p = player;
		if (board[0][0] == p&&board[0][1]==p&&board[0][2]==p)
			return true;
		else if (board[1][0] == p&&board[1][1]==p&&board[1][2]==p)
			return true;
		else if (board[2][0]==p&&board[2][1]==p&&board[2][2]==p)
			return true;	
		else if(board[0][0]==p&&board[1][1]==p&&board[2][2]==p)
			return true;
		else if(board[0][2]==p&&board[1][1]==p&&board[2][0]==p)
			return true;
		else
		return false;
	}
	public static void printBoard()
	{	
		System.out.println("   0   1   2");
		for (int row = 0; row < 3; row++)
		{
			System.out.print(row + " ");
			for (int col = 0; col < 3; col++)
			{
				System.out.print("[" + board[row][col] + "] ");
			}
			System.out.println();
		}
	}
	
	public static int value(char player)
	{
		int best = -1;
		char other = otherPlayer(player);
		if (win(player))
		{
			return 1;
		}
		else if (win(other))
		{
			return -1;
		}
		else if (staleMate())
		{
			return 0;
		}
		for (int row = 0; row < 3; row++)
		{
			for (int col = 0; col < 3; col++)
			{
				if (isLegalMove(row, col))
				{
					makeMove(row, col, player);
					int newValue = -value(other);
					board[row][col] = BLANK;
					if (newValue > best)
					{
						best = newValue;
					}
				}
			}
		}
		return best;
	}
	
	public static int[] chooseMove(char player)
	{
		char other = otherPlayer(player);
		int best = -1;
		int[] result =  new int[2];
		for (int row = 0; row <3; row++)
		{
			for (int col = 0; col < 3; col++)
			{
				if (isLegalMove(row, col))
				{
					makeMove(row, col, player);
					int current = -value(other);
					board[row][col] = BLANK;
					if (current > best)
					{
						result[0] = row;
						result[1] = col;
						best = current;
					}
				}
			}
		}
		return result;
	}
}
