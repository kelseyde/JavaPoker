package com.example.kelseyde.poker.models;

import java.util.ArrayList;


public interface Dealing {

    void deal(int cardsPerPlayer, ArrayList<Player> players);

    void dealTable(int numberOfCards, ArrayList<Card> table);

}
