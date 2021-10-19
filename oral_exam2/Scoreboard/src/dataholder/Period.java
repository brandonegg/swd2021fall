package dataholder;

public class Period {

    private final String name;
    private final int maxPeriod;
    private int amount;

    public Period(String name, int maxPeriod) {
        this.name = name;
        this.maxPeriod = maxPeriod;
        amount = 1;
    }

    public boolean increment() {
        if (amount+1 > maxPeriod) {
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

    @Override
    public String toString() {
        return name+": " + amount;
    }

}
