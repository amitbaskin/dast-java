package hash_tables.analyzer;

import hash_tables.dast.SimpleSetWrapper;

/**
 * Has a main method that measures the run-times of constructing java's sets from given data and
 * searching for elements in it versus doing so with the sets I wrote for this ex4.
 * Running the main method should be done by inserting the desired tests in the arguments of the method.
 * Any number of arguments can be inserted, and a valid argument is a string which equals to one of the test
 * that are stated in the test fields . An argument which is not valid, shall be ignored.
 * In order to execute a test as was described in the ex4 description, then the correct argument for that
 * should be the string "test" and the number of the test from 1 to 6 right after the string.
 * Furthermore, in order to execute a specific test, i.e. of the tests between 1 to 6 but only for a
 * specific data structure, then it should be given as an argument such that the first word of the data
 * structure should be written and right after that the number of the test, e.g. "hash3" would mean to run
 * test number 3 on the HashSet data structure. In addition it is possible to run tests only by the data
 * structure, e.g. "open" would run all the tests on the hash_tables.dast.OpenHashSet data structure.
 */
public class SimpleSetPerformanceAnalyzer {

    // Represents tests by type
    private static final String OPEN_TYPE = "open";
    private static final String CLOSED_TYPE = "closed";
    private static final String TREE_TYPE = "tree";
    private static final String LINKED_TYPE = "linked";
    private static final String HASH_TYPE = "hash";

    // Names of general tests
    private static final String TEST1 = "test1";
    private static final String TEST2 = "test2";
    private static final String TEST3 = "test3";
    private static final String TEST4 = "test4";
    private static final String TEST5 = "test5";
    private static final String TEST6 = "test6";

    // names of specific tests
    private static final String OPEN1 = "open1";
    private static final String OPEN2 = "open2";
    private static final String OPEN3 = "open3";
    private static final String OPEN4 = "open4";
    private static final String OPEN5 = "open5";
    private static final String OPEN6 = "open6";
    private static final String CLOSED1 = "closed1";
    private static final String CLOSED2 = "closed2";
    private static final String CLOSED3 = "closed3";
    private static final String CLOSED4 = "closed4";
    private static final String CLOSED5 = "closed5";
    private static final String CLOSED6 = "closed6";
    private static final String TREE1 = "tree1";
    private static final String TREE2 = "tree2";
    private static final String TREE3 = "tree3";
    private static final String TREE4 = "tree4";
    private static final String TREE5 = "tree5";
    private static final String TREE6 = "tree6";
    private static final String LINKED1 = "linked1";
    private static final String LINKED2 = "linked2";
    private static final String LINKED3 = "linked3";
    private static final String LINKED4 = "linked4";
    private static final String LINKED5 = "linked5";
    private static final String LINKED6 = "linked6";
    private static final String HASH1 = "hash1";
    private static final String HASH2 = "hash2";
    private static final String HASH3 = "hash3";
    private static final String HASH4 = "hash4";
    private static final String HASH5 = "hash5";
    private static final String HASH6 = "hash6";

    // The strings for the contains tests
    private static final String STR1 = "hi";
    private static final String STR2 = "-13170890158";
    private static final String STR3 = "23";

    // Messages to print
    private static final String OPENING_MESSAGE = "For ";
    private static final String CONSTRUCTION_MESSAGE = " the time measurement of construction for ";
    private static final String COLON_MESSAGE = " is: ";
    private static final String CONTAIN_MESSAGE1 = " the time measurement of checking containment of ";
    private static final String CONTAIN_MESSAGE2 = " initialized with ";

    private static final double TIME_FACTOR = Math.pow(10, -6); // The factor to multiply nano seconds in
    // order to convert to ms

    private static final int ITERATIONS_AMOUNT1 = (int) (7 * Math.pow(10, 4)); // The amount of iterations
    // for the contains tests for all sets except LinkedList

    private static final int ITERATIONS_AMOUNT2 = (int) (7 * Math.pow(10, 3)); // The amount of iterations
    // for the contains tests of LinkedList

    // data1 and data2 which are mentioned in the comments below represent the data files that were given
    // in the ex4 supplied files

