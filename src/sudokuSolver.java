 

import java.util.Scanner;
/*
    Problem Statement :
    Write a program to solve a Sudoku puzzle by filling the empty cells.
    A sudoku solution must satisfy all the following rules:
    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    The '.' character indicates empty cells.

 */
class solve{
    static boolean isSafe(char [][]board,int row,int col,int number){

        //check for row have same number that filled in iteration.
        for(int i=0;i<board.length;i++){
            if(board[row][i]==(char) (number+'0')){
                return false;
            }
        }
        //check for row have same number that filled in iteration.
        for(int i=0;i<board.length;i++){
            if(board[i][col]==(char) (number+'0')){
                return false;
            }
        }

        //if grid had that number
        int startingRow = 3*(row/3);//calculate first row of grid.

        int startingCol = 3*(col/3);//calculate first col of grid.


        for(int i= startingRow; i< startingRow+3;i++){
            for(int j=startingCol;j<startingCol+3;j++){
                if(board[i][j]==(char) (number+'0')){
                    return false;
                }
            }
        }

        return true;
    }
    static boolean helper(char [][]board,int row,int col){


        if(row == board.length){
            return true;
        }
        //calculate value of row and column for next recursion call;
        int nRow = 0;
        int nCol = 0;
        if(col == board.length-1){
            nRow = row+1;
            nCol = 0;
        }else{
            nRow = row;
            nCol = col+1;
        }


        //if cell has already had some number.
        if(board[row][col]!='.'){
            if(helper(board,nRow,nCol)){
                return true;
            }
        }else{
            //if cell is empty then fill the cell by appropriate value
            for(int i=1;i<=9;i++){
                if(isSafe(board,row,col,i)){
                    board[row][col] = (char) (i+'0');

                    //if we right on further step return true
                    if(helper(board,nRow,nCol)){
                        return true;
                    }else{

                        //if we wrong on further step backtrack remove element and define cell as empty
                        board[row][col] = '.';
                    }
                }
            }

        }
        return false;
    }
    public static void printBoard(char [][] board){
        System.out.println("After filling cell sudoku board :");
        System.out.println(board);
    }
    public static void solveSudoku(char [][]board){
        helper(board,0,0);
        //print the board after filling all empty cells
        for (int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
public class sudokuSolver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Input :
        char [][] board = { {'5','3','.','.','7','.','.','.','.'},
                            {'6','.','.','1','9','5','.','.','.'},
                            {'.','9','8','.','.','.','.','6','.'},
                            {'8','.','.','.','6','.','.','.','3'},
                            {'4','.','.','8','.','3','.','.','1'},
                            {'7','.','.','.','2','.','.','.','6'},
                            {'.','6','.','.','.','.','2','8','.'},
                            {'.','.','.','4','1','9','.','.','5'},
                            {'.','.','.','.','8','.','.','7','9'}
                          };
        solve.solveSudoku(board);


    }
}