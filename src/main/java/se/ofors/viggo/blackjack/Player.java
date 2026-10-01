package se.ofors.viggo.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private double balance;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            IO.println("Invalid amount.");
        }
    }

    public void bet(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
        } else {
            IO.println("Invalid amount.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addBalance(double amount) {
        balance += amount;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public List<Card> getCards() {
        return cards;
    }
}
