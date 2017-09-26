package com.example.kelseyde.poker;

import com.example.kelseyde.poker.models.Card;
import com.example.kelseyde.poker.models.ConsoleLogger;
import com.example.kelseyde.poker.models.Dealer;
import com.example.kelseyde.poker.models.HandEvaluating;
import com.example.kelseyde.poker.models.Logger;
import com.example.kelseyde.poker.models.Player;
import com.example.kelseyde.poker.models.PokerGame;
import com.example.kelseyde.poker.models.PokerHandEvaluator;
import com.example.kelseyde.poker.models.RankType;
import com.example.kelseyde.poker.models.SuitType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class PokerGameTest {

    private PokerGame game;
    private Dealer dealer;
    private HandEvaluating evaluator;
    private Logger logger;
    private Player player1;
    private Player player2;

    @Before
    public void before() {
        game = new PokerGame();
        player1 = new Player("Bill");
        player2 = new Player("Meg");
    }

    @Test
    public void testAddAndGetPlayers() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testGetCurrentPlayer() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testNextPlayer() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(player2, game.nextPlayer());
        game.nextPlayer();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testGetPlayers() {
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void testGetPot() {
        assertEquals(0, game.getPot());
    }

    @Test
    public void testSetPot() {
        game.setPot(100);
        assertEquals(100, game.getPot());
    }

    @Test
    public void addToPot() {
        game.addToPot(100);
        assertEquals(100, game.getPot());
    }

    @Test
    public void testGetDealer() {
        assertEquals("Dealer", game.getDealer().getClass().getSimpleName());
    }

    @Test
    public void testGetTable() {
        assertEquals(0, game.getTable().size());
    }

    @Test
    public void testGetPlayerHands() {
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        player1.setHand(hand1);
        player2.setHand(hand2);
        game.addPlayer(player1);
        game.addPlayer(player2);
        ArrayList<ArrayList<Card>> hands = game.getPlayerHands();
        assertTrue(hands.contains(hand1));
        assertTrue(hands.contains(hand2));
    }

    @Test
    public void testGetWinnerHighCard() {
        Card card1 = new Card(SuitType.CLUBS, RankType.EIGHT);
        Card card2 = new Card(SuitType.CLUBS, RankType.JACK);
        player1.getHand().add(card1);
        player2.getHand().add(card2);
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(player2, game.getWinner());
    }

    @Test
    public void testGetWinnerRoyalFlush() {
        Collections.addAll(player1.getHand(), new Card(SuitType.CLUBS, RankType.TEN), new Card(SuitType.CLUBS,
                RankType.JACK), new Card(SuitType.CLUBS, RankType.QUEEN), new Card(SuitType.CLUBS,
                RankType.KING), new Card(SuitType.CLUBS, RankType.ACE));
        Collections.addAll(player2.getHand(), new Card(SuitType.CLUBS, RankType.FIVE), new Card(SuitType.DIAMONDS,
                RankType.FIVE), new Card(SuitType.HEARTS, RankType.FOUR), new Card(SuitType.CLUBS,
                RankType.JACK), new Card(SuitType.SPADES, RankType.FIVE));
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(player1, game.getWinner());
    }

    @Test
    public void testClearHands() {
        Collections.addAll(player1.getHand(), new Card(SuitType.CLUBS, RankType.TEN), new Card(SuitType.CLUBS,
                RankType.JACK), new Card(SuitType.CLUBS, RankType.QUEEN), new Card(SuitType.CLUBS,
                RankType.KING), new Card(SuitType.CLUBS, RankType.ACE));
        Collections.addAll(player2.getHand(), new Card(SuitType.CLUBS, RankType.FIVE), new Card(SuitType.DIAMONDS,
                RankType.FIVE), new Card(SuitType.HEARTS, RankType.FOUR), new Card(SuitType.CLUBS,
                RankType.JACK), new Card(SuitType.SPADES, RankType.FIVE));
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.clearHands();
        assertEquals(0, player1.getHand().size());
        assertEquals(0, player2.getHand().size());
    }

    @Test
    public void testClearTable() {
        Card card1 = new Card(SuitType.CLUBS, RankType.EIGHT);
        game.getTable().add(card1);
        assertEquals(1, game.getTable().size());
        game.clearTable();
        assertEquals(0, game.getTable().size());
    }

}