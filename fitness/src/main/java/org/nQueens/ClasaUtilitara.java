package org.nQueens;

import java.util.Random;

public class ClasaUtilitara {

    public static int[] generateBoard() {
        int[] boardState=new int[org.nQueens.Main.board_Size];
        Random random = new Random();
        for (int i = 0; i < org.nQueens.Main.board_Size; i++) {
            boardState[i]=(random.nextInt(org.nQueens.Main.board_Size));


        }


        return boardState;
    }







    public static int calculateFitness(int[] board) {
        int n = org.nQueens.Main.board_Size;
        int conflicts = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Orizontal
                if (board[i]==board[j]) {
                    conflicts++;
                }
                // Diagonal
                if (Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    conflicts++;
                }
            }
        }
        return conflicts;
    }




}
