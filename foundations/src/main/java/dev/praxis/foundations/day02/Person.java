package dev.praxis.foundations.day02;
import java.util.Objects;

/**
 * EXERCISE 1 — equals and hashCode, properly
 *
 * In Money you wrote these for a single field. Here there are TWO, and one of
 * them can be null. That changes things.
 *
 * Diagnostic Q6.
 */
public final class Person {

    private final String name;   // may be null
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String name() { return name; }
    public int age()     { return age; }

    /**
     * Two Person objects are equal when BOTH name and age match.
     *
     * Follow the standard shape you used in Money:
     *     identity check -> instanceof check -> compare fields
     *
     * The catch: `name` can be null, so `this.name.equals(other.name)` throws
     * a NullPointerException when this person has no name.
     *
     * java.util.Objects has a static method that compares two references and
     * handles null on BOTH sides. Find it. (Hint: look at Objects.equals.)
     */
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;

        if(!(o instanceof Person) ) return false;

        Person other = (Person)o;
        if(Objects.equals(this.name,other.name) && this.age==other.age) return true;
        return false;
    }

    /**
     * Must agree with equals(): if two Persons are equal, this MUST return the
     * same int for both.
     *
     * Combine BOTH fields — a hashCode built from only `age` would still be
     * "correct" but would put every 30-year-old in one bucket.
     *
     * java.util.Objects has a varargs method that hashes several values
     * together and handles nulls. Find it.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name,this.age);
    }

    /** Format: "Ada (36)". A null name renders as "unknown". */
    @Override
    public String toString() {
        String display = (this.name==null) ? "unknown":name;
         return display+" ("+this.age+")";
    }
}
