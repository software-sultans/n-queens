package org.nQueens;

import java.util.*;

public class Main {

    // Parametrii
    public static int board_Size=8;
    public static final int POPULATION_SIZE = 100;
    public static final double MUTATION_RATE = 0.01;
    public static final int MAX_GENERATIONS = 1000;



    public static void main(String[] args) {
        // Se genereaza prma populatie
        List<int[]> population = new ArrayList<>();
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
                newPopulation.add(child);
            }

            // Se schimba populatia
            population = newPopulation;


//             Print-eaza cea mai buna solutie gasita dupa fiecare generatie
            System.out.println("Cea mai buna solutie: " + Arrays.toString(bestBoardState) + ", Fitness: " + ClasaUtilitara.calculateFitness(bestBoardState)+" , Generation: "+generation);
        }
    }
}