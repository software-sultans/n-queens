package org.nQueens;

public class Fitness {
    public int calculateFitness(int[] board) {
        int n = board.length;
        int conflicts = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // orizontala
                if (board[i] == board[j]) {
                    conflicts++;
                }
                // diagonale
                if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }
}
