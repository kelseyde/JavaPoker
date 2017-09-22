package com.example.kelseyde.poker;

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

}