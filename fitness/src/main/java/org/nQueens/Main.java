package org.nQueens;

public class Main {
    public static void main(String[] args) {
        int[] board = {0, 1, 2, 3};
        Fitness fitnessCalculator = new Fitness();
        int fitness = fitnessCalculator.calculateFitness(board);
        System.out.println(fitness);
        // test example -> toate reginele pe diagonala , prima regina ataca alte 3, a doua regina urmatoarele 2, a treia regina pe ultima -> 6 atacuri
    }
}
