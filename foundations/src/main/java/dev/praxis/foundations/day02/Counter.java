package dev.praxis.foundations.day02;

/**
 * EXERCISE 4 — static vs instance
 *
 * You asked why toString() takes no input. The answer was: instance methods
 * carry an implicit `this`. This exercise makes that concrete.
 *
 * A `static` member belongs to the CLASS — one copy, shared by everything.
 * An instance member belongs to each OBJECT — every object gets its own.
 *
 * Diagnostic Q5 was this question, and you got it right. This is the other
 * half: knowing when to reach for which.
 */
public final class Counter {

    /** TODO: a static int, shared by every Counter, counting how many exist. */
    static int count=0;
    /** TODO: an instance int — each Counter's own serial number, 1-based. */
     int serialNumber;
    /**
     * Each new Counter should:
     *   - increment the shared total
     *   - take that new total as its own serial number
     *
     * So the first Counter ever created is #1, the second is #2, and so on.
     */
    public Counter() {
        count+=1;
        this.serialNumber=count;
    }

    /** This object's own serial number. */
    public int serialNumber() {
        return this.serialNumber;
    }

    /**
     * How many Counters have been created in total.
     *
     * Note this is STATIC — callable as Counter.totalCreated(), with no object.
     * It has no `this`, so it can only read static state.
     */
    public static int totalCreated() {
        return count;
    }

    /** Reset the shared total to zero. The tests need this to stay independent. */
    public static void reset() {
        count=0;
    }
}
