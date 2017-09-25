package com.example.kelseyde.poker.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;


public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        newDeck();
        shuffle();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void add(Card card) {
        deck.add(card);
    }

    public Card get(int index) {
        return deck.get(index);
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

    public void shuffle() {
        Collections.shuffle(deck);
    }



}
