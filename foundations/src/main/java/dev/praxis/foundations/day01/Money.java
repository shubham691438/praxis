package dev.praxis.foundations.day01;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * EXERCISE 5 — Never use double for money
 *
 * This is diagnostic Q2. 0.1 + 0.2 != 0.3 in binary floating point.
 *
 * Run this in a scratch main() before you start:
 *     System.out.println(0.1 + 0.2);
 * Look at what it actually prints. That is why this class exists.
 *
 * Immutable, like Temperature. BigDecimal inside, never double.
 */
public final class Money {

    // TODO: private final BigDecimal amount;
    private final BigDecimal amount;

    /** Construct from a decimal string, e.g. "10.50". Never from a double. */
    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }

    private Money(BigDecimal amount) {
      this.amount=amount;
    }

    /** Returns a NEW Money. Must not modify this one. */
    public Money plus(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    /** Returns a NEW Money. Must not modify this one. */
    public Money minus(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    /** Always exactly 2 decimal places, HALF_UP. e.g. "10.50" */
    @Override
    public String toString() {
        return this.amount.setScale(2,RoundingMode.HALF_UP).toString();
    }

    /**
     * Value equality: two Money with the same amount are equal.
     *
     * Careful — BigDecimal.equals() considers scale, so 10.5 and 10.50 are NOT
     * equal by it. Use compareTo() == 0 instead. This trips up experienced devs.
     */
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;

        if(! ( o instanceof Money)) return false;

        Money other = (Money)o;
        return this.amount.compareTo(other.amount)==0;
    }

    @Override
    public int hashCode() {
       return this.amount.stripTrailingZeros().intValue();
    }
}
