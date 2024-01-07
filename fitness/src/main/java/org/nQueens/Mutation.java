package org.nQueens;

import java.util.Random;

public class Mutation {
    public static int[] mutate(int[] boardState) {
        int pos1 = new Random().nextInt(8);
        int pos2 = new Random().nextInt(8);
        int temp=0;
        temp=boardState[pos1];
        boardState[pos1]=boardState[pos2];
        boardState[pos2]=temp;
        return boardState;
    }

}
