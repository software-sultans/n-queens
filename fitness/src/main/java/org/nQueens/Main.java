package org.nQueens;

import java.util.*;

public class Main {

    // Parametrii
    public static int board_Size=14;
    public static final int POPULATION_SIZE = 5000;

    public static final double MUTATION_RATE_INITIAL=0.01;

    public static final int MAX_GENERATIONS = 10000;



    public static double MUTATION_RATE = MUTATION_RATE_INITIAL;
    public static float counter_since_last_best=0;
    public static int[] last_best=new int[board_Size];




    public static void main(String[] args) {
        // Se genereaza prma populatie
        List<int[]> population = new ArrayList<>();
        int[] bestBoardStateOverall=new int[board_Size];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(ClasaUtilitara.generateBoard());
        }

        // Main Genetic Algorithm loop
        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            // Calculeaza fitness-ul fiecarei individ din populatie

            // bestBoardState se ia drept cel mai bun individ din fiecare generatie
            int[] bestBoardState=population.get(0);
            for(int i=0;i<population.size();i++)
            {
                if(ClasaUtilitara.calculateFitness(population.get(i))<ClasaUtilitara.calculateFitness(bestBoardState))
                {
                    bestBoardState=population.get(i);
                    if (ClasaUtilitara.calculateFitness(bestBoardState)<ClasaUtilitara.calculateFitness(bestBoardStateOverall))
                    {
                        bestBoardStateOverall=bestBoardState.clone();
                    }

                }
            }
            if(ClasaUtilitara.hasDuplicates(bestBoardState)==true) {
                bestBoardState = ClasaUtilitara.generateBoard();
            }
            else
            {
                if (Arrays.equals(bestBoardState, last_best)) {
                    counter_since_last_best++;
                    if(MUTATION_RATE<1)
                        MUTATION_RATE = MUTATION_RATE * (counter_since_last_best/10);
                } else {
                    counter_since_last_best = 0;
                    last_best = bestBoardState.clone();
                    MUTATION_RATE = MUTATION_RATE_INITIAL;
                }
            }



            // Verifica daca s-a gasit o solutie
            if (ClasaUtilitara.calculateFitness(bestBoardState) == 0) {
                System.out.println("Solutia a fost gasita in generatia " + generation + ": " +
                        Arrays.toString(bestBoardState) + ", Conflicte: " + ClasaUtilitara.calculateFitness(bestBoardState));
                break;
            }

            // Creeaza urmatoarea generati
            List<int[]> newPopulation = new ArrayList<>();

            // Pastreaza cea mai buna solutie din ultima generatie, pe baza conceptului de elitism
            newPopulation.add(bestBoardState);

            // Perform selection, crossover, and mutation
            while (newPopulation.size() < POPULATION_SIZE) {

                int[] parent1 = Selection.tournamentSelection(population);
                int[] parent2 = Selection.tournamentSelection(population);
                int[] child = Crossover.crossover(parent1, parent2);
                if (new Random().nextDouble() < MUTATION_RATE) {
                    child = Mutation.mutate(child);
                }
                if(ClasaUtilitara.hasDuplicates(child) == true) {
                    newPopulation.add(ClasaUtilitara.generateBoard());

                }
                else
                    newPopulation.add(child);
            }

            // Se schimba populatia
            population = newPopulation;


//             Print-eaza cea mai buna solutie gasita dupa fiecare generatie
            System.out.println("Cea mai buna solutie: " +Arrays.toString(bestBoardState)+ ", Fitness: " + ClasaUtilitara.calculateFitness(bestBoardState)+" , Generation: "+generation+" Mutaion rate : "+MUTATION_RATE);
        }
        System.out.println("Cea mai buna solutie din simulare: " +Arrays.toString(bestBoardStateOverall)+ ", Fitness: " + ClasaUtilitara.calculateFitness(bestBoardStateOverall));

    }
}