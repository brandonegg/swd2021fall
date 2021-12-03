import org.junit.Test;

import java.util.HashMap;

/**
 * Easter test class
 */
public class TestEaster {
    private static HashMap<Integer, String> referenceDates = new HashMap<>();

    /**
     * Fills private member variable referenceDates with example date strings for several years
     * to compare with Easter class results.
     * These dates were obtained from: https://www.census.gov/srd/www/genhol/easter500.html
     */
    public void createReferenceDates() {
        //SOURCE: https://www.census.gov/srd/www/genhol/easter500.html
        referenceDates.put(1900, "April 15, 1900");
        referenceDates.put(1905, "April 23, 1905");
        referenceDates.put(1910, "March 27, 1910");
        referenceDates.put(1915, "April 4, 1915");
        referenceDates.put(1920, "April 4, 1920");
        referenceDates.put(1925, "April 12, 1925");
        referenceDates.put(1930, "April 20, 1930");
        referenceDates.put(1935, "April 21, 1935");
        referenceDates.put(1940, "March 24, 1940");
        referenceDates.put(1945, "April 1, 1945");
        referenceDates.put(1950, "April 9, 1950");
        referenceDates.put(1955, "April 10, 1955");
        referenceDates.put(1960, "April 17, 1960");
        referenceDates.put(1965, "April 18, 1965");
        referenceDates.put(1966, "April 10, 1966");
        referenceDates.put(1971, "April 11, 1971");
        referenceDates.put(1988, "April 3, 1988");
        referenceDates.put(1999, "April 4, 1999");
        referenceDates.put(2002, "March 31, 2002");
        referenceDates.put(2008, "March 23, 2008");
        referenceDates.put(2025, "April 20, 2025");
        referenceDates.put(2052, "April 21, 2052");
        referenceDates.put(2057, "April 22, 2057");
    }

    Easter tester;

    /**
     * Initialize new Easter object for testing
     * @param date  The date to be tested
     */
    public void init(int date) { tester = new Easter(date); }

    /**
     * Generates reference dates and compares their results with Easter toString() results.
     */
    @Test
    public void testDates() {
        createReferenceDates();

        for (Integer year : referenceDates.keySet()) {
            init(year);
            System.out.println("result: " + tester.toString() + " actual: " + referenceDates.get(year));
            assert tester.toString().equals(referenceDates.get(year));
        }
    }
}
