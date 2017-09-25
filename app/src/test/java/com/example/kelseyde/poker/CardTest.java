package com.example.kelseyde.poker;

import com.example.kelseyde.poker.models.Card;
import com.example.kelseyde.poker.models.RankType;
import com.example.kelseyde.poker.models.SuitType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    Card card;

    @Before
    public void before() {
        card = new Card(SuitType.SPADES, RankType.ACE);
    }

    @Test
    public void testCanGetSuit() {
        assertEquals(SuitType.SPADES, card.getSuit());
    }

    @Test
    public void testCanSetSuit() {
        card.setSuit(SuitType.HEARTS);
        assertEquals(SuitType.HEARTS, card.getSuit());
    }

    @Test
    public void testCanGetRank() {
        assertEquals(RankType.ACE, card.getRank());
    }

    @Test
    public void testCanSetRank() {
        card.setRank(RankType.QUEEN);
        assertEquals(RankType.QUEEN, card.getRank());
    }

    @Test
    public void testCanGetRankValue() {
        assertEquals(13, card.getRank().getValue());
    }

}