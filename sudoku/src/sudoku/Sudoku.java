package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Sudoku {
	 private final static  int width = 9;
	 private final static int height = 9;
	 boolean[] flag = new boolean[10];
	 
	 private int[][] sudokuAnswer = new int[width][height];
	 private int[][] sudokuPuzzle = new int[width][height];
	
	 Sudoku() {
		 initSudoku();
		 initarray();
		 generateAnswer();
		 generatePuzzle();
	 }
	 
	  
	 
	 void initSudoku() {
		 for(int i=0; i<height; i++) {
			 for(int j=0; j<width; j++) {
				 sudokuAnswer[i][j] = 0;
				 sudokuPuzzle[i][j] = 0;
			 }
		 }
	 }
	 
	 void generateAnswer() {
		 for(int i = 0; i<9; i++) {
			 for(int j = 0; j<9; j++) {
				 int n = generate(i, j);
				 while(n == 0) {
					 n = generate(i, j);
				 }
				 if(n ==10) {
					 j =-1;
					 continue;
				 }
				 sudokuAnswer[i][j] = n;				 
			 }
		 }
	 }
	 

	 int generate (int i, int j) {
		 
		 Random ran = new Random();
		 ran.setSeed(System.currentTimeMillis());
		 int n = ran.nextInt(9) + 1;	
		 
		
		flag[n] = check(i, j, n);  
		 
		 
		if(flag[n] == false) {
			int count = 0;
			for(int a = 1; a<10; a++) {
				if(flag[a] == false)
					count++;
			}
			if(count == 9) {
				initarray();
				count = 0;
				for(int a = 0; a<9; a++) {
					sudokuAnswer[i][a] = 0;
					
				}return 10;
			}
			return 0;
		}
		else {
			initarray();
			return n;
		}
	}
	 
	 void initarray() {
		 for(int a = 1; a<10; a++) {
			 flag[a] = true;
		 }
	 }
	 
	
	 boolean check(int i, int j, int n) {
		 int s = i/3;
		 int g = j/3;
		 int score = 0;
			
		 for(int a = 0; a<9; a++) {
			 if(sudokuAnswer[i][a] == n || sudokuAnswer[a][j] == n)
				 score++;
		 }
		 
		for(int a = s*3; a<(s*3+3); a++) {
			for(int b = g*3; b<(g*3+3); b++) {
				if(sudokuAnswer[a][b] == n)
					score++;
			}
		}
			if(score != 0) {
				return false;
			} else
				return true;
	 }
	 
	 
	 void generatePuzzle() {
		 Random ran = new Random();
		 ran.setSeed(System.currentTimeMillis());
		 int num = ran.nextInt(70) + 30;	
		 num = num/2;
		 
		 for(int i=0; i<9; i++) {
			 for(int j=0; j<9; j++) {
				 sudokuPuzzle[i][j] = sudokuAnswer[i][j];
			 }
		 }
		 
		 for(int i=0; i<=num; i++ ) {
			 int x = ran.nextInt(9);
			 int y = ran.nextInt(9);
			 
			 sudokuPuzzle[x][y] = 0;
			 sudokuPuzzle[8-x][8-y] = 0;
		 }
		 
		 
		 
		
	 }
	 
	 void printSudoku(int[][] sudoku) {
		 for(int i=0; i<height; i++) {
			 if(i % 3== 0)
				 System.out.print("+-----------------------+\n");
			 for(int j=0; j<width; j++) {
				 if(j % 3 == 0)
					 System.out.print("| ");
				 System.out.print(sudoku[i][j] + " ");
			 }
			 System.out.print("|\n");
		 }
		 System.out.print("+-----------------------+\n");
	 }
	 
	 
	 int[][] getAnswer() {
		 return sudokuAnswer;
	 }
	 
	 
	 int[][] getPuzzle(){
		 return sudokuPuzzle;
	 }
	 
	 
	 public static void main(String[] args) {
		 Sudoku sudoku = new Sudoku();
		 
		 System.out.println(" # Sudoku Puzzle #");
		 sudoku.printSudoku(sudoku.getPuzzle());
		 System.out.println("\n # Sudoku Answer #");
		 sudoku.printSudoku(sudoku.getAnswer());
	 }
}