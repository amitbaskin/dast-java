package hash_tables.dast;

import java.util.Collection;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 */
public record CollectionFacadeSet(Collection<String> collection) implements SimpleSet {

    /**
     * Adds a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return false iff newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (!collection.contains(newValue)) {
            return collection.add(newValue);
        } else return false;
    }

    /**
     * Looks for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return true iff searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return true iff toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        boolean objectStatus = collection.remove(toDelete);
        while (collection.remove(toDelete)) {
            collection.remove(toDelete);
        }
        return objectStatus;
    }

    public int size() {
        return collection.size();
    }
}