package com.example.kelseyde.poker.models;

import java.util.ArrayList;
import java.util.Scanner;


public class PokerRunner {

    PokerGame game = new PokerGame();
    Scanner sc = new Scanner(System.in);
    Logger lg = new ConsoleLogger();
    CardDisplayer cd = new CardDisplayer();
    PokerHandEvaluator evaluator = new PokerHandEvaluator();

    Card ace1 = new Card(SuitType.CLUBS, RankType.ACE);
    Card ace2 = new Card(SuitType.DIAMONDS, RankType.ACE);
    Card ace3 = new Card(SuitType.SPADES, RankType.ACE);
    Card ace4 = new Card(SuitType.HEARTS, RankType.ACE);

    public String printHand(ArrayList<Card> hand) {
        String result = null;
        switch (evaluator.evaluateHand(hand)) {
            case 10 : result = "ROYAL FLUSH"; break;
            case 9 : result = "STRAIGHT FLUSH"; break;
            case 8 : result = "FOUR OF A KIND"; break;
            case 7 : result = "FULL HOUSE"; break;
            case 6 : result = "FLUSH"; break;
            case 5 : result = "STRAIGHT"; break;
            case 4 : result = "THREE OF A KIND"; break;
            case 3 : result = "TWO PAIR"; break;
            case 2 : result = "TWO OF A KIND"; break;
            case 1 : result = "HIGH CARD"; break;
        }
        return result;
    }


    public void introduction() {
        lg.clear();
        lg.log(cd.displayCard(ace1)+" "+cd.displayCard(ace2)+" "+"\033[1;30m"+
                "Welcome to Command Line Poker!"+
                "\033[0m"+" "+cd.displayCard(ace3)+" "+cd.displayCard(ace4)+"\n" +
                "Today we will be playing two-player Texas Hold'em. \n" +
                "Player 1, please enter your name.");
        String playerName = sc.nextLine();
        Player player1 = new Player(playerName);
        game.addPlayer(player1);

        lg.log("Player 2, please enter your name.");
        playerName = sc.nextLine();
        Player player2 = new Player(playerName);
        game.addPlayer(player2);
        lg.log("Thank you.");
        game.setCurrentPlayer(player1);
    }

    public void dealHoleCards() {
        game.getDealer().getDeck().newDeck();
        game.getDealer().getDeck().shuffle();
        game.getDealer().deal(2, game.getPlayers());
        lg.clear();
        lg.log("The dealer will now deal each player two hole cards.\n"+
                game.getCurrentPlayer().getName()+", hit enter to view your cards.");
        sc.nextLine();
        lg.log("YOUR HAND: "+cd.displayHand(game.getCurrentPlayer().getHand())+
                "\nHit enter to continue.");
        sc.nextLine();
        lg.clear();
        game.nextPlayer();
        lg.log(game.getCurrentPlayer().getName()+", hit enter to view your cards.");
        sc.nextLine();
        lg.log("YOUR HAND: "+cd.displayHand(game.getCurrentPlayer().getHand())+
                "\nHit enter to continue.");
        sc.nextLine();
        lg.clear();
    }

    public void roundOfBetting() {
        lg.log(game.getCurrentPlayer().getName() + ", you now have the chance to bet.\n" +
                "Enter how much you'd like to bet (enter 0 if you'd like to check).\n" +
                "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        Integer bet1 = sc.nextInt();
        game.getCurrentPlayer().bet(bet1);
        game.nextPlayer();

        lg.log(game.getCurrentPlayer().getName() + ", you must either call " + bet1 + ", raise or fold.\n" +
                "Enter how much you'd like to bet (enter below " + bet1 + " to fold).\n" +
                "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        Integer bet2 = sc.nextInt();
        game.getCurrentPlayer().bet(bet2);
        game.nextPlayer();
        if (bet2 > bet1) {
            lg.log(game.getCurrentPlayer().getName() + ", you must either call " + bet2 + " or fold.\n" +
                    "Enter how much you'd like to bet (enter above or below " + bet2 + " to fold).\n" +
                    "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
            Integer bet3 = sc.nextInt();
            game.getCurrentPlayer().bet(bet3 - bet1);
            if ((bet3 > bet2) || (bet3 < bet2)) {
                lg.log(game.getCurrentPlayer().getName() + " folds.\n");
                game.nextPlayer();
                lg.log(game.getCurrentPlayer().getName() + " wins!");
                System.exit(1);
            } else if (bet2 == bet3) {
                lg.log(game.getCurrentPlayer().getName() + " has called! On to the next round.");
                lg.log("\n");
            }
        } else if (bet2 == bet1) {
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " has called! On to the next round...\n");
        } else {
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " folds.\n");
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " wins!");
            System.exit(1);
        }
    }

    public void dealFlop() {
        lg.log("The dealer will now deal the FLOP.");
        String next = sc.nextLine();
        game.getDealer().dealTable(3, game.getTable());
        lg.log("THE FLOP: " + cd.displayHand(game.getTable()) + "\n");
        lg.log("Hit enter to continue.");
        sc.nextLine();
    }

    public void dealTurn() {
        lg.log("The dealer will now deal the TURN.");
        sc.nextLine();
        game.getDealer().dealTable(1, game.getTable());
        lg.log("THE TURN: " + cd.displayHand(game.getTable()) + "\n");
        lg.log("Hit enter to continue.");
        sc.nextLine();
    }

    public void dealRiver() {
        lg.log("The dealer will now deal the RIVER.");
        sc.nextLine();
        game.getDealer().dealTable(1, game.getTable());
        lg.log("THE RIVER: "+cd.displayHand(game.getTable())+"\n");
        lg.log("Hit enter to continue.");
        sc.nextLine();
    }

    public void showdown() {
        lg.log("All the cards have been dealt; it's time for the showdown. Hit enter to show your hands.");
        sc.nextLine();
        lg.log("THE TABLE: "+cd.displayHand(game.getTable()));
        ArrayList<Card> handToEvaluate = new ArrayList<>(game.combineHandAndTable(game.getCurrentPlayer()));
        lg.log(game.getCurrentPlayer().getName()+"'s hand: "+cd.displayHand(game.getCurrentPlayer().getHand())
                +" "+ printHand(handToEvaluate));
        handToEvaluate.clear();
        game.nextPlayer();
        handToEvaluate = game.combineHandAndTable(game.getCurrentPlayer());
        lg.log(game.getCurrentPlayer().getName()+"'s hand: "+cd.displayHand(game.getCurrentPlayer().getHand())
                +" "+ printHand(handToEvaluate));
        Player winner = game.getWinner();
        lg.log(winner.getName()+" wins! \n\n" +
                "Would you like to play again?\n" +
                "'Y' - Yes, 'N' - No");
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            game.clearTable();
            game.clearHands();
        } else if (choice.equals("n")) {
            lg.log("Thank you for playing!");
            System.exit(1);
        }
    }

    public void play() {
        boolean newRound = false;
        introduction();
        dealHoleCards();
        roundOfBetting();
        dealFlop();
        roundOfBetting();
        dealTurn();
        roundOfBetting();
        dealRiver();
        roundOfBetting();
        showdown();
        if (newRound = true) {
            newRound();
        }
    }

    public void newRound() {
        boolean newRound = false;
        lg.log("Welcome back!");
        dealHoleCards();
        roundOfBetting();
        dealFlop();
        roundOfBetting();
        dealTurn();
        roundOfBetting();
        dealRiver();
        roundOfBetting();
        showdown();
        if (newRound = true) {
            newRound();
        }
    }


    public static void main(String[] args) {
        PokerRunner runner = new PokerRunner();
        runner.play();
    }


    }


