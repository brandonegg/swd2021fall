public class Easter {
    private int year;
    private String dateStr;

    Easter(int year) {
        this.year = year;
        this.dateStr = generateDateStr(year);
    }

    public String toString() {
        return dateStr;
    }

    public void setYear(int year) {
        this.year = year;
        this.dateStr = generateDateStr(year);
    }

    private String getMonthFromInt(int monthNum) {
        switch (monthNum) {
            case 3: return "March";
            case 4: return "April";
        }
        return null;
    }

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

        return Integer.toString(p)+ " " + getMonthFromInt(n) + " " + Integer.toString(newYear);
    }
}
