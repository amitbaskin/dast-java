package hash_tables.dast;

import java.util.LinkedList;

/**
 * Wraps a LinkedList object in order to be able to have an array of linked lists.
 */
public class LinkedListWrapper {

    private final LinkedList<String> myLinkedList;

    /**
     * The default constructor.
     */
    public LinkedListWrapper(){
        this.myLinkedList = new LinkedList<>();
    }

    /**
     * Returns the linked list of the object.
     * @return the linked list of the object
     */
    protected LinkedList<String> getMyLinkedList(){
        return this.myLinkedList;
    }
}