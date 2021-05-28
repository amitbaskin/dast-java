package hash_tables.analyzer;

import hash_tables.dast.SimpleSetWrapper;

/**
 * Represents a test in the hash_tables.analyzer.SimpleSetPerformanceAnalyzer class.
 */
public class AnalyzerTests {
    private final SimpleSetWrapper myWrappedSet; // The set involved in the test
    private final String myString; // The string involved in the test
    private final String myName; // The name of the test
    private final String myType; // the type of the set involved in the test i.e. an indicator for the set that
    // should be tested

    /**
     * The default constructor for a test.
     * @param myWrappedSet The set involved
     * @param myName The name of the set
     * @param myType The type of the set involved
     * @param myString The string involved
     */
    public AnalyzerTests(SimpleSetWrapper myWrappedSet, String myName, String myType, String myString){
        this.myWrappedSet = myWrappedSet;
        this.myName = myName;
        this.myType = myType;
        this.myString = myString;
    }

    /**
     * Returns the set involved in the test.
     * @return the set involved in the test
     */
    public SimpleSetWrapper getMyWrappedSet(){
        return myWrappedSet;
    }

    /**
     * Returns the name of the test.
     * @return the name of the test
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Returns the type of the set involved in the test.
     * @return the type of the set involved in the test
     */
    public String getMyType() {
        return myType;
    }

    /**
     * Returns the string involved in the test.
     * @return the string involved in the test
     */
    public String getMyString(){
        return myString;
    }
}