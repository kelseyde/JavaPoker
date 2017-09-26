package com.example.kelseyde.poker.models;

import java.util.ArrayList;
import java.util.Scanner;


public class PokerConsole {

    PokerGame game = new PokerGame();
    Scanner sc = new Scanner(System.in);
    Logger lg = new ConsoleLogger();
    CardDisplayer cd = new CardDisplayer();
    PokerHandEvaluator evaluator = new PokerHandEvaluator();

    int bet1 = 0;
    int bet2 = 0;
    int bet3 = 0;

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

    public static boolean isInt(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfp) {
            return false;
        }
        return true;
    }

    public void showCards() {
        lg.log("YOUR HAND: "+cd.displayHand(game.getCurrentPlayer().getHand())+
                "\nHit enter to continue.");
        sc.nextLine();
        lg.clear();
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

        lg.log("\nPlayer 2, please enter your name.");
        playerName = sc.nextLine();
        Player player2 = new Player(playerName);
        game.addPlayer(player2);
        lg.log("\nThank you.");
        game.setCurrentPlayer(player1);
    }

    public void dealHoleCards() {
        game.getDealer().getDeck().newDeck();
        game.getDealer().getDeck().shuffle();
        game.getDealer().deal(2, game.getPlayers());
        lg.log("The dealer will now deal each player two hole cards.\n");
        lg.log(game.getCurrentPlayer().getName()+", hit enter to view your cards.");
        sc.nextLine();
        showCards();
        game.nextPlayer();
        lg.log(game.getCurrentPlayer().getName()+", hit enter to view your cards.");
        sc.nextLine();
        showCards();
        game.nextPlayer();
        lg.clear();
        bettingRound();
    }

    public void firstBet() {
        lg.log(game.getCurrentPlayer().getName() + ", you now have the chance to bet.\n" +
                "Enter how much you'd like to bet.\nEnter 0 to check.\nEnter 'view' to view cards.\n" +
                "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("view")) {
            showCards();
            firstBet();
        } else if (isInt(choice)) {
            bet1 = Integer.parseInt(choice);
            game.getCurrentPlayer().bet(bet1);
            game.nextPlayer();
        } else {
            lg.log("Incorrect input!\n");
            firstBet();
        }
    }

    public void secondBet() {
        lg.log(game.getCurrentPlayer().getName() + ", you must either call "+bet1+", raise or fold.\n" +
                "Enter how much you'd like to bet.\nEnter 0 to check.\nEnter 'view' to view cards.\n" +
                "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
        String choice = sc.nextLine().toLowerCase();
        if (choice.equals("view")) {
            showCards();
            firstBet();
        } else if (isInt(choice)) {
            bet2 = Integer.parseInt(choice);
            game.getCurrentPlayer().bet(bet2);
        } else {
            lg.log("Incorrect input!");
            secondBet();
        }
        game.nextPlayer();
    }

    public void thirdBet() {
        if (bet2 > bet1) {
            lg.log("\n" + game.getCurrentPlayer().getName() + ", you must either call " + bet2 + " or fold.\n" +
                    "Enter how much you'd like to bet (enter above or below " + bet2 + " to fold).\n"+
                    "Enter 'view' to view your cards.\n"+
                    "YOUR CHIPS: " + game.getCurrentPlayer().getChips());
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("view")) {
                showCards();
                thirdBet();
            } else if (isInt(choice)) {
                bet3 = Integer.parseInt(choice);
                game.getCurrentPlayer().bet(bet3 - bet1);
                if ((bet3 > bet2) || (bet3 < bet2)) {
                    lg.log("\n" + game.getCurrentPlayer().getName() + " folds.\n");
                    game.nextPlayer();
                    lg.log(game.getCurrentPlayer().getName() + " wins!");
                    System.exit(1);
                } else if (bet2 == bet3) {
                    lg.log("\n" + game.getCurrentPlayer().getName() + " has called! On to the next round.");
                    lg.log("\n");
                }
            }
        } else if (bet2 == bet1) {
            game.nextPlayer();
            lg.log("\n"+game.getCurrentPlayer().getName() + " has called! On to the next round...\n");
        } else {
            game.nextPlayer();
            lg.log("\n"+game.getCurrentPlayer().getName() + " folds.\n");
            game.nextPlayer();
            lg.log(game.getCurrentPlayer().getName() + " wins!");
            System.exit(1);
        }
    }

    public void bettingRound() {
        firstBet();
        secondBet();
        thirdBet();
    }

    public void dealFlop() {
        lg.log("The dealer will now deal the FLOP.\n");
        lg.pause(2);
        lg.log("\033[1;30m"+"Dealing the FLOP..."+"\033[0m");
        lg.pause(1);
        game.getDealer().dealTable(3, game.getTable());
        lg.log("THE FLOP: " + cd.displayHand(game.getTable()) + "\n");
        lg.pause(1);
        bettingRound();
    }

    public void dealTurn() {
        lg.log("The dealer will now deal the TURN.\n");
        lg.pause(2);
        lg.log("\033[1;30m"+"Dealing the TURN..."+"\033[0m");
        lg.pause(1);
        game.getDealer().dealTable(1, game.getTable());
        lg.log("THE TURN: " + cd.displayHand(game.getTable()) + "\n");
        lg.pause(1);
        bettingRound();
    }

    public void dealRiver() {
        lg.log("The dealer will now deal the RIVER.\n");
        lg.pause(2);
        lg.log("\033[1;30m"+"Dealing the RIVER..."+"\033[0m");
        lg.pause(1);
        game.getDealer().dealTable(1, game.getTable());
        lg.log("THE RIVER: "+cd.displayHand(game.getTable())+"\n");
        lg.pause(1);
        bettingRound();
    }

    public void showdown() {
        lg.log("\nAll the cards have been dealt; it's time for the \033[1;30mSHOWDOWN.\033[0m \n");
        lg.pause(1);
        lg.log("THE TABLE: "+cd.displayHand(game.getTable())+"\n");
        lg.pause(1);
        ArrayList<Card> handToEvaluate = new ArrayList<>(game.combineHandAndTable(game.getCurrentPlayer()));
        lg.log(game.getCurrentPlayer().getName()+"'s hand: "+cd.displayHand(game.getCurrentPlayer().getHand())
                +" "+ printHand(handToEvaluate));
        handToEvaluate.clear();
        lg.pause(1);
        game.nextPlayer();
        handToEvaluate = game.combineHandAndTable(game.getCurrentPlayer());
        lg.log(game.getCurrentPlayer().getName()+"'s hand: "+cd.displayHand(game.getCurrentPlayer().getHand())
                +" "+ printHand(handToEvaluate)+"\n");
        Player winner = game.getWinner();
        lg.pause(1);
        lg.log("\033[1;30m"+winner.getName()+" wins!\033[0m\n\n");
        lg.pause(2);
        lg.log("Would you like to play again?\n"+
                "'Y' - Yes, 'N' - No");
        String choice = sc.next().toLowerCase();
        if (choice.equals("y")) {
            game.clearTable();
            game.clearHands();
        } else if (choice.equals("n")) {
            lg.log("Thank you for playing!");
            boolean newRound = true;
            System.exit(1);
        }
    }

    public void play() {
        boolean newRound = false;
        introduction();
        dealHoleCards();
        dealFlop();
        dealTurn();
        dealRiver();
        showdown();
        if (newRound = true) {
            newRound();
        }
    }

    public void newRound() {
        boolean newRound = false;
        lg.clear();
        lg.log("Welcome back!");
        dealHoleCards();
        dealFlop();
        dealTurn();
        dealRiver();
        showdown();
        if (newRound = true) {
            newRound();
        }
    }

    public static void main(String[] args) {
        PokerConsole console = new PokerConsole();
        console.play();
    }


    }


