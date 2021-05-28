package hash_tables.dast;

import java.lang.*;
import java.util.Arrays;

/**
 * A hash-set based on closed-hashing with quadratic probing.
 */
public class ClosedHashSet extends SimpleHashSet {

    private String[] hashTable; // The table that contains the items of the set
    private boolean[] deletionFlagTable;

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity, upper load
     * factor and lower load factor.
     */
    public ClosedHashSet() {
        super();
        hashTable = new String[INITIAL_CAPACITY];
        deletionFlagTable = new boolean[INITIAL_CAPACITY];
        initializeFlagsTable();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity.
     * @param upperLoadFactor  The upper load factor of the hash table
     * @param lowerLoadFactor The lower load factor of the hash table
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new String[INITIAL_CAPACITY];
        deletionFlagTable = new boolean[INITIAL_CAPACITY];
        initializeFlagsTable();
    }

    /**
     * Constructs a new, empty table with the specified load factors and capacity.
     * @param capacity The capacity of the set
     * @param upperLoadFactor The upper load factor of the hash table
     * @param lowerLoadFactor The lower load factor of the hash table
     */
    private ClosedHashSet(int capacity, float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new String[capacity];
        deletionFlagTable = new boolean[capacity];
        setCapacity(capacity);
        initializeFlagsTable();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity, upper load factor, and lower
     * load factor.
     * @param data Values to add to the set
     */
    public ClosedHashSet(String[] data) {
        super();
        deletionFlagTable = new boolean[INITIAL_CAPACITY];
        hashTable = new String[INITIAL_CAPACITY];
        initializeFlagsTable();
        for (String value : data) {
            this.add(value);
        }
    }

    /**
     * Adds a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {

        testPrints();
        if (newValue == null) {
            return false;
        }

        int index = containedIndex(newValue);
        if (index == -1) {
            return false;
        }

        increaseSize();

        if (isEnlargeTable()) {
            reconstructTable((int) (ENLARGEMENT_FACTOR * capacity()));
        }

        hashTable[index] = newValue;

        return true;
    }

    /**
     * Looks for a specified value in the set.
     * @param searchVal Value to search for
     * @return the index of the item in the set if it was found and -1 otherwise
     */
    private int containedIndex(String searchVal) {

        for (int i=0; i<=capacity()-1; i++) {

            int suspectedIndex = probingFunction(searchVal.hashCode(), i);
            String suspectedValue = hashTable[suspectedIndex];

            if (suspectedValue == null) {
                if (!deletionFlagTable[suspectedIndex]) {
                    return -1;
                } else {
                    continue;
                }
            }

            if (suspectedValue.equals(searchVal)) {
                return suspectedIndex;
            }
        }
        return -1;
    }

    /**
     * Looks for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return containedIndex(searchVal) != -1;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {

        int suspectedIndex = containedIndex(toDelete);

        if (suspectedIndex == -1) {
            return false;
        }

        hashTable[suspectedIndex] = null;
        deletionFlagTable[suspectedIndex] = true;
        decreaseSize();

        if (isShrinkTable()) {
            int newCapacity = (int) (SHRINKAGE_FACTOR * capacity());

            if (newCapacity > 0) {
                reconstructTable(newCapacity);
            }

            else {
                reconstructTable(1);
            }
        }

        else {
            hashTable[suspectedIndex] = null;
            deletionFlagTable[suspectedIndex] = true;
        }

        return true;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index The index to clamp
     * @return the clamped index
     */
    @Override
    public int clamp(int index) {

        int i = 0;
        while ((hashTable[probingFunction(index, i)] != null)) {
            i++;
        }
        return probingFunction(index, i);
    }

    /**
     * Returns an index within the hash table that corresponds to the given index using quadratic probing.
     * @param index The index to prob
     * @param i The integer variable for the probing
     * @return an index within the hash table that corresponds to the given index using quadratic probing.
     */
    private int probingFunction(int index, int i) {
        return (index + ((i + i*i) / 2)) & (capacity() - 1);
    }

    /**
     * Reconstructs the hash table with the given capacity
     * @param capacity The new capacity
     */
    private void reconstructTable(int capacity) {
        ClosedHashSet newHashSet = new ClosedHashSet(capacity, getUpperLoadFactor(), getLowerLoadFactor());

        for (String value : this.hashTable) {
            if (value != null) {
                newHashSet.add(value);
            }
        }
        this.setCapacity(newHashSet.capacity());
        this.hashTable = newHashSet.hashTable;
        this.deletionFlagTable = newHashSet.deletionFlagTable;
        initializeFlagsTable();
    }

    /**
     * Initializes the flag table of the hash table which indicates for each cell if an item has been
     * deleted from it.
     */
    private void initializeFlagsTable() {
        Arrays.fill(deletionFlagTable, false);
    }
}