import java.util.Map;
import java.util.TreeMap;

public class EasterCycle {
        public static  Map<String, Integer> countEasterDates() {
            Map<String, Integer> dateOccurenceCounters =  new TreeMap<String, Integer>();

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
