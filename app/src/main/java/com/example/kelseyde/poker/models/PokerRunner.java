package com.example.kelseyde.poker.models;

import java.util.ArrayList;
import java.util.Scanner;


public class PokerRunner {

        PokerGame game = new PokerGame();
        Scanner sc = new Scanner(System.in);
        Logger lg = new ConsoleLogger();
        CardDisplayer cd = new CardDisplayer();
        PokerHandEvaluator evaluator = new PokerHandEvaluator();

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
        lg.log("Welcome to Command Line Poker! Today we will be playing two-player \n" +
                "Texas Hold'em. Player 1, please enter your name.");
        String playerName = sc.nextLine();
        Player player1 = new Player(playerName);
        game.addPlayer(player1);

        lg.log("Player 2, please enter your name.");
        playerName = sc.nextLine();
        Player player2 = new Player(playerName);
        game.addPlayer(player2);

        game.setCurrentPlayer(player1);
    }

    public void dealHoleCards() {
        game.getDealer().deal(2, game.getPlayers());

        lg.log("Thank you. The dealer will now deal each player two hole cards.\n" +
                game.getCurrentPlayer().getName() + ", hit enter to view your cards.");
        sc.nextLine();
        lg.log("YOUR HAND: " + cd.displayHand(game.getCurrentPlayer().getHand()));
        lg.log("\nHit enter to continue.");
        sc.nextLine();
        game.nextPlayer();
        lg.log(game.getCurrentPlayer().getName() + ", hit enter to view your cards.");
        sc.nextLine();
        lg.log("YOUR HAND: " + cd.displayHand(game.getCurrentPlayer().getHand()));
        lg.log("\nHit enter to continue.");
        sc.nextLine();
    }

    public void roundOfBetting() {
        lg.log(game.getCurrentPlayer().getName() + ", you now have the chance to bet.\n" +
                "Enter how much you'd like to bet (enter 0 if you'd like to check).");
        lg.log("YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        Integer bet1 = sc.nextInt();
        game.getCurrentPlayer().bet(bet1);
        game.nextPlayer();

        lg.log(game.getCurrentPlayer().getName() + ", you must either call " + bet1 + ", raise or fold.\n" +
                "Enter how much you'd like to bet (enter below " + bet1 + " to fold).");
        lg.log("YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        Integer bet2 = sc.nextInt();
        game.getCurrentPlayer().bet(bet2);
        game.nextPlayer();
        if (bet2 > bet1) {
            lg.log(game.getCurrentPlayer().getName() + ", you must either call " + bet2 + " or fold.\n" +
                    "Enter how much you'd like to bet (enter above or below " + bet2 + " to fold).");
            lg.log("YOUR CHIPS: " + game.getCurrentPlayer().getChips());
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
            lg.log(game.getCurrentPlayer().getName() + " has called! On to the next round...");
            lg.log("\n");
        } else {
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " folds.\n");
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " wins!");
            System.exit(1);
        }
    }

    public void dealFlop() {
        lg.log("The dealer will now deal the FLOP. Hit enter to view.");
        sc.nextLine();
        game.getDealer().dealTable(3, game.getTable());
        lg.log("THE FLOP: " + cd.displayHand(game.getTable()) + "\n");
        lg.log("Hit enter to continue.");
        sc.nextLine();
    }

    public void dealTurn() {
        lg.log("The dealer will now deal the TURN. Hit enter to view.");
        sc.nextLine();
        game.getDealer().dealTable(1, game.getTable());
        lg.log("THE TURN: " + cd.displayHand(game.getTable()) + "\n");
        lg.log("Hit enter to continue.");
        sc.nextLine();
    }

    public void dealRiver() {
        lg.log("The dealer will now deal the RIVER. Hit enter to view.");
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
        String choice = sc.nextLine();
        if (choice.equals("Y")) {
            play();
        } else if (choice.equals("N")) {
            lg.log("Thank you for playing!");
            System.exit(1);
        }
    }

    public void play() {
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
    }


    public static void main(String[] args) {
        PokerRunner runner = new PokerRunner();
        runner.play();
    }


    }


