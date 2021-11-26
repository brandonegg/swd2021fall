import org.junit.Test;

import xyz.thisisbrandon.datastructurs.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * JUnit test class for testing the functionality of
 * the search method within the List class.
 * @see List
 */
public class TestSearchList {

    /**
     * Generates an Integer List object to be used in testSearchInteger()
     * @return  List object containing 3 test Integers, 10, 20, 30
     */
    public List<Integer> generateIntegerList() {
        List<Integer> linkedList = new List<Integer>();

        linkedList.insertAtBack(10);
        linkedList.insertAtBack(20);
        linkedList.insertAtBack(30);

        return linkedList;
    }

    /**
     * Generates a String list object to be used in testSearchString()
     * @return  List object containing 3 test Strings
     */
    public List<String> generateStringList() {
        List<String> linkedList = new List<String>();

        linkedList.insertAtBack("hello");
        linkedList.insertAtBack("goodbye");
        linkedList.insertAtBack("Hello world");

        return linkedList;
    }

    /**
     * Tests if search can successfully find the 3 elements in our test
     * integer list and if it successfully doesn't find a fourth number.
     */
    @Test
    public void testSearchInteger() {
        List<Integer> linkedList = generateIntegerList();

        assertEquals(Integer.valueOf(10), linkedList.search(10));
        assertEquals(Integer.valueOf(20), linkedList.search(20));
        assertEquals(Integer.valueOf(30), linkedList.search(30));
        assertNull(linkedList.search(40));
    }

    /**
     * Tests if search can successfully find the 3 elements in our test
     * String list and if it successfully doesn't find a fourth string with typo.
     */
    @Test
    public void testSearchString() {
        List<String> linkedList = generateStringList();

        assertEquals("hello", linkedList.search("hello"));
        assertEquals("goodbye", linkedList.search("goodbye"));
        assertEquals("Hello world", linkedList.search("Hello world"));
        assertNull(linkedList.search("hello world"));
    }

}
