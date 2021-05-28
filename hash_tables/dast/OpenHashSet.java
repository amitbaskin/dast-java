package hash_tables.dast;

import java.util.LinkedList;

/**
 * A hash-set based on chaining.
 */
public class OpenHashSet extends SimpleHashSet {

    private LinkedListWrapper[] hashTable; // The table that contains the items of the set

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity, upper load
     * factor and lower load factor.
     */
    public OpenHashSet() {
        super();
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
        initializeTable(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity.
     * @param upperLoadFactor  The upper load factor of the hash table
     * @param lowerLoadFactor The lower load factor of the hash table
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
        initializeTable(INITIAL_CAPACITY);
    }

    /**
     * Constructs a new, empty table with the specified load factors and capacity.
     * @param capacity The capacity of the set
     * @param upperLoadFactor The upper load factor of the hash table
     * @param lowerLoadFactor The lower load factor of the hash table
     */
    private OpenHashSet(int capacity, float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        hashTable = new LinkedListWrapper[capacity];
        this.capacity = capacity;
        initializeTable(capacity);
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity, upper load factor, and lower
     * load factor.
     * @param data Values to add to the set
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        hashTable = new LinkedListWrapper[INITIAL_CAPACITY];
        initializeTable(INITIAL_CAPACITY);
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

        // The following prints are for the analyzer tests

        testPrints();
        if (contains(newValue)) {
            return false;
        }

        else {
            increaseSize();

            if (isEnlargeTable()) {
                reconstructTable((int) (ENLARGEMENT_FACTOR * capacity()), null);
            }

            int cellIndex = clamp(newValue.hashCode());
            LinkedListWrapper cell = hashTable[cellIndex];
            LinkedList<java.lang.String> cellLinkedList = cell.getMyLinkedList();
            cellLinkedList.addFirst(newValue);

            return true;
        }
    }

    /**
     * Looks for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {

        if(size() == 0){
            return false;
        }

        int suspectedIndex = clamp(searchVal.hashCode());
        LinkedListWrapper suspectedCell = hashTable[suspectedIndex];
        LinkedList<java.lang.String> cellLinkedList = suspectedCell.getMyLinkedList();

        return cellLinkedList.contains(searchVal);
}

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        int suspectedIndex = clamp(toDelete.hashCode());
        LinkedList<java.lang.String> suspectedCell = hashTable[suspectedIndex].getMyLinkedList();

        if(suspectedCell.contains(toDelete)){
            decreaseSize();
        }

        else return false;

        if (isShrinkTable()) {
            int newCapacity = (int) (SHRINKAGE_FACTOR * capacity());
            if(newCapacity == 0){
                newCapacity = 1;
            }

            reconstructTable(newCapacity, toDelete);
            return true;
        }

        else return suspectedCell.remove(toDelete);
    }

    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index The index to clamp
     * @return the clamped index
     */
    @Override
    public int clamp ( int index){
        return index & (capacity() - 1);
    }

    /**
     * Reconstructs the hash table with the given capacity and without the item toIgnore.
     * @param capacity The new capacity
     * @param toIgnore The item to ignore while constructing
     */
    private void reconstructTable ( int capacity, String toIgnore){

        if(capacity == 0 & toIgnore == null){
            return;
        }

        OpenHashSet newHashSet = new OpenHashSet(capacity, getUpperLoadFactor(), getLowerLoadFactor());

        for (LinkedListWrapper cell : this.hashTable) {
            if(cell.getMyLinkedList().size() == 0){
                continue;
            }

            for (String currentValue : cell.getMyLinkedList()) {
                if (!currentValue.equals(toIgnore)) {
                    newHashSet.add(currentValue);
                }
            }
        }

        this.capacity = newHashSet.capacity();
        this.hashTable = newHashSet.hashTable;
    }

    /**
     * Initializes the hash table.
     * @param capacity The capacity to give the hash table
     */
    private void initializeTable(int capacity){
        for(int i=0; i<capacity; i++){
            hashTable[i] = new LinkedListWrapper();
        }
    }
}