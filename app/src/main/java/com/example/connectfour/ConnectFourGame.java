package com.example.connectfour;

public class ConnectFourGame {
    public static final int ROW = 7;
    public static final int COL = 6;
    public static final int EMPTY = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;
    public static final int DISCS = 42;
    private int[][] board;
    private int player;

    public ConnectFourGame() {
        board = new int[ROW][COL];
        newGame();
    }

    public void newGame() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = EMPTY;
            }
        }
        player = BLUE;
    }
    public int getDisc(int row, int col) {
        return board[row][col];
    }
    public boolean isGameOver() {
        return isWin() || isFull();
    }
    private boolean isFull() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    private boolean checkHorizontal() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j <= COL - 4; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i][j + 1] &&
                        board[i][j] == board[i][j + 2] &&
                        board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j] &&
                        board[i][j] == board[i + 2][j] &&
                        board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        // Top-left to bottom-right
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 0; j <= COL - 4; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j + 1] &&
                        board[i][j] == board[i + 2][j + 2] &&
                        board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= ROW - 4; i++) {
            for (int j = 3; j < COL; j++) {
                if (board[i][j] != EMPTY &&
                        board[i][j] == board[i + 1][j - 1] &&
                        board[i][j] == board[i + 2][j - 2] &&
                        board[i][j] == board[i + 3][j - 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void setState(String gameState) {
        int index = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = Character.getNumericValue(gameState.charAt(index++));
            }
        }
    }

    public void selectDisc(int row, int col) {
        for (int i = ROW - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = player;
                player = (player == BLUE) ? RED : BLUE;
                break;
            }
        }
    }
    public int getPlayer() {
        return player;
    }

    public String getState() {
        StringBuilder state = new StringBuilder();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                state.append(board[i][j]);
            }
        }
        return state.toString();
    }
}


