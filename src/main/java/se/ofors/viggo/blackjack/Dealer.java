package se.ofors.viggo.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer {

    private final Random random = new Random();
    private final Player player;

    private final List<Card> deck;
    private final List<Card> faceUpCards = new ArrayList<>();
    private Card holeCard;

    public Dealer(List<Card> deck, Player player) {
        this.deck = deck;
        this.player = player;
    }

    public void setup() {
        for (int i = 0; i < 2; i++) {
            player.addCard(dealCard());
        }

        holeCard = dealCard();
        faceUpCards.add(dealCard());
    }

    private Card dealCard() {
        int randomIndex = random.nextInt(0, deck.size());
        Card randomCard = deck.get(randomIndex);
        deck.remove(randomIndex);

        return randomCard;
    }

    public Card deal() {
        return dealCard();
    }

    public void dealersTurn() throws InterruptedException {
        faceUpCards.add(holeCard);

        printTable();
        Thread.sleep(1000);

        int dealersScore = countDealerCards();
        while (dealersScore < 17) {
            Main.clearScreen();
            faceUpCards.add(dealCard());
            dealersScore = countDealerCards();
            printTable();
            Thread.sleep(1000);
        }
    }

    public void printTable() {
        IO.println("<----DEALERS HAND---->");
        IO.println("Cards: ");
        for (Card card : faceUpCards) {
            IO.println(card.getAsciiArt());
        }

        IO.println("Hole Card: HIDDEN");
        IO.println("\nCount: " + countDealerCards());

        IO.println("<----PLAYERS HAND---->");
        IO.println("Cards: ");

        for (Card card : player.getCards()) {
            IO.println(card.getAsciiArt());
        }

        IO.println("\nCount: " + countPlayerCards());
    }

    // TODO: MAKE ONE METHOD FOR THESE TWO.
    private int countDealerCards() {
        int dealerAmount = 0;
        for (Card card : faceUpCards) {
            if (card.getSymbol().equals("A")) {
                if (dealerAmount + card.getNumber() > 21) {
                    card.changeAce();
                }
            }
            dealerAmount += card.getValue();
        }

        return dealerAmount;
    }

    public int countPlayerCards() {
        // PLAYER
        int playerAmount = 0;
        for (Card card : player.getCards()) {
            if (card.getSymbol().equals("A")) {
                if (playerAmount + card.getNumber() > 21) {
                    card.changeAce();
                }
            }
            playerAmount += card.getValue();
        }

        return playerAmount;
    }

    public int returnWinPercentage() {
        int playerCount = countPlayerCards();
        int dealerCount = countDealerCards();

        if (playerCount > 21) {
            // DEALER WIN
            return 0;
        } else if (dealerCount > 21) {
            // PLAYER WIN
            return 2;
        } else if (playerCount == dealerCount) {
            // TIE
            return 1;
        } else if (playerCount > dealerCount) {
            // PLAYER WIN
            return 2;
        } else {
            // DEALER WIN
            return 0;
        }
    }
}
