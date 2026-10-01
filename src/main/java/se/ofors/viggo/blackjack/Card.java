package se.ofors.viggo.blackjack;

public class Card {
    private final String suit;
    private final int number;
    private final String symbol; // Can't be char because of '10'
    private int value;

    public Card(String type, int number) {
        this.suit = type;
        this.number = number;
        symbol = setSymbol(number);
        value = setValue();
    }

    private String setSymbol(int number) {
        return switch (number) {
            case 1 -> "A";
            case 11 -> "J";
            case 12 -> "D";
            case 13 -> "K";
            default -> "" + number;
        };
    }

    public void changeAce() {
        if (symbol.equals("A")) {
            value = 1;
        }
    }

    private int setValue() {
        return switch (number) {
            case 1 -> 11;
            case 11, 12, 13 -> 10;
            default -> number;
        };
    }

    public String getSuit() {
        return suit;
    }

    public int getNumber() {
        return number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public String getAsciiArt() {
        String left = String.format("%-2s", value);
        String right = String.format("%2s", value);

        return """
                ┌─────────┐
                │%s       │
                │         │
                │    %s    │
                │         │
                │       %s│
                └─────────┘
                """.formatted(symbol, suit, symbol);
    }

    @Override
    public String toString() {
        return symbol + " of " + suit;
    }
}
