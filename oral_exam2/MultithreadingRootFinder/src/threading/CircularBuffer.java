package threading;

/**
 * CircularBuffer implements the Buffer interface to create a shared buffer for adding
 * and retrieving data between active threads.
 * @param <T>   Generic type of data stored in CircularBuffer
 * @see   Buffer
 */
public class CircularBuffer<T> implements Buffer<T> {

    /**
     * Array storing data currently in buffer
     */
    private final T[] buffer; // shared buffer

    /**
     * Number of cells occupied in buffer array
     */
    private int occupiedCells = 0; // count number of buffers used
    /**
     * Current index of the write head of the buffer
     */
    private int writeIndex = 0; // index of next element to write to
    /**
     * Current index of the read head of the buffer
     */
    private int readIndex = 0; // index of next element to read

    /**
     * Constructor for CircularBuffer, initializes size of buffer to parameter size.
     * @param size  Max size of buffer
     */
    public CircularBuffer(int size) {
        buffer = (T[]) new Object[size];
    }

    /**
     * Adds a value to the buffer, if buffer is full, wait until an opening has been created
     * from blockingGet then add value.
     * @param value Value to be added to buffer
     * @throws InterruptedException
     */
    public synchronized void blockingPut(T value)
            throws InterruptedException {
        // wait until buffer has space avaialble, then write value;
        // while no empty locations, place thread in waiting state
        while (occupiedCells == buffer.length) {
            //System.out.printf("Buffer is full. Producer waits.%n");
            wait(); // wait until a buffer cell is free
        }

        buffer[writeIndex] = value; // set new buffer value

        // update circular write index
        writeIndex = (writeIndex + 1) % buffer.length;

        ++occupiedCells; // one more buffer cell is full
        notifyAll(); // notify threads waiting to read from buffer
    }

    /**
     * Gets the next value in the buffer, if no next value in buffer wait until a new value has been
     * added from blockingPut.
     * @return  Next value at read index.
     * @throws InterruptedException
     */
    public synchronized T blockingGet() throws InterruptedException {
        // wait until buffer has data, then read value;
        // while no data to read, place thread in waiting state
        while (occupiedCells == 0) {
            //System.out.printf("Buffer is empty. Consumer waits.%n");
            wait(); // wait until a buffer cell is filled
        }

        T readValue = buffer[readIndex]; // read value from buffer

        // update circular read index
        readIndex = (readIndex + 1) % buffer.length;

        --occupiedCells; // one fewer buffer cells are occupied
        notifyAll(); // notify threads waiting to write to buffer

        return readValue;
    }

    /**
     * Returns whether the buffer is empty.
     * @return  True if there are no occupied cells in buffer.
     */
    public boolean isEmpty() {
        return (occupiedCells==0);
    }

    /**
     * Gets number of cells occupied in buffer.
     * @return  The number of cells occupied in buffer
     */
    public int getOccupiedCells() {
        return occupiedCells;
    }
}