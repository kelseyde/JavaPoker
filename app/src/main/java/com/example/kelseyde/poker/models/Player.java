package com.example.kelseyde.poker.models;


import com.example.kelseyde.poker.models.Card;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;
    private Integer chips;
    private Integer stake;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.chips = 100;
        this.stake = 0;
    }

    //getters and setters

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

    public Integer getStake() {
        return stake;
    }

    public void setStake(Integer stake) {
        this.stake = stake;
    }

    //methods

    public void bet(Integer bet) {
        stake += bet;
        chips -= bet;
    }


}
