package com.example.kelseyde.poker;


import java.util.ArrayList;

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

    public Card getCard(int index) {
        return deck.get(index);
    }

    public Card drawCard(int index) {
        Card card = getCard(index);
        deck.remove(index);
        return card;
    }

    public void dealCard(Player player, Card card) {
        player.getHand().add(card);
    }

    public void deal(int cardsPerPlayer, ArrayList<Player> players) {
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (Player player : players) {
                Card card = drawCard(0);
                dealCard(player, card);
            }
        }
    }

}
