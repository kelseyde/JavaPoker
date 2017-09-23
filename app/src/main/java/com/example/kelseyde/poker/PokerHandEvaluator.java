package com.example.kelseyde.poker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PokerHandEvaluator {

    public HashMap<Integer, Integer> setUpRankCounter() {
        HashMap<Integer, Integer> rankCounter = new HashMap<>();
        rankCounter.put(1, 0);
        rankCounter.put(2, 0);
        rankCounter.put(3, 0);
        rankCounter.put(4, 0);
        rankCounter.put(5, 0);
        rankCounter.put(6, 0);
        rankCounter.put(7, 0);
        rankCounter.put(8, 0);
        rankCounter.put(9, 0);
        rankCounter.put(10, 0);
        rankCounter.put(11, 0);
        rankCounter.put(12, 0);
        rankCounter.put(13, 0);
        return rankCounter;
    }

    public HashMap<RankType, Integer> setUpRoyalRankCounter() {
        HashMap<RankType, Integer> royalRankCounter = new HashMap<>();
        royalRankCounter.put(RankType.TEN, 0);
        royalRankCounter.put(RankType.JACK, 0);
        royalRankCounter.put(RankType.QUEEN, 0);
        royalRankCounter.put(RankType.KING, 0);
        royalRankCounter.put(RankType.ACE, 0);
        return royalRankCounter;
    }

    public HashMap<SuitType, Integer> setUpSuitCounter() {
        HashMap<SuitType, Integer> suitCounter = new HashMap<>();
        suitCounter.put(SuitType.DIAMONDS, 0);
        suitCounter.put(SuitType.CLUBS, 0);
        suitCounter.put(SuitType.HEARTS, 0);
        suitCounter.put(SuitType.SPADES, 0);
        return suitCounter;
    }

    public Card highCard(ArrayList<Card> hand) {
        Integer highestValue = 0;
        Card highestCard = null;
        for (Card card : hand) {
            Integer cardValue = card.getRank().getValue();
            if (cardValue >= highestValue) {
                highestValue = cardValue;
                highestCard = card;
            }
        }
        return highestCard;
    }

    public ArrayList< ArrayList<Card> > howManyOfKind(int howMany, ArrayList<Card> hand) {
        ArrayList<ArrayList<Card>> kindCounter = new ArrayList<>();
        HashMap<Integer, Integer> rankCounter = setUpRankCounter();

        //increment rankCounter to determine how many of each kind there are...
        for (Card card : hand) {
            Integer keyOfCard = card.getRank().getValue();
            Integer currentValue = rankCounter.get(keyOfCard);
            rankCounter.put(keyOfCard, (currentValue + 1));
        }

        //...check if there is the correct number of a kind...
        for (Integer key : rankCounter.keySet()) {
            Integer cardCount = rankCounter.get(key);
            if (cardCount.equals(howMany)) {

                //...and if there is, make an ArrayList of those cards...
                ArrayList<Card> kindArray = new ArrayList<>();
                for (Card card : hand) {
                    if ((card.getRank().getValue()) == key) {
                        kindArray.add(card);
                    }
                }
                //...and add it to the kindCounter ArrayList.
                kindCounter.add(kindArray);
            }
        }
        return kindCounter;
    }

    public ArrayList<Card> flush(ArrayList<Card> hand) {
        ArrayList<Card> flush = new ArrayList<Card>();
        HashMap<SuitType, Integer> suitCounter = setUpSuitCounter();

        //increment suitCounter to determine how many of each suit there are...
        for (Card card : hand) {
            SuitType cardSuit = card.getSuit();
            for (SuitType suit : suitCounter.keySet()) {
                if (cardSuit.equals(suit)) {
                    Integer currentValue = suitCounter.get(suit);
                    suitCounter.put(suit, (currentValue + 1));
                }
            }
        }

        //...check if there is a flush...
        for (SuitType suit : suitCounter.keySet()) {
            if (suitCounter.get(suit) == 5) {

                //...and if there is, add those cards to the flush ArrayList.
                for (Card card : hand) {
                    SuitType cardSuit = card.getSuit();
                    if (cardSuit.equals(suit)) {
                        flush.add(card);
                    }
                }
            }
        }
        return flush;
    }



    public ArrayList<Card> royalFlush(ArrayList<Card> hand) {
        ArrayList<Card> royalFlush = new ArrayList<>();

        //exit early if no flush...
        if (flush(hand).isEmpty()) return royalFlush;

        //...increment royalRankCounter to check how many of each rank there are...
        HashMap<RankType, Integer> royalRankCounter = setUpRoyalRankCounter();
        for (Card card : hand) {
            RankType cardRank = card.getRank();
            for (RankType rank : royalRankCounter.keySet()) {
                if (cardRank.equals(rank)) {
                    Integer currentValue = royalRankCounter.get(rank);
                    royalRankCounter.put(rank, (currentValue + 1));
                }
            }
        }

        //...check if there is a royal flush...
        if((royalRankCounter.get(RankType.TEN) == 1) &&
                (royalRankCounter.get(RankType.JACK) == 1) &&
                (royalRankCounter.get(RankType.QUEEN) == 1) &&
                (royalRankCounter.get(RankType.KING) == 1) &&
                (royalRankCounter.get(RankType.ACE) == 1)) {

            //and if there is, add the cards into royalFlush
            for (Card card : hand) {
                royalFlush.add(card);
            }
        }
        return royalFlush;
    }

    public ArrayList<Card> fullHouse(ArrayList<Card> hand) {
        ArrayList<Card> fullHouse = new ArrayList<>();

        //set up a copy of hand ArrayList, so that elements can be removed w/o removing from hand
        ArrayList<Card> workingHand = new ArrayList<>();
        for (Card card : hand) {
            workingHand.add(card);
        }

        //gets ArrayList of three-of-a-kind combinations
        ArrayList<ArrayList<Card>> threeOfAKinds = howManyOfKind(3, workingHand);
        for (ArrayList<Card> threeofAKind : threeOfAKinds) {
            if (!(threeofAKind.isEmpty())) {
                //if there is a three-of-a-kind, remove it from workingHand
                workingHand.removeAll(threeofAKind);
                //and check workingHand for two-of-a-kinds
                ArrayList<ArrayList<Card>> twoOfAKinds = howManyOfKind(2, workingHand);
                //if there is a two-of-a-kind
                if (!(twoOfAKinds.get(0).isEmpty())) {
                    //add two-of-a-kind to fullHouse, along with three-of-a-kind, making full house
                    fullHouse.addAll(threeofAKind);
                    fullHouse.addAll(twoOfAKinds.get(0));
                }
            }
        }
        return fullHouse;
    }

    public ArrayList<Card> straight(ArrayList<Card> hand) {
        ArrayList<Card> straight = new ArrayList<>();
        HashMap<Integer, Integer> rankCounter = setUpRankCounter();

        //increment rankCounter to determine how many of each kind there are...
        for (Card card : hand) {
            Integer keyOfCard = card.getRank().getValue();
            Integer currentValue = rankCounter.get(keyOfCard);
            rankCounter.put(keyOfCard, (currentValue + 1));
        }

        //if there are cards of five consecutive ranks...
        for (int i=1; i < 10; i++) {
            if ((rankCounter.get(i) > 0) &&
                    (rankCounter.get(i + 1) > 0) &&
                    (rankCounter.get(i + 2) > 0) &&
                    (rankCounter.get(i + 3) > 0) &&
                    (rankCounter.get(i + 4) > 0)) {

                //...and if there are, add each one into straight ArrayList...
                for (Card card : hand) {
                    if (((card.getRank().getValue() == i) ||
                            (card.getRank().getValue() == i + 1) ||
                            (card.getRank().getValue() == i + 2) ||
                            (card.getRank().getValue() == i + 3) ||
                            (card.getRank().getValue() == i + 4)) &&
                            !(isDuplicateRank(card, straight))) {
                        straight.add(card);
                    }
                }
            }
        }

        //separate if-statement for low-ace straight
        if ((rankCounter.get(13) > 0) &&
                (rankCounter.get(1) > 0) &&
                (rankCounter.get(2) > 0) &&
                (rankCounter.get(3) > 0) &&
                (rankCounter.get(4) > 0)) {
            for (Card card : hand) {
                if (((card.getRank().getValue() == 13) ||
                        (card.getRank().getValue() == 1) ||
                        (card.getRank().getValue() == 2) ||
                        (card.getRank().getValue() == 3) ||
                        (card.getRank().getValue() == 4)) &&
                        !(isDuplicateRank(card, straight))) {
                    straight.add(card);
                }
            }
        }

        return straight;
    }

    public boolean isDuplicateRank(Card card, ArrayList<Card> hand) {
        boolean isDuplicate = false;
        for (Card handCard : hand) {
            if (card.getRank().equals(handCard.getRank())) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }







}
