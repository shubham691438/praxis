package dev.praxis.foundations.week01;

/**
 * EXERCISE 1 — Immutability
 *
 * An immutable value object. Once constructed, it never changes.
 * Every "modification" returns a NEW Temperature and leaves this one alone.
 *
 * This is how String behaves, and why `s.toUpperCase()` on its own does nothing.
 *
 * Rules:
 *   - the field must be `final`
 *   - no setters
 *   - warmer()/cooler() return a new instance
 */
public final class Temperature {

    // TODO: declare a private final double field for celsius
    private double celsiusTemperature;

    public Temperature(double celsius) {
        // TODO: assign the field
        celsiusTemperature=celsius;

    }

    public double celsius() {
        // TODO: return the field
        return celsiusTemperature;
    }

    public double fahrenheit() {
        // TODO: convert. F = C * 9/5 + 32
        // Careful: 9/5 in Java is integer division and equals 1. Think about it.
        double fahrenheitTemperature = celsiusTemperature * (double)9/5+32;
        return fahrenheitTemperature;
    }

    /** Returns a NEW Temperature, `degrees` warmer. Must not modify this one. */
    public Temperature warmer(double degrees) {
         Temperature warmedTemperature = new Temperature(degrees+celsiusTemperature);
        return warmedTemperature;
    }

    /** Returns a NEW Temperature, `degrees` cooler. Must not modify this one. */
    public Temperature cooler(double degrees) {
        Temperature cooledTemperature= new Temperature(celsiusTemperature-degrees);
        return cooledTemperature;
    }
}
