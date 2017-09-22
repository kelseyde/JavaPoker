package com.example.kelseyde.poker;

import java.util.ArrayList;


public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void add(Card card) {
        deck.add(card);
    }

}
