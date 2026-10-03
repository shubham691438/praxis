package dev.praxis.foundations.week01;

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
        return new Money(this.amount+other)l
    }

    /** Returns a NEW Money. Must not modify this one. */
    public Money minus(Money other) {
        throw new UnsupportedOperationException("TODO: implement minus()");
    }

    /** Always exactly 2 decimal places, HALF_UP. e.g. "10.50" */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO: implement toString()");
    }

    /**
     * Value equality: two Money with the same amount are equal.
     *
     * Careful — BigDecimal.equals() considers scale, so 10.5 and 10.50 are NOT
     * equal by it. Use compareTo() == 0 instead. This trips up experienced devs.
     */
    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("TODO: implement equals()");
    }

    @Override
    public int hashCode() {
        // Must be consistent with equals(). Week 2 covers the full contract —
        // for now, derive it from the amount stripped of trailing zeros.
        throw new UnsupportedOperationException("TODO: implement hashCode()");
    }
}
