package hash_tables.analyzer;

import hash_tables.*;
import hash_tables.dast.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A factory for the sets involved in the hash_tables.analyzer.SimpleSetPerformanceAnalyzer class tests.
 */
public class AnalyzerFactory {

    // The names of the classes of the different sets
    private static final String OPEN_CLASS = "hash_tables.dast.OpenHashSet";
    private static final String CLOSED_CLASS = "hash_tables.dast.ClosedHashSet";
    private static final String TREE_CLASS = "TreeSet";
    private static final String LINKED_CLASS = "LinkedList";
    private static final String HASH_CLASS = "HashSet";

   // The data files from which the sets are constructed
    private static final String DATA1_FILE_NAME = "data1.txt";
    private static final String[] DATA_ARRAY1 = Utils.file2array(DATA1_FILE_NAME);
    private static final String DATA2_FILE_NAME = "data2.txt";
    private static final String[] DATA_ARRAY2 = Utils.file2array(DATA2_FILE_NAME);

    /**
     * Returns the name of the open hash set class.
     * @return the name of the open hash set class
     */
    public static String getOpenClass() {
        return OPEN_CLASS;
    }

    /**
     * Returns the name of the closed hash set class.
     * @return the name of the closed hash set class
     */
    public static String getClosedClass() {
        return CLOSED_CLASS;
    }

    /**
     * Returns the name of the tree set class.
     * @return the name of the tree set class
     */
    public static String getTreeClass() {
        return TREE_CLASS;
    }

    /**
     * Returns the name of the linked list class.
     * @return the name of the linked list class
     */
    public static String getLinkedClass() {
        return LINKED_CLASS;
    }

    /**
     * Returns the name of the hash set class.
     * @return the name of the hash set class
     */
    public static String getHashClass() {
        return HASH_CLASS;
    }

    /**
     * Returns data1Array.
     * @return data1Array
     */
    public static String[] getDataArray1() {
        return DATA_ARRAY1;
    }

    /**
     * Returns data2Array.
     * @return data2Array
     */
    public static String[] getDataArray2() {
        return DATA_ARRAY2;
    }

    /**
     * Returns data1 file name.
     * @return data1 file name.
     */
    public static String getData1Name() {
        return DATA1_FILE_NAME;
    }

    /**
     * Returns data2 file name.
     * @return data2 file name.
     */
    public static String getData2Name() {
        return DATA2_FILE_NAME;
    }

    /**
     * Adds the items in the given data array into the given set.
     * @param mySet The set to add the items to
     * @param dataArray The array to add the items from
     */
    private static void addItems(SimpleSet mySet, String[] dataArray) {
        for (String item : dataArray) {
            mySet.add(item);
        }
    }

    /**
     * Returns a java set according to the choice given.
     * @param choice The java set to return
     * @return The java set that was required by the choice given
     */
    private static CollectionFacadeSet javaSetFactory(String choice) {
        return switch (choice) {
            case TREE_CLASS -> new CollectionFacadeSet(new TreeSet<>());
            case LINKED_CLASS -> new CollectionFacadeSet(new LinkedList<>());
            case HASH_CLASS -> new CollectionFacadeSet(new HashSet<>());
            default -> null;
        };
    }

    /**
     * Initializes an empty wrapped set while its type is decided according to the choice given and its
     * data according to the data given.
     * @param choice The type of set to initialize
     * @param dataArray The data array to give the set object to hold
     * @param dataName The data file name that the set object should hold
     * @return the required wrapped empty set
     */
    public static SimpleSetWrapper initializeEmptyWrappedSet(String choice, String[] dataArray,
                                                             String dataName) {
        return switch (choice) {
            case OPEN_CLASS -> new SimpleSetWrapper(new OpenHashSet(), OPEN_CLASS, dataArray, dataName);
            case CLOSED_CLASS -> new SimpleSetWrapper(new ClosedHashSet(), CLOSED_CLASS, dataArray, dataName);
            case TREE_CLASS -> new SimpleSetWrapper(javaSetFactory(TREE_CLASS), TREE_CLASS, dataArray,
                     dataName);
            case LINKED_CLASS -> new SimpleSetWrapper(javaSetFactory(LINKED_CLASS), LINKED_CLASS,
                     dataArray, dataName);
            case HASH_CLASS -> new SimpleSetWrapper(javaSetFactory(HASH_CLASS), HASH_CLASS, dataArray,
                     dataName);
            default -> null;
        };
    }

    /**
     * Puts data to the given set according to the data the set object holds and the choice given indicates
     * which constructor to use.
     * @param choice The type of set to put the data into. It gives the information which constructor to use
     * @param wrappedSet The wrapped set to put the data into
     */
    public static void putData(String choice, SimpleSetWrapper wrappedSet) {

        switch (choice) {
            case OPEN_CLASS -> {
                SimpleSetWrapper openDataSet = new SimpleSetWrapper(new OpenHashSet(wrappedSet.getMyData()),
                        OPEN_CLASS, wrappedSet.getMyData(), wrappedSet.getMyDataName());
                wrappedSet.setMySet(openDataSet.getMySet());
            }
            case CLOSED_CLASS -> {
                SimpleSetWrapper closedDataSet =
                        new SimpleSetWrapper(new ClosedHashSet(wrappedSet.getMyData()),
                                CLOSED_CLASS, wrappedSet.getMyData(), wrappedSet.getMyDataName());
                wrappedSet.setMySet(closedDataSet.getMySet());
            }
            case TREE_CLASS -> {
                SimpleSet treeDataSet = new CollectionFacadeSet(new TreeSet<>());
                addItems(treeDataSet, wrappedSet.getMyData());
                wrappedSet.setMySet(treeDataSet);
            }
            case LINKED_CLASS -> {
                CollectionFacadeSet linkedDataSet = new CollectionFacadeSet(new LinkedList<>());
                addItems(linkedDataSet, wrappedSet.getMyData());
                wrappedSet.setMySet(linkedDataSet);
            }
            case HASH_CLASS -> {
                CollectionFacadeSet hashDataSet = new CollectionFacadeSet(new HashSet<>());
                addItems(hashDataSet, wrappedSet.getMyData());
                wrappedSet.setMySet(hashDataSet);
            }
        }
    }
}