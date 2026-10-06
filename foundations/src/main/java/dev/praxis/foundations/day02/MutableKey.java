package dev.praxis.foundations.day02;

import java.util.HashMap;
import java.util.Map;

/**
 * EXERCISE 3 — Why a mutable object must never be a HashMap key
 *
 * Person and Money are immutable, so this cannot happen to them. Tag below is
 * mutable on purpose, so you can watch it go wrong.
 *
 * This one is worth a journal entry — it is a real production bug that is very
 * hard to diagnose when you meet it in the wild.
 */
public final class MutableKey {

    /** MUTABLE. equals and hashCode both read `label`, which can change. */
    public static final class Tag {
        private String label;

        public Tag(String label) { this.label = label; }

        public void setLabel(String label) { this.label = label; }
        public String label() { return label; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tag other)) return false;
            return label.equals(other.label);
        }

        @Override
        public int hashCode() { return label.hashCode(); }
    }

    /**
     * Sanity check first. Put a Tag in as a key, look it up, no mutation.
     *
     *     Map<Tag, String> map = new HashMap<>();
     *     Tag key = new Tag("a");
     *     map.put(key, "value");
     *     return map.get(key);
     *
     * Return what get() gives back. This one behaves normally.
     */
    public static String lookupWithoutMutation() {
        Map<Tag,String> map = new HashMap<>();
        Tag key = new Tag("a");
        map.put(key,"value");
        return map.get(key);
    }

    /**
     * Now the same thing, but mutate the key AFTER putting it in:
     *
     *     map.put(key, "value");
     *     key.setLabel("b");          // the key object itself changes
     *     return map.get(key);        // look it up with THE VERY SAME OBJECT
     *
     * Note you are not even using a different key — it is the identical object.
     * Predict the answer. Then run it.
     */
    public static String lookupAfterMutatingKey() {
        Map<Tag,String> map = new HashMap<>();
        Tag key= new Tag("a");
        map.put(key,"value");
        key.setLabel("b");
        return map.get(key);
    }

    /**
     * After the mutation above, is the entry still IN the map at all?
     * Return map.size() after put-then-mutate.
     *
     * The entry you can no longer reach has not been removed. It is stranded —
     * occupying memory, findable by iterating, unreachable by get(). That is a
     * memory leak with extra steps.
     */
    public static int mapSizeAfterMutatingKey() {
        Map<Tag,String> map = new HashMap<>();
        Tag key= new Tag("a");
        map.put(key,"value");
        key.setLabel("b");
        return map.size();
    }
}
