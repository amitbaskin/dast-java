package hash_tables;

/**
 * A superclass for implementations of hash-sets implementing the hash_tables.SimpleSet interface.
 */
abstract public class SimpleHashSet implements SimpleSet {

    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f; // The default upper load factor of a
    // newly created hash set
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f; // The default lower load factor of a
    // newly created hash set
    protected static int INITIAL_CAPACITY = 16; // Describes the default capacity of a newly created hash set

    protected double ENLARGEMENT_FACTOR = 2; // The factor in which to increase the capacity in case the
    // set should enlarge
    protected double SHRINKAGE_FACTOR = 0.5; // The factor in which to decrease the capacity in case the
    // set should shrink

    private int capacity; // The amount of cells in the set
    private final float upperLoadFactor; // The upper load factor of the set
    private final float lowerLoadFactor; // The lower load factor of the set
    private int tableSize; // The amount of elements in the set

    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet(){
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.tableSize = 0;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY given in lowerLoadFactor and upperLoadFactor.
     * @param upperLoadFactor The upper load factor to initialize the set with
     * @param lowerLoadFactor The lower load factor to initialize the set with
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.tableSize = 0;
    }

    public void testPrints() {
        // The following prints are for the analyzer tests
        if (size() % 10000 == 0) {
            System.out.println("size()");
            System.out.println(size());
            System.out.println("capacity()");
            System.out.println(capacity());
            System.out.println("(double) this.size() / this.capacity()");
            System.out.println((double) this.size() / this.capacity());
        }
    }

    /**
     * Returns the capacity of the set.
     * @return the capacity of the set
     */
    public int capacity(){
        return capacity;
    }

    /**
     * Sets the capacity of the set to be capacityToSet.
     * @param capacityToSet The capacity to set
     */
    protected void setCapacity(int capacityToSet){
        capacity = capacityToSet;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity.
     * @param index The index to clamp
     * @return the clamped index
     */
    protected abstract int clamp(int index);

    /**
     * Return the lower load factor of the set.
     * @return the lower load factor of the set
     */
    protected float getLowerLoadFactor(){
        return lowerLoadFactor;
    }

    /**
     * Return the upper load factor of the set.
     * @return the upper load factor of the set
     */
    protected float getUpperLoadFactor(){
        return upperLoadFactor;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public abstract boolean add(String newValue);

    /**
     * Looks for a specified value in the set.
     * @param searchVal Value to search for
     * @return true iff searchVal is found in the set
     */
    public abstract boolean contains(String searchVal);

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return true iff toDelete is found and deleted
     */
    public abstract boolean delete(String toDelete);

    /**
     * Returns the number of elements currently in the set.
     * @return the number of elements currently in the set
     */
    public int size(){
        return tableSize;
    }

    /**
     * Increases the size of the set by 1.
     */
    protected void increaseSize(){
        tableSize++;
    }

    /**
     * Decreases the size of the set by 1.
     */
    protected void decreaseSize(){
        tableSize--;
    }

    /**
     * Checks whether or not the size of the set has passed the upper load factor.
     * @return true iff the set should enlarge
     */
    protected boolean isEnlargeTable() {
        return (double) this.size() / this.capacity() > getUpperLoadFactor();
    }

    /**
     * Checks whether or not the size of the set has passed the lower load factor.
     * @return true iff the set should shrink
     */
    protected boolean isShrinkTable() {
        return (double) this.size() / this.capacity() < getLowerLoadFactor();
    }
}