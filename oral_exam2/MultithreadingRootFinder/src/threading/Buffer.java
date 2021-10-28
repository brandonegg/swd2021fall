package threading;

// Fig. 23.9: threading.Buffer.java
// threading.Buffer interface specifies methods called by Producer and Consumer.

/**
 * Buffer interface implemented by our CircularBuffer
 * @param <T>   Generic type for data stored in circular buffer to be computed by threads
 * @see  CircularBuffer
 */
public interface Buffer<T> {

    /**
     * Required method for placing values into the buffer
     * @param value Value to be added to buffer
     * @throws InterruptedException
     */
    public void blockingPut(T value) throws InterruptedException;

    /**
     * Required for retrieving values from the buffer.
     * @return  The next value in the buffer
     * @throws InterruptedException
     */
    public T blockingGet() throws InterruptedException;
}
