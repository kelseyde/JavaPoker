package com.example.kelseyde.poker;

import com.example.kelseyde.poker.models.Card;
import com.example.kelseyde.poker.models.Player;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    ArrayList<Card> hand;
    Card card1;
    Card card2;
    Card card3;

    @Before
    public void before() {
        player = new Player("Bill");
    }


    @Test
    public void testGetName() {
        assertEquals("Bill", player.getName());
    }

    @Test
    public void testSetName() {
        player.setName("Meg");
        assertEquals("Meg", player.getName());
    }

    @Test
    public void testGetHand() {
        player.setHand(hand);
        assertEquals(hand, player.getHand());
    }

    @Test
    public void testSetHand() {
        player.setHand(hand);
        assertEquals(hand, player.getHand());
    }

    @Test
    public void testHandStartsAtZero() {
        assertEquals(0, player.getHand().size());
    }

    @Test
    public void testGetChips() {
        assertEquals((Integer) 100, player.getChips());
    }

    @Test
    public void testSetChips() {
        player.setChips(101);
        assertEquals((Integer) 101, player.getChips());
    }

    @Test
    public void testBet() {
        player.bet(10);
        assertEquals((Integer) 90, player.getChips());
    }

}