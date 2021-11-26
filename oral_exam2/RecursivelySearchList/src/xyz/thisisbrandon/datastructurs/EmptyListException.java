// Fig. 21.4: EmptyListException.java
// Class EmptyListException declaration.
package xyz.thisisbrandon.datastructurs;

/**
 * Class for creating a throwable exception for empty List objects.
 * @see List
 */
public class EmptyListException extends RuntimeException {

    /**
     * Default constructor for when no message is displayed
     */
    public EmptyListException() {
        this("List"); // call other EmptyListException constructor
    }

    /**
     * Constructor for exception class, allows specifying a more detailed
     * exception message to superclass RuntimeException.
     * @param name  name of the list throwing the exception.
     * @see         RuntimeException
     */
    public EmptyListException(String name) {
        super(name + " is empty"); // call superclass constructor
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
