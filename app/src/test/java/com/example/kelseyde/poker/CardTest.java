package com.example.kelseyde.poker;

import com.example.kelseyde.poker.models.Card;
import com.example.kelseyde.poker.models.CardDisplayer;
import com.example.kelseyde.poker.models.RankType;
import com.example.kelseyde.poker.models.SuitType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.kelseyde.poker.models.SuitType.*;
import static org.junit.Assert.*;

public class CardTest {

    Card card;

    @Before
    public void before() {
        card = new Card(SPADES, RankType.ACE);
    }

    @Test
    public void testCanGetSuit() {
        assertEquals(SPADES, card.getSuit());
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

    @Test
    public void testIcon() {
        System.out.println(card.icon());
        assertEquals("\033[0mA\u2660\033[0m", card.icon());
    }

    @Test
    public void testDisplayHand() {
        Card card1 = new Card(SuitType.CLUBS, RankType.EIGHT);
        Card card2 = new Card(SuitType.HEARTS, RankType.EIGHT);
        ArrayList<Card> hand = new ArrayList<>();
        Collections.addAll(hand, card1, card2);
        assertEquals("[ \033[0m8\u2663\033[0m \033[0;31m8\u2665\033[0m ]", CardDisplayer.displayHand(hand));

    }

}