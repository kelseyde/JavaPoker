package com.example.kelseyde.poker.models;


public class Card {

    private SuitType suit;
    private RankType rank;
    private CardDisplayer displayer;

    public Card(SuitType suit, RankType rank) {
        this.suit = suit;
        this.rank = rank;
        this.displayer = new CardDisplayer();
    }

    public SuitType getSuit() {
        return suit;
    }

    public void setSuit(SuitType suit) {
        this.suit = suit;
    }

    public RankType getRank() {
        return rank;
    }

    public void setRank(RankType rank) {
        this.rank = rank;
    }

    public String icon() {
        return displayer.icon(this);
    }
}
