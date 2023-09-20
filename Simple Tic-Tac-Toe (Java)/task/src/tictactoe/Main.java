package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] board = createEmptyBoard();
        printGrid(board);

        // Initialize the player (X starts)
        char currentPlayer = 'X';

        boolean gameIsOver = false;
        int moves = 0;

        while (!gameIsOver) {
            boolean validMove = false;
            while (!validMove) {
                System.out.print("Player " + currentPlayer + ", enter the coordinates (row and column): ");
                int row, col;
                try {
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    scanner.nextLine(); // Consume the invalid input
                    continue;
                }

                if (isValidCoordinates(row, col)) {
                    if (isCellEmpty(board, row, col)) {
                        board[row - 1][col - 1] = currentPlayer;
                        validMove = true;
                        moves++;
                        printGrid(board);

                        if (checkWin(board, currentPlayer) || checkDraw(moves)) {
                            gameIsOver = true;
                        }

                        // Switch to the other player
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    } else {
                        System.out.println("This cell is occupied! Choose another one.");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }
        }

        // Check and print the final result
        if (checkWin(board, 'X')) {
            System.out.println("X wins");
        } else if (checkWin(board, 'O')) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }

    public static char[][] createEmptyBoard() {
        char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        return board;
    }

    public static void printGrid(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean isValidCoordinates(int row, int col) {
        return row >= 1 && row <= 3 && col >= 1 && col <= 3;
    }

    public static boolean isCellEmpty(char[][] board, int row, int col) {
        return board[row - 1][col - 1] == ' ';
    }

    public static boolean checkWin(char[][] board, char currentPlayer) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Rows
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Columns
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Diagonal from top-left to bottom-right
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Diagonal from top-right to bottom-left
        }
        return false;
    }

    public static boolean checkDraw(int moves) {
        return moves == 9;
    }
}
