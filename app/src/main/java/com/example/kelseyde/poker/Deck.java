package com.example.kelseyde.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;


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

    public void remove(int index) {
        deck.remove(index);
    }

    public int size() {
        return deck.size();
    }

    public void clear() {
        deck.clear();
    }

    public void newDeck() {
        Card card;

        ArrayList<Card> deck = new ArrayList<Card>();

        ArrayList<SuitType> suits =
                new ArrayList<SuitType>(EnumSet.allOf(SuitType.class));

        ArrayList<RankType> cardTypes =
                new ArrayList<RankType>(EnumSet.allOf(RankType.class));

        for(SuitType suit : suits) {
            for (RankType value : cardTypes) {
                card = new Card(suit, value);
                deck.add(card);
            }
        }

        this.deck = deck;
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }



}
