package com.example.kelseyde.poker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class PokerHandEvaluatorTest {

    PokerHandEvaluator evaluator;
    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;
    Card card6;
    Card card7;
    Card card8;
    Card card9;
    Card card10;
    Card card11;
    ArrayList<Card> hand;

    @Before
    public void before() {
        evaluator = new PokerHandEvaluator();
        card1 = new Card(SuitType.CLUBS, RankType.EIGHT);
        card2 = new Card(SuitType.HEARTS, RankType.EIGHT);
        card3 = new Card(SuitType.SPADES, RankType.QUEEN);
        card4 = new Card(SuitType.DIAMONDS, RankType.QUEEN);
        card5 = new Card(SuitType.CLUBS, RankType.ACE);
        card6 = new Card(SuitType.HEARTS, RankType.FIVE);
        card7 = new Card(SuitType.SPADES, RankType.SIX);
        card8 = new Card(SuitType.CLUBS, RankType.TEN);
        card9 = new Card(SuitType.CLUBS, RankType.JACK);
        card10 = new Card(SuitType.CLUBS, RankType.QUEEN);
        card11 = new Card(SuitType.CLUBS, RankType.KING);
        hand = new ArrayList<>();
    }

    @Test
    public void testHighCard() {
        Collections.addAll(hand, card1, card2, card3, card4, card5);
        assertEquals(card5, evaluator.highCard(hand));
    }

    @Test
    public void testHasZeroTwoOfAKind() {
        Collections.addAll(hand, card1, card3, card5, card6, card7);
        ArrayList<ArrayList<Card>> result = evaluator.howManyOfKind(2, hand);
        assertEquals(0, result.size());
    }

    @Test
    public void testHasOneTwoOfAKind() {
        Collections.addAll(hand, card1, card2, card5, card6, card7);
        ArrayList<ArrayList<Card>> result = evaluator.howManyOfKind(2, hand);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).size());
    }

    @Test
    public void testHasTwoTwoOfAKinds() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        ArrayList<ArrayList<Card>> result = evaluator.howManyOfKind(2, hand);
        assertEquals(2, result.size());
        assertEquals(2, result.get(0).size());
        assertEquals(2, result.get(1).size());
    }

    @Test
    public void testHasZeroThreeOfAKind() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        assertEquals(0, evaluator.howManyOfKind(3, hand).size());
    }

    @Test
    public void testHasOneThreeOfAKind() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7, new Card(SuitType.CLUBS, RankType.EIGHT));
        assertEquals(1, evaluator.howManyOfKind(3, hand).size());
    }

    @Test
    public void testHasTwoThreeOfAKind() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7,
                new Card(SuitType.CLUBS, RankType.EIGHT),
                new Card(SuitType.HEARTS, RankType.QUEEN));
        assertEquals(2, evaluator.howManyOfKind(3, hand).size());
    }

    @Test
    public void testHasZeroFourOfAKind() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        assertEquals(0, evaluator.howManyOfKind(4, hand).size());
    }

    @Test
    public void testHasOneFourOfAKind() {
        Collections.addAll(hand, card1, card2, card3, card4, card5,
                new Card(SuitType.CLUBS, RankType.EIGHT),
                new Card(SuitType.SPADES, RankType.EIGHT));
        assertEquals(1, evaluator.howManyOfKind(4, hand).size());
    }

    @Test
    public void testNoFlush() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        assertEquals(0, evaluator.flush(hand).size());
    }

    @Test
    public void testFlush() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7,
                new Card(SuitType.CLUBS, RankType.EIGHT),
                new Card(SuitType.CLUBS, RankType.SIX),
                new Card(SuitType.CLUBS, RankType.ACE));
        assertEquals(5, evaluator.flush(hand).size());
    }

    @Test
    public void testNoRoyalFlush() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        assertEquals(0, evaluator.royalFlush(hand).size());
    }

    @Test
    public void testRoyalFlush() {
        Collections.addAll(hand, card8, card9, card10, card11, card5);
        assertEquals(5, evaluator.royalFlush(hand).size());
    }

    @Test
    public void testNoFullHouse() {
        Collections.addAll(hand, card1, card2, card3, card4, card5, card6, card7);
        assertEquals(0, evaluator.fullHouse(hand).size());
    }

    @Test
    public void testFullHouse() {
        Collections.addAll(hand, card1, card2, card3, card4, new Card(SuitType.CLUBS, RankType.QUEEN));
        assertEquals(5, evaluator.fullHouse(hand).size());
    }


}