// Fig. 21.3: List.java
// ListNode and List class declarations.
package xyz.thisisbrandon.datastructurs;

/**
 * Class representing a single node in linkedlist
 * @param <T>   Value type.
 * @see     List
 */
class ListNode<T> {
    // package access members; List can access these directly
    /**
     * Data stored in this node.
     */
    T data; // data for this node
    /**
     * Reference to next node in list, null represents current node
     * being the end of list.
     */
    ListNode<T> nextNode;

    /**
     * Constructor for node with no next node specified. Value stored
     * is required.
     * @param object    Value to be stored in node
     */
    ListNode(T object) {
        this(object, null);
    }

    /**
     * Constructor for node.
     * @param object    Value to be stored in node
     * @param node      Next node in list
     */
    ListNode(T object, ListNode<T> node) {
        data = object;
        nextNode = node;
    }

    /**
     * Returns data stored in node
     * @return  data member variable
     */
    T getData() {
        return data;
    }

    /**
     * Returns the next node after this in list
     * @return  nextNode member variable
     */
    ListNode<T> getNext() {
        return nextNode;
    }
} // end class ListNode<T>

/**
 * List object is a simple LinkedList
 * @param <T>   type of value stored in list
 * @see ListNode
 */
public class List<T> {

    /**
     * Reference to first node in list.
     */
    private ListNode<T> firstNode;
    /**
     * Reference to last node in list.
     */
    private ListNode<T> lastNode;
    /**
     * Name of list.
     */
    private String name; // string like "list" used in printing

    /**
     * Default constructor, creates an empty list with name "list"
     */
    public List() {
        this("list");
    }

    /**
     * Constructor, creates an empty list with specified name.
     * @param listName  the name of the list
     */
    public List(String listName) {
        name = listName;
        firstNode = lastNode = null;
    }

    /**
     * Appends new object to front of list.
     * @param insertItem    object to append
     */
    public void insertAtFront(T insertItem) {
        if (isEmpty()) // firstNode and lastNode refer to same object
            firstNode = lastNode = new ListNode<T>(insertItem);
        else // firstNode refers to new node
            firstNode = new ListNode<T>(insertItem, firstNode);
    }

    /**
     * Appends new object to end of list
     * @param insertItem    object to append
     */
    public void insertAtBack(T insertItem) {
        if (isEmpty()) // firstNode and lastNode refer to same object
            firstNode = lastNode = new ListNode<T>(insertItem);
        else // lastNode's nextNode refers to new node
            lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
    }

    /**
     * Searches for a node that contains the specified value starting at start node.
     * If no node contains this value search returns null.
     * @param value Value to search for
     * @return      Reference to actual value within List or null if not found.
     */
    public T search(T value) {
        return search(value, firstNode);
    }

    /**
     * Searches for a node that contains the specified value starting at a specified
     * node inside the linked list.
     * @param value         Value to search for
     * @param startPoint    Node at which search starts
     * @return              Reference to actual value within list or null if not found.
     */
    public T search(T value, ListNode<T> startPoint) {
        if (startPoint == null) {
            return null;
        } else if (startPoint.getData().equals(value)) {
            return startPoint.getData();
        } else {
            return search(value, startPoint.getNext());
        }
    }

    /**
     * Remove first node from list
     * @return  value stored within first node
     * @throws EmptyListException   Thrown when trying to remove an element from an empty list.
     */
    public T removeFromFront() throws EmptyListException {
        if (isEmpty()) // throw exception if List is empty
            throw new EmptyListException(name);

        T removedItem = firstNode.data; // retrieve data being removed

        // update references firstNode and lastNode
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            firstNode = firstNode.nextNode;

        return removedItem; // return removed node data
    } // end method removeFromFront

    /**
     * Removes last node in list
     * @return  value stored within last node
     * @throws EmptyListException   Thrown when trying to remove an element from an empty list.
     */
    public T removeFromBack() throws EmptyListException {
        if (isEmpty()) // throw exception if List is empty
            throw new EmptyListException(name);

        T removedItem = lastNode.data; // retrieve data being removed

        // update references firstNode and lastNode
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else // locate new last node
        {
            ListNode<T> current = firstNode;

            // loop while current node does not refer to lastNode
            while (current.nextNode != lastNode)
                current = current.nextNode;

            lastNode = current; // current is new lastNode
            current.nextNode = null;
        }

        return removedItem; // return removed node data
    }

    /**
     * Checks if list is empty
     * @return  true if empty, false is not empty
     */
    public boolean isEmpty() {
        return firstNode == null; // return true if list is empty
    }

    /**
     * Method for printing list contents to console
     */
    public void print() {
        if (isEmpty()) {
            System.out.printf("Empty %s%n", name);
            return;
        }

        System.out.printf("The %s is: ", name);
        ListNode<T> current = firstNode;

        // while not at end of list, output current node's data
        while (current != null) {
            System.out.printf("%s ", current.data);
            current = current.nextNode;
        }

        System.out.println();
    }
}

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
