package hash_tables.analyzer;

import hash_tables.dast.SimpleSetWrapper;

/**
 * Represents a test in the hash_tables.analyzer.SimpleSetPerformanceAnalyzer class.
 */
public record AnalyzerTests(SimpleSetWrapper myWrappedSet, String myName,
                            String myType, String myString) {

    /**
     * Returns the set involved in the test.
     *
     * @return the set involved in the test
     */
    public SimpleSetWrapper getMyWrappedSet() {
        return myWrappedSet;
    }

    /**
     * Returns the name of the test.
     *
     * @return the name of the test
     */
    public String getMyName() {
        return myName;
    }

    /**
     * Returns the type of the set involved in the test.
     *
     * @return the type of the set involved in the test
     */
    public String getMyType() {
        return myType;
    }

    /**
     * Returns the string involved in the test.
     *
     * @return the string involved in the test
     */
    public String getMyString() {
        return myString;
    }
}