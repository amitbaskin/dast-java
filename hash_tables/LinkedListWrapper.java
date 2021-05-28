package hash_tables;

import java.util.LinkedList;

/**
 * Wraps a LinkedList object in order to be able to have an array of linked lists.
 */
public class LinkedListWrapper {

    private final java.util.LinkedList<String> myLinkedList;

    /**
     * The default constructor.
     */
    LinkedListWrapper(){
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