import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for computing and storing results of each date of Easter over an entire cycle (5700000 years)
 * @see Easter
 */
public class EasterCycle {

    /**
     * Generates a count of the number of occurrences of each easter date
     * over an entire cycle (5700000 years)
     * @return  Returns an ordered LinkedHashMap following (Date String, Number of occurrences)
     * @see     Easter
     */
    public static  Map<String, Integer> countEasterDates() {
        Map<String, Integer> dateOccurenceCounters = new LinkedHashMap<>();

        //The following ensures dates are stored in order
        //Generate march days:
        for (int i = 22; i <= 31; i++) {
            dateOccurenceCounters.put(Integer.toString(i) + " March", 0);
        }
        //Generate april days:
        for (int i = 1; i <= 25; i++) {
            dateOccurenceCounters.put(Integer.toString(i) + " April", 0);
        }

        for (int i = 1; i <= 5700000; i++) {
            Easter easterDate = new Easter(i);
            String dayMonthStr = easterDate.getDate();

            if (dateOccurenceCounters.containsKey(dayMonthStr)) {
                dateOccurenceCounters.replace(dayMonthStr, dateOccurenceCounters.get(dayMonthStr) + 1);
            } else {
                dateOccurenceCounters.put(dayMonthStr, 1);
            }
        }

        return dateOccurenceCounters;
    }

    /**
     * Used in EasterCalculator, takes results from cuntEasterDates() and outputs them to console
     * @see EasterCalculator
     */
    public static void outputEasterDateCounts() {
        Map<String, Integer> dateOccurences = countEasterDates();

        for (String key : dateOccurences.keySet()) {
            System.out.println(key + " - " + Integer.toString(dateOccurences.get(key)));
        }
    }
}
