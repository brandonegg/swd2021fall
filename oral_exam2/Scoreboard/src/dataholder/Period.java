package dataholder;

/**
 * Period is an object storing period related data such as name, max amount of periods, length of a period
 * and the current period the game is on.
 */
public class Period {

    /**
     * Name of the period.
     */
    private final String name;
    /**
     * Maximum period reached
     */
    private final int maxPeriod;
    /**
     * Length of a period (minutes)
     */
    private int length;
    /**
     * Current period
     */
    private int amount;

    /**
     * Class constructor, assigns amount to 1 for first period.
     * @param name      Name of period
     * @param maxPeriod Maximum period obtainable
     * @param length    Length of a period (minutes)
     */
    public Period(String name, int maxPeriod, int length) {
        this.name = name;
        this.maxPeriod = maxPeriod;
        this.length = length;
        amount = 1;
    }

    /**
     * Set the length of a period
     * @param length    New length of a period (methods)
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Increments the period amount, if max period has been passed, an invalid increment is
     * signified with a false return value.
     * @return  A boolean which is false when there is no remaining periods.
     */
    public boolean increment() {
        if (amount+1 > maxPeriod) {
            amount++;
            return false;
        }
        amount++;
        return true;
    }

    /**
     * Gets current period number
     * @return  int representing current period
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns name of the period
     * @return  String of period name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the max obtainable period
     * @return  int representing max period
     */
    public int getMaxPeriod() { return maxPeriod; }

    /**
     * Returns length of a period
     * @return  int representing length of period in minutes
     */
    public int getLength() { return length; }

    /**
     * Provides string format of the Period object
     * @return  String representing period, "name: current period"
     * @see String
     */
    @Override
    public String toString() {
        String count = String.valueOf(amount);

        if (amount > maxPeriod) {
            count = "final";
        }
        return name+": " + count;
    }

}
