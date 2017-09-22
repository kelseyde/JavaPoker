package com.example.kelseyde.poker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck;
    Card card;

    @Before
    public void before() {
        deck = new Deck();
        card = new Card(SuitType.SPADES, RankType.QUEEN);

    }

    @Test
    public void testDeckStartsEmpty() {
        assertEquals(0, deck.getDeck().size());
    }

    @Test
    public void testCanAddCard() {
        deck.add
    }

}