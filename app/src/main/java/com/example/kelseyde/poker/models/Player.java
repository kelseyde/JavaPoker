package com.example.kelseyde.poker.models;

import com.example.kelseyde.poker.models.Card;
import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private Integer chips;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.chips = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Integer getChips() {
        return chips;
    }

    public void setChips(Integer chips) {
        this.chips = chips;
    }

    public void bet(Integer bet) {
        chips -= bet;
    }

}
