package dataholder;

public class Period {

    private final String name;
    private final int maxPeriod;
    private int length;
    private int amount;

    public Period(String name, int maxPeriod, int length) {
        this.name = name;
        this.maxPeriod = maxPeriod;
        this.length = length;
        amount = 1;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean increment() {
        if (amount+1 > maxPeriod) {
            amount++;
            return false;
        }
        amount++;
        return true;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public int getLength() { return length; }

    @Override
    public String toString() {
        String count = String.valueOf(amount);

        if (amount > maxPeriod) {
            count = "final";
        }
        return name+": " + count;
    }

}
