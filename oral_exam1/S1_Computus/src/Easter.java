/**
 * The class to represent an Easter date within a given year.
 * @see Easter
 */
public class Easter {
    private int year;
    private int month;
    private int day;
    private String dateStr;

    /**
     * Constructs an Easter object for a given year.
     * @param year  The year in which the date of easter shall be calculated
     */
    Easter(int year) {
        this.year = year;
        this.dateStr = generateDateStr(year);
    }

    /**
     * Year private member variable getter
     * @return Returns the year of the Easter object
     */
    public int getYear() {
        return year;
    }

    /**
     * Produces date string for easter year.
     * @return Returns string following format: "day month"
     */
    public String getDate() {
        return Integer.toString(day) + " " + getMonthFromInt(month);
    }

    /**
     * Returns string version of Easter object
     * @return Returns string following format: "Month day, year"
     */
    @Override
    public String toString() {
        return dateStr;
    }

    /**
     * Sets the year for the Easter object, method automatically recomputes
     * month and day of Easter for the new year.
     * @param year  New easter year
     */
    public void setYear(int year) {
        this.year = year;
        this.dateStr = generateDateStr(year);
    }

    /**
     * Private helper for converting the numeric month value to the correct month string.
     * @param monthNum  number representing month, 1 = January, etc.
     * @return          Respective month string
     */
    private String getMonthFromInt(int monthNum) {
        switch (monthNum) {
            case 3: return "March";
            case 4: return "April";
        }
        return null;
    }

    /**
     * Private helper for computing the actual date Easter falls on within the provided year,
     * this will also update month and day private member variables
     * @param newYear   The year the easter date will be computed for
     * @return          The date string for the given newYear, "Month day, year"
     */
    private String generateDateStr(int newYear) {
        int a,b,c,d,e,g,h,i,k,l,m,n,p;
        a = newYear%19;
        b = newYear/100;
        c = newYear % 100;
        d = b/4;
        e = b%4;
        g = (8*b+13)/25;
        h = (19*a + b - d - g +15)%30;
        i = c/4;
        k = c%4;
        l = (32+2*e+2*i-h-k)%7;
        m = (a+11*h+19*l)/433;
        n = (h+l-7*m+90)/25;
        p = (h+l-7*m+33*n+19)%32;

        day = p;
        month = n;
        return getMonthFromInt(n)+ " " + Integer.toString(p) + ", " + Integer.toString(newYear);
    }
}
