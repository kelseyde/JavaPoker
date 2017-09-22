package com.example.kelseyde.poker;


public class Dealer {

    Deck deck;

    public Dealer() {
        this.deck = new Deck();
    }

    //getters and setters

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

}
