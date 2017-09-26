package com.example.kelseyde.poker.models;

import java.util.ArrayList;


public class PokerGame {

    private ArrayList<Player> players;
    private Dealer dealer;
    private Player currentPlayer;
    private ArrayList<Card> table;
    private PokerHandEvaluator evaluator;
    private int pot;

    public PokerGame() {
        this.players = new ArrayList<Player>();
        this.dealer = new Dealer();
        this.currentPlayer = null;
        this.table = new ArrayList<Card>();
        this.evaluator = new PokerHandEvaluator();
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

    public void addToPot(int bet) {
        this.pot = getPot() + bet;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public ArrayList<Card> getTable() {
        return table;
    }

    public void clearTable() {
        table.clear();
    }

    public void clearHands() {
        for (Player player : players) {
            player.getHand().clear();
        }
    }

    public ArrayList<ArrayList<Card>> getPlayerHands() {
        ArrayList<ArrayList<Card>> hands = new ArrayList<>();
        for (Player player : players) {
            hands.add(player.getHand());
        }
        return hands;
    }

    public ArrayList<Card> combineHandAndTable(Player player) {
        ArrayList<Card> handToEvaluate = new ArrayList<>(table);
        handToEvaluate.addAll(player.getHand());
        return handToEvaluate;
    }

    public Player getWinner() {
        //prepare ArrayList of player's hands combined with table cards
        ArrayList<ArrayList<Card>> handsWithTable = new ArrayList<>();
        for (Player player : getPlayers()) {
            ArrayList<Card> handWithTable = combineHandAndTable(player);
            handsWithTable.add(handWithTable);
        }
        //get winning hand, and find the player whose hand it is
        ArrayList<Card> winningHand = evaluator.getWinningHand(handsWithTable);
        Player winner = null;
        for (Player player : players) {
            if (combineHandAndTable(player).equals(winningHand)) {
                winner = player;
            }
        }
        return winner;
    }

}
