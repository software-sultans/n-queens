package org.nQueens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.nQueens.ClasaUtilitara.calculateFitness;

public class Selection {
    public static int[] tournamentSelection(List<int[]> population) {
        int tournamentSize = 10;
        List<int[]> tournament = new ArrayList<>();
        // Amesteca la intamplare populatia pentru a selecta doar 10 elemente la intamplare din populatie
        Collections.shuffle(population);
        for (int i = 0; i < tournamentSize; i++) {
            tournament.add(population.get(i));
        }
        int[] bestInTournament=tournament.get(0);
        for(int i=0;i<tournament.size();i++)
        {
            if (calculateFitness(bestInTournament)>calculateFitness(tournament.get(i)))
            {
                bestInTournament=tournament.get(i);
            }
        }




        return bestInTournament;
    }
}
