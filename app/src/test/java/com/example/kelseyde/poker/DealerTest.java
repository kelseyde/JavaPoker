package com.example.kelseyde.poker;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class DealerTest {

    Dealer dealer;
    Deck deck;
    Player player1;
    Player player2;
    Player player3;
    ArrayList<Player> players;

    @Before
    public void before() {
        dealer = new Dealer();
        deck = new Deck();
        dealer.setDeck(deck);
        player1 = new Player("Bill");
        player2 = new Player("Meg");
        player3 = new Player("Sam");
        players = new ArrayList<Player>();
        Collections.addAll(players, player1, player2, player3);
    }

    @Test
    public void testGetDeck() {
        assertEquals(deck, dealer.getDeck());
    }

    @Test
    public void testSetDeck() {
        Deck deck2 = new Deck();
        dealer.setDeck(deck2);
        assertEquals(deck2, dealer.getDeck());
    }

    @Test
    public void testDealOneCard() {
        deck.newDeck();
        dealer.deal(1, players);
        assertEquals(1, player1.getHand().size());
        assertEquals(1, player2.getHand().size());
        assertEquals(1, player3.getHand().size());
    }

    @Test
    public void testDealTenCards() {
        deck.newDeck();
        dealer.deal(10, players);
        assertEquals(10, player1.getHand().size());
        assertEquals(10, player2.getHand().size());
        assertEquals(10, player3.getHand().size());
    }

}