package GameNT;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final int value;

    public Card(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Valor invalido de carta");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int compareTo(Card other) {
        return Integer.compare(this.value, other.value);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value;
    }

    public int hashCode() {
        return Objects.hash(value);
    }
}
