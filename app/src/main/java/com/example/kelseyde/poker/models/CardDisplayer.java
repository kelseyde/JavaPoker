package com.example.kelseyde.poker.models;

import static com.example.kelseyde.poker.models.SuitType.*;

public class CardDisplayer {


    public String setColour(Card card) {
        SuitType suit = card.getSuit();
        String colour = null;
        switch (suit) {
            case HEARTS : colour = "\033[0m";
            case DIAMONDS : colour = "\033[0m";
            case SPADES : colour = "\033[0;31m";
            case CLUBS : colour = "\033[0;31m";
        }
        return colour;
    }

    public String setSuitIcon(Card card) {
        SuitType suit = card.getSuit();
        String suitIcon = null;
        switch (suit) {
            case CLUBS: suitIcon = "\u2663";
            case SPADES: suitIcon = "\u2660";
            case DIAMONDS: suitIcon = "\u2666";
            case HEARTS: suitIcon = "\u2665";
            }
        return suitIcon;
    }

    public String setRankIcon(Card card) {
        RankType cardRank = card.getRank();
        String rankIcon = null;
        switch (cardRank) {
            case TWO : rankIcon = "2";
            case THREE : rankIcon = "3";
            case FOUR : rankIcon = "4";
            case FIVE : rankIcon = "5";
            case SIX : rankIcon = "6";
            case SEVEN : rankIcon = "7";
            case EIGHT : rankIcon = "8";
            case NINE : rankIcon = "9";
            case TEN : rankIcon = "10";
            case JACK : rankIcon = "J";
            case QUEEN : rankIcon = "Q";
            case KING : rankIcon = "K";
            case ACE : rankIcon = "A";
        }
        return rankIcon;
    }



    public String icon(Card card) {
        String colour = setColour(card);
        String rankIcon = setRankIcon(card);
        String suitIcon = setSuitIcon(card);
        String reset = "\033[0;31m";
        String icon = (colour + rankIcon + suitIcon + reset);
        return icon;
    }



}
