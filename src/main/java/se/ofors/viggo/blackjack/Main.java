package se.ofors.viggo.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Card> deck = new ArrayList<>();
    private static final Player player = new Player("Viggo");
    private static final Dealer dealer = new Dealer(deck, player);

    static void main() throws InterruptedException {
        createFullDeck();
        player.deposit(1000);

        String betAmountInput = IO.readln("How much would you like to bet? ");
        int betAmount = 0;

        try {
            betAmount = Integer.parseInt(betAmountInput);
        } catch (NumberFormatException e) {
            IO.println("It has to be a number.");
        }

        player.bet(betAmount);

        dealer.setup();
        dealer.printTable();

        while (true) {
            if (dealer.countPlayerCards() < 21) {
                String playingChoice = IO.readln("HIT or STAND? (h/s): ");
                if (playingChoice.equalsIgnoreCase("h")) {
                    hit();
                } else if (playingChoice.equalsIgnoreCase("s")) {
                    break;
                } else {
                    IO.println("not valid input.");
                }
            } else {
                break;
            }

        }
        clearScreen();
        dealer.dealersTurn();

        int winPercentage = dealer.returnWinPercentage();
        double totalWin = betAmount * winPercentage;
        player.addBalance(totalWin);
        IO.println("\n\nYou won: $" + totalWin + "\nTotal balance: $" + player.getBalance());
    }

    private static void createFullDeck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                switch (i) {
                    case 0 -> deck.add(new Card("♣", j));
                    case 1 -> deck.add(new Card("♦", j));
                    case 2 -> deck.add(new Card("♥", j));
                    case 3 -> deck.add(new Card("♠", j));
                }
            }
        }
    }

    private static void hit() {
        Card dealtCard = dealer.deal();
        player.addCard(dealtCard);
        dealer.printTable();
    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            IO.println(" ");
        }
    }
}
