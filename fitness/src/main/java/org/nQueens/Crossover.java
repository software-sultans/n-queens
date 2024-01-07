package org.nQueens;

import java.util.Random;

public class Crossover {
    public static int[] crossover(int[] parent1, int[] parent2) {

        int i=0;
        int [] child=new int[org.nQueens.Main.board_Size];
        int crossoverPoint = new Random().nextInt(org.nQueens.Main.board_Size) ;
        for(i=0;i<crossoverPoint;i++)
            child[i]=parent1[i];
        for(i=crossoverPoint; i< org.nQueens.Main.board_Size; i++)
            child[i]=parent2[i];
        return child;
    }

}
