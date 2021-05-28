package hash_tables;

/**
 * Wraps a hash_tables.SimpleSet object so it would hold its original set, the type of class of its set, the data it
 * should be constructed from and the name of this data. It is used for the tests in the
 * hash_tables.SimpleSetPerformanceAnalyzer class.
 */
public class SimpleSetWrapper {

    private SimpleSet mySet;
    private final String myClass;
    private final String[] myData;
    private final String myDataName;

    /**
     * The default constructor.
     * @param mySet The object's set
     * @param myClass The class of the object's set
     * @param myData The data it should be constructed from
     * @param myDataName The name of the data it should be constructed from
     */
    SimpleSetWrapper(SimpleSet mySet, String myClass, String[] myData, String myDataName){
        this.mySet = mySet;
        this.myClass = myClass;
        this.myData = myData;
        this.myDataName = myDataName;
    }

    /**
     * Returns the object's set.
     * @return the object's set
     */
    public SimpleSet getMySet(){
        return mySet;
    }

    /**
     * Returns the class of the object's set.
     * @return the class of the object's set
     */
    public String getMyClass(){
        return myClass;
    }

    /**
     * Returns the data that the object should be constructed from.
     * @return the data that the object should be constructed from
     */
    public String[] getMyData(){
        return myData;
    }

    /**
     * Returns the name of the data that the object should be constructed from.
     * @return the data that the object should be constructed from
     */
    public String getMyDataName() {
        return myDataName;
    }

    /**
     * Sets the object's set to be the given set.
     * @param setToPut The set to set
     */
    public void setMySet(SimpleSet setToPut){
        this.mySet = setToPut;
    }

    /**
     * Constructs the object with its data even if it is already constructed.
     */
    public void constructMyDataAnyway(){
        AnalyzerFactory.putData(this.myClass, this);
    }

    /**
     * Constructs the object with its data only if it is not already constructed.
     */
    public void constructMyDataDependently(){
        if(this.mySet == null) {
            AnalyzerFactory.putData(this.myClass, this);
        }
    }
}