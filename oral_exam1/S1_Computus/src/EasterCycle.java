import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

public class EasterCycle {
        public static  Map<String, Integer> countEasterDates() {
            Map<String, Integer> dateOccurenceCounters =  new LinkedHashMap<>();

            //The following ensures dates are stored in order
            //Generate march days:
            for (int i = 22; i<=31; i++) {
                dateOccurenceCounters.put(Integer.toString(i)+ " March", 0);
            }
            //Generate april days:
            for (int i = 1; i<=25; i++) {
                dateOccurenceCounters.put(Integer.toString(i)+ " April", 0);
            }

            for (int i = 1; i<=5700000; i++) {
                Easter easterDate = new Easter(i);
                String dayMonthStr = easterDate.getDate();

                if (dateOccurenceCounters.containsKey(dayMonthStr)) {
                    dateOccurenceCounters.replace(dayMonthStr, dateOccurenceCounters.get(dayMonthStr)+1);
                } else {
                    dateOccurenceCounters.put(dayMonthStr, 1);
                }
            }

            return dateOccurenceCounters;
        }

        public static void outputEasterDateCounts() {
            Map<String, Integer> dateOccurences = countEasterDates();

            for (String key : dateOccurences.keySet()) {
                System.out.println(key + " - " + Integer.toString(dateOccurences.get(key)));
            }
        }
}
