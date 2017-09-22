package com.example.kelseyde.poker;


public class Card {

    private SuitType suit;
    private RankType rank;

    public Card(SuitType suit, RankType rank) {
        this.suit = suit;
        this.rank = rank;
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

}
