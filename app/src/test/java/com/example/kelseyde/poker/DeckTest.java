package com.example.kelseyde.poker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck;
    Card card1;
    Card card2;
    Card card3;

    @Before
    public void before() {
        deck = new Deck();
        card1 = new Card(SuitType.SPADES, RankType.QUEEN);
        card2 = new Card(SuitType.HEARTS, RankType.EIGHT);
        card3 = new Card(SuitType.CLUBS, RankType.FIVE);

    }

    @Test
    public void testDeckStartsEmpty() {
        assertEquals(0, deck.size());
    }

    @Test
    public void testCanAddCard() {
        deck.add(card1);
        assertEquals(card1, deck.getDeck().get(0));
    }

    @Test
    public void testCanRemoveCard() {
        deck.add(card1);
        assertEquals(1, deck.size());
        deck.remove(0);
        assertEquals(0, deck.size());
    }

    @Test
    public void testClearDeck() {
        deck.add(card1);
        assertEquals(1, deck.size());
        deck.clear();
        assertEquals(0, deck.size());
    }

    @Test
    public void testNewDeck() {
        deck.clear();
        deck.newDeck();
        assertEquals(52, deck.size());
    }

}