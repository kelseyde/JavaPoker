package com.example.kelseyde.poker.models;

import java.util.ArrayList;


public class PokerGame {

    private ArrayList<Player> players;
    private Dealing dealer;
    private Player currentPlayer;
    private HandEvaluating evaluator;
    private Logger logger;
    private int pot;

    public PokerGame() {
        this.players = new ArrayList<Player>();
        this.dealer = new Dealer();
        this.currentPlayer = null;
        this.evaluator = new PokerHandEvaluator();
        this.logger = new ConsoleLogger();
        this.pot = 0;
    }

    //methods


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        setCurrentPlayer(players.get(0));
    }

    public Player nextPlayer() {
        int numberOfPlayers = this.players.size();
        if (players.indexOf(this.currentPlayer) == (numberOfPlayers - 1)) {
            this.currentPlayer = players.get(0);
        } else {
            int newPlayerIndex = (players.indexOf(this.currentPlayer) + 1);
            this.currentPlayer = players.get(newPlayerIndex);
        }
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }
}
