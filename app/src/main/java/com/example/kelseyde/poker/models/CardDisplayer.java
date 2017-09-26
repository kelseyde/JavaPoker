package com.example.kelseyde.poker.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.kelseyde.poker.models.SuitType.*;

public class CardDisplayer {

    public String setColour(Card card) {
        SuitType suit = card.getSuit();
        String colour = null;
        switch (suit) {
            case HEARTS : colour = "\033[0;31m"; break;
            case DIAMONDS : colour = "\033[0;31m"; break;
            case SPADES : colour = "\033[0m"; break;
            case CLUBS : colour = "\033[0m"; break;
        }
        return colour;
    }

    public String setSuitIcon(Card card) {
        SuitType suit = card.getSuit();
        String suitIcon = null;
        switch (suit) {
            case CLUBS: suitIcon = "\u2663"; break;
            case SPADES: suitIcon = "\u2660"; break;
            case DIAMONDS: suitIcon = "\u2666"; break;
            case HEARTS: suitIcon = "\u2665"; break;
            }
        return suitIcon;
    }

    public String setRankIcon(Card card) {
        RankType cardRank = card.getRank();
        String rankIcon = null;
        switch (cardRank) {
            case TWO : rankIcon = "2"; break;
            case THREE : rankIcon = "3"; break;
            case FOUR : rankIcon = "4"; break;
            case FIVE : rankIcon = "5"; break;
            case SIX : rankIcon = "6"; break;
            case SEVEN : rankIcon = "7"; break;
            case EIGHT : rankIcon = "8"; break;
            case NINE : rankIcon = "9"; break;
            case TEN : rankIcon = "10"; break;
            case JACK : rankIcon = "J"; break;
            case QUEEN : rankIcon = "Q"; break;
            case KING : rankIcon = "K"; break;
            case ACE : rankIcon = "A"; break;
        }
        return rankIcon;
    }

    public String displayCard(Card card) {
        String colour = setColour(card);
        String rankIcon = setRankIcon(card);
        String suitIcon = setSuitIcon(card);
        String reset = "\033[0m";
        String icon = (colour + rankIcon + suitIcon + reset);
        return icon;
    }

    public String displayHand(ArrayList<Card> hand) {
        String display = "[ ";
        for (Card card : hand) {
            display = display + displayCard(card) + " ";
        }
        display = display + "]";
        return display;
    }


}