    private static final SimpleSetWrapper open1 = // Represents an hash_tables.dast.OpenHashSet with data1
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getOpenClass(),
                    AnalyzerFactory.getDataArray1(), AnalyzerFactory.getData1Name());

    private static final SimpleSetWrapper open2 = // Represents an hash_tables.dast.OpenHashSet with data2
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getOpenClass(),
            AnalyzerFactory.getDataArray2(), AnalyzerFactory.getData2Name());

    private static final SimpleSetWrapper closed1 = // Represents a hash_tables.dast.ClosedHashSet with data1
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getClosedClass(),
            AnalyzerFactory.getDataArray1(), AnalyzerFactory.getData1Name());

    private static final SimpleSetWrapper closed2 = // Represents a hash_tables.dast.ClosedHashSet with data2
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getClosedClass(),
            AnalyzerFactory.getDataArray2(), AnalyzerFactory.getData2Name());

    private static final SimpleSetWrapper tree1 = // Represents a TreeSet with data1
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getTreeClass(),
            AnalyzerFactory.getDataArray1(), AnalyzerFactory.getData1Name());

    private static final SimpleSetWrapper tree2 = // Represents a TreeSet with data2
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getTreeClass(),
            AnalyzerFactory.getDataArray2(), AnalyzerFactory.getData2Name());

    private static final SimpleSetWrapper linked1 = // Represents a LinkedList with data1
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getLinkedClass(),
            AnalyzerFactory.getDataArray1(), AnalyzerFactory.getData1Name());

    private static final SimpleSetWrapper linked2 = // Represents a LinkedList with data2
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getLinkedClass(),
            AnalyzerFactory.getDataArray2(), AnalyzerFactory.getData2Name());

    private static final SimpleSetWrapper hash1 = // Represents a HashSet with data1
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getHashClass(),
            AnalyzerFactory.getDataArray1(), AnalyzerFactory.getData1Name());

    private static final SimpleSetWrapper hash2 = // Represents a HashSet with data2
            AnalyzerFactory.initializeEmptyWrappedSet(AnalyzerFactory.getHashClass(),
            AnalyzerFactory.getDataArray2(), AnalyzerFactory.getData2Name());

    // The following hash_tables.analyzer.AnalyzerTests objects represents specific test. The fields are named according to the
    // set that is involved in the test together with the number of the test as was described the ex4
    // description
    private static final AnalyzerTests OPEN1_TEST = new AnalyzerTests(open1, OPEN1, OPEN_TYPE, null);
    private static final AnalyzerTests OPEN2_TEST = new AnalyzerTests(open2, OPEN2, OPEN_TYPE, null);
    private static final AnalyzerTests OPEN3_TEST = new AnalyzerTests(open1, OPEN3, OPEN_TYPE, STR1);
    private static final AnalyzerTests OPEN4_TEST = new AnalyzerTests(open1, OPEN4, OPEN_TYPE, STR2);
    private static final AnalyzerTests OPEN5_TEST = new AnalyzerTests(open2, OPEN5, OPEN_TYPE, STR3);
    private static final AnalyzerTests OPEN6_TEST = new AnalyzerTests(open2, OPEN6, OPEN_TYPE, STR1);
    private static final AnalyzerTests CLOSED1_TEST = new AnalyzerTests(closed1, CLOSED1, CLOSED_TYPE, null);
    private static final AnalyzerTests CLOSED2_TEST = new AnalyzerTests(closed2, CLOSED2, CLOSED_TYPE, null);
    private static final AnalyzerTests CLOSED3_TEST = new AnalyzerTests(closed1, CLOSED3, CLOSED_TYPE, STR1);
    private static final AnalyzerTests CLOSED4_TEST = new AnalyzerTests(closed1, CLOSED4, CLOSED_TYPE, STR2);
    private static final AnalyzerTests CLOSED5_TEST = new AnalyzerTests(closed2, CLOSED5, CLOSED_TYPE, STR3);
    private static final AnalyzerTests CLOSED6_TEST = new AnalyzerTests(closed2, CLOSED6, CLOSED_TYPE, STR1);
    private static final AnalyzerTests TREE1_TEST = new AnalyzerTests(tree1, TREE1, TREE_TYPE, null);
    private static final AnalyzerTests TREE2_TEST = new AnalyzerTests(tree2, TREE2, TREE_TYPE, null);
    private static final AnalyzerTests TREE3_TEST = new AnalyzerTests(tree1, TREE3, TREE_TYPE, STR1);
    private static final AnalyzerTests TREE4_TEST = new AnalyzerTests(tree1, TREE4, TREE_TYPE, STR2);
    private static final AnalyzerTests TREE5_TEST = new AnalyzerTests(tree2, TREE5, TREE_TYPE, STR3);
    private static final AnalyzerTests TREE6_TEST = new AnalyzerTests(tree2, TREE6, TREE_TYPE, STR1);
    private static final AnalyzerTests LINKED1_TEST = new AnalyzerTests(linked1, LINKED1, LINKED_TYPE, null);
    private static final AnalyzerTests LINKED2_TEST = new AnalyzerTests(linked2, LINKED2, LINKED_TYPE, null);
    private static final AnalyzerTests LINKED3_TEST = new AnalyzerTests(linked1, LINKED3, LINKED_TYPE, STR1);
    private static final AnalyzerTests LINKED4_TEST = new AnalyzerTests(linked1, LINKED4, LINKED_TYPE, STR2);
    private static final AnalyzerTests LINKED5_TEST = new AnalyzerTests(linked2, LINKED5, LINKED_TYPE, STR3);
    private static final AnalyzerTests LINKED6_TEST = new AnalyzerTests(linked2, LINKED6, LINKED_TYPE, STR1);
    private static final AnalyzerTests HASH1_TEST = new AnalyzerTests(hash1, HASH1, HASH_TYPE, null);
    private static final AnalyzerTests HASH2_TEST = new AnalyzerTests(hash2, HASH2, HASH_TYPE, null);
    private static final AnalyzerTests HASH3_TEST = new AnalyzerTests(hash1, HASH3, HASH_TYPE, STR1);
    private static final AnalyzerTests HASH4_TEST = new AnalyzerTests(hash1, HASH4, HASH_TYPE, STR2);
    private static final AnalyzerTests HASH5_TEST = new AnalyzerTests(hash2, HASH5, HASH_TYPE, STR3);
    private static final AnalyzerTests HASH6_TEST = new AnalyzerTests(hash2, HASH6, HASH_TYPE, STR1);

    // An array of all the specific tests that were announced above
    private static final AnalyzerTests[] allTests = {OPEN1_TEST, OPEN2_TEST, OPEN3_TEST, OPEN4_TEST,
            OPEN5_TEST, OPEN6_TEST, CLOSED1_TEST, CLOSED2_TEST, CLOSED3_TEST, CLOSED4_TEST, CLOSED5_TEST,
            CLOSED6_TEST, TREE1_TEST, TREE2_TEST, TREE3_TEST, TREE4_TEST, TREE5_TEST, TREE6_TEST,
            LINKED1_TEST, LINKED2_TEST, LINKED3_TEST, LINKED4_TEST, LINKED5_TEST, LINKED6_TEST, HASH1_TEST,
            HASH2_TEST, HASH3_TEST, HASH4_TEST, HASH5_TEST, HASH6_TEST};

    private static final SimpleSetWrapper[] linkedSets = {linked1, linked2}; // sets of LinkedLIst type
    private static final SimpleSetWrapper[] data1Sets = {open1, closed1, tree1, linked1, hash1}; // sets which
    // are connected to data1
    private static final SimpleSetWrapper[] data2Sets = {open2, closed2, tree2, linked2, hash2};

    /**
     * Returns time difference in ms.
     * @param before The time that was measured in an earlier stage
     * @return the time difference in ms
     */
    private static double msTimeMeasurement(long before){
        return TIME_FACTOR * (System.nanoTime() - before);
    }

    /**
     * Returns time difference in nano seconds.
     * @param before The time that was measured in an earlier stage
     * @return the time difference in nano seconds
     */
    private static long nanoTimeMeasurement(long before){
        return System.nanoTime() - before;
    }

    /**
     * Returns the amount of iterations required for the given set in the contains tests
     * @param wrappedSet The set to check the amount of iterations it should be tested in the contains
     *                   tests before measuring the time
     * @return the amount of iterations it should be tested in the contains tests before measuring the time
     */
    private static int iterationsAmount(SimpleSetWrapper wrappedSet){
        if(arrayContain(wrappedSet)){
            return ITERATIONS_AMOUNT2;
        }
        return ITERATIONS_AMOUNT1;
    }


    /**
     * Checks whether or not the given array contains the given object.
     * @param setToCheck The set to check if contained in the given array
     * @return true iff the given array contains the given set
     */
    private static boolean arrayContain(SimpleSetWrapper setToCheck){
        for(SimpleSetWrapper item : SimpleSetPerformanceAnalyzer.linkedSets){
            if(item == setToCheck){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints the appropriate message as the result of constructing a set, i.e. mentioning the set that was
     * constructed, the data it was constructed with and the time it took.
     * @param wrappedSet The set that was constructed with data
     * @param timeMeasurement The time the construction took
     */
    private static void printConstructionResult(SimpleSetWrapper wrappedSet, double timeMeasurement){
        System.out.println(OPENING_MESSAGE + wrappedSet.getMyClass() + CONSTRUCTION_MESSAGE +
                wrappedSet.getMyDataName() + COLON_MESSAGE + timeMeasurement);
    }

    /**
     * Prints the appropriate message as the result of checking if the given set contains the given string, i
     * .e. mentioning the set that was checked, the data it was checked with, the string the was checked
     * and the time it took.
     * @param wrappedSet The set that was checked
     * @param str The string that was checked
     * @param timeMeasurement The time it took
     */
    private static void printContainsResult(SimpleSetWrapper wrappedSet, String str, double timeMeasurement){
        System.out.println(OPENING_MESSAGE + wrappedSet.getMyClass() + CONTAIN_MESSAGE2 +
                wrappedSet.getMyDataName()+ CONTAIN_MESSAGE1 +str+ COLON_MESSAGE +timeMeasurement);
    }

    /**
     * Tests constructing a single given set.
     * @param wrappedSet The set to construct
     */
    private static void testConstructSingle(SimpleSetWrapper wrappedSet) {
        long timeBefore = System.nanoTime();
        wrappedSet.constructMyDataAnyway();
        double timeMeasurement = msTimeMeasurement(timeBefore);
        printConstructionResult(wrappedSet, timeMeasurement);
    }

    /**
     * Tests constructing multiple given sets.
     * @param wrappedSets The sets to construct
     */
    private static void testConstructMultiple(SimpleSetWrapper[] wrappedSets){
        for(SimpleSetWrapper singleWrappedSet : wrappedSets){
            testConstructSingle(singleWrappedSet);
        }
    }

    /**
     * Tests a single set for containing the given string.
     * @param wrappedSet The set to test
     * @param str the string to test
     */
    private static void testSingleContains(SimpleSetWrapper wrappedSet, String str) {
        wrappedSet.constructMyDataDependently();
        int iterationsAmount = iterationsAmount(wrappedSet);

        for (int i = 0; i < iterationsAmount; i++) {
            wrappedSet.getMySet().contains(str);
        }
        long timeBefore = System.nanoTime();
        wrappedSet.getMySet().contains(str);
        printContainsResult(wrappedSet, str, nanoTimeMeasurement(timeBefore));
    }

    /**
     * Tests multiple sets for containing the given string.
     * @param wrappedSets The sets to test
     * @param str The string to test
     */
    private static void testMultipleContains(SimpleSetWrapper[] wrappedSets, String str){
        for(SimpleSetWrapper singleWrappedSet : wrappedSets){
            testSingleContains(singleWrappedSet, str);
        }
    }

    /**
     * Executes a single analyzer test.
     * @param analyzerTest The analyzer test to execute
     */
    private static void executeSingleAnalyzerTest(AnalyzerTests analyzerTest){
        if(analyzerTest.getMyString() == null) {
            testConstructSingle(analyzerTest.getMyWrappedSet());
        }
        else{
            testSingleContains(analyzerTest.getMyWrappedSet(), analyzerTest.getMyString());
        }
    }

    /**
     * Executes the given test.
     * @param testRequired The test to execute
     */
    private static void executeTestRequired(String testRequired){
        for(AnalyzerTests analyzerTest : allTests){
            if(testRequired.equals(analyzerTest.getMyName())){
                executeSingleAnalyzerTest(analyzerTest);
            }
            if(testRequired.equals(analyzerTest.getMyType())){
                executeSingleAnalyzerTest(analyzerTest);
            }
        }
    }

    /**
     * Runs the tests according to the arguments given.
     * @param args The arguments representing the tests required
     */
    private static void runMain(String[] args){
        for(String testRequired : args){
            switch (testRequired) {
                case TEST1 -> testConstructMultiple(data1Sets);
                case TEST2 -> testConstructMultiple(data2Sets);
                case TEST3 -> testMultipleContains(data1Sets, STR1);
                case TEST4 -> testMultipleContains(data1Sets, STR2);
                case TEST5 -> testMultipleContains(data2Sets, STR3);
                case TEST6 -> testMultipleContains(data2Sets, STR1);
            }
        }

        for(String testRequired : args){
            executeTestRequired(testRequired);
        }
    }

    /**
     * The main method.
     * Running the main method should be done by inserting the desired tests in the arguments of the method.
     * Any number of arguments can be inserted, and a valid argument is a string which equals to one of the test
     * that are stated in the test fields . An argument which is not valid, shall be ignored.
     * In order to execute a test as was described in the ex4 description, then the correct argument for that
     * should be the string "test" and the number of the test from 1 to 6 right after the string.
     * Furthermore, in order to execute a specific test, i.e. of the tests between 1 to 6 but only for a
     * specific data structure, then it should be given as an argument such that the first word of the data
     * structure should be written and right after that the number of the test, e.g. "hash3" would mean to run
     * test number 3 on the HashSet data structure. In addition it is possible to run tests only by the data
     * structure, e.g. "open" would run all the tests on the hash_tables.dast.OpenHashSet data structure.
     * @param args The arguments given
     */
    public static void main(String[] args) {
        runMain(args);
    }
}