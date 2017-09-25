package com.example.kelseyde.poker;

import com.example.kelseyde.poker.models.ConsoleLogger;
import com.example.kelseyde.poker.models.Dealer;
import com.example.kelseyde.poker.models.HandEvaluating;
import com.example.kelseyde.poker.models.Logger;
import com.example.kelseyde.poker.models.Player;
import com.example.kelseyde.poker.models.PokerGame;
import com.example.kelseyde.poker.models.PokerHandEvaluator;

import org.junit.Before;
import org.junit.Test;

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



}