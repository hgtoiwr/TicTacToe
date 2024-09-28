package models;

import java.util.Scanner;

public class TicTacToe {

    char[][] board = new char[3][3];
    char currentPlayer = 'X';

    public TicTacToe() { // Создаю конструктор с полем
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void display() { // создаю метод, который будет отвечать за расставление символов
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            } System.out.println();
        }
    }

    /**
     *
     * @param row отвечает за линию на поле
     * @param col отвечает за столбец на поле
     * @return True, если ход действителен и совершен, false нельзя
     *
     */

    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board == null || board[row][col] != '-') {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    /**
     * Проверяет, выиграл ли текущий игрок.
     *
     * @return True, если игрок выиграл, false - нет
     */


    public boolean checkWin() {
        // Проверяем линии
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Проверяем столбцы
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer  && board[2][2] == currentPlayer) {
            return true;
        }
        // Проверяем диагонали
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer  && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    /*
     * Проверяет, ничья или нет.
     *
     * @return True, если ничья, false - нет
     */


    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Переключает игрока.
     */

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X')? 'O' : 'X';
    }

    /*
     * Запускает игру.
     */

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            display();
            System.out.println("Игрок: " +  currentPlayer + ", ходите:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (makeMove(row, col)) {
                if (checkWin()) {
                    display();
                    System.out.println("Игрок " + currentPlayer + " победил!");
                    gameOver = true;
                } else if (checkDraw()) {
                    display();
                    System.out.println("Ничья!");
                    gameOver = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Некорректный ход!");
            }
        }
        scanner.close();
    }
}
