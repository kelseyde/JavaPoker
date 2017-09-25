package com.example.kelseyde.poker.models;

import java.util.ArrayList;


public class PokerGame {

    private ArrayList<Player> players;
    private Dealing dealer;
    private Player currentPlayer;
    private HandEvaluating evaluator;
    private int pot;

    public PokerGame() {
        this.players = new ArrayList<Player>();
        this.dealer = new Dealer();
        this.currentPlayer = null;
        this.evaluator = new PokerHandEvaluator();
        this.pot = 0;
    }

    //getters and setters

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Dealing getDealer() {
        return dealer;
    }

    public void setDealer(Dealing dealer) {
        this.dealer = dealer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public HandEvaluating getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(HandEvaluating evaluator) {
        this.evaluator = evaluator;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    //methods



}
