package hash_tables.dast;

/**
 * A superclass for implementations of hash-sets implementing the hash_tables.dast.SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {

    /**
     * The default upper load factor of a newly created hash set
     */
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /**
     * The default lower load factor of a newly created hash set
     */
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**
     * Describes the default capacity of a newly created hash set
     */
    protected static int INITIAL_CAPACITY = 16;

    /**
     * The factor in which to increase the capacity in case the set should enlarge
     */
    protected double ENLARGEMENT_FACTOR = 2;

    /**
     * The factor in which to decrease the capacity in case the set should shrink
     */
    protected double SHRINKAGE_FACTOR = 0.5;

    /**
     * The amount of cells in the set
     */
    protected int capacity;

    private final float upperLoadFactor; // The upper load factor of the set
    private final float lowerLoadFactor; // The lower load factor of the set
    private int tableSize; // The amount of elements in the set

    /**
     * Constructs a new hash set with the defaults stated above
     */
    protected SimpleHashSet(){
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.tableSize = 0;
    }

    /**
     * Constructs a new hash set with according to the input parameters
     * @param upperLoadFactor The upper load factor to initialize the set with
     * @param lowerLoadFactor The lower load factor to initialize the set with
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.capacity = INITIAL_CAPACITY;
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        this.tableSize = 0;
    }

    /**
     * Prints for the analyzer tests
     */
    public void testPrints() {
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