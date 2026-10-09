import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 3: Library Late Fine Counter
 * Abstract LibraryItem mandates a fine() method; each item type supplies its rule.
 */
abstract class LibraryItem {
    private final String title;
    private final int daysLate;

    protected LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public String getTitle() { return title; }
    protected int getDaysLate() { return daysLate; }

    public abstract double fine();
}

class BookItem extends LibraryItem {
    BookItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double fine() { return 2.0 * getDaysLate(); }
}

class DvdItem extends LibraryItem {
    private static final double MAX_FINE = 50;

    DvdItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double fine() { return Math.min(5.0 * getDaysLate(), MAX_FINE); }
}

class MagazineItem extends LibraryItem {
    MagazineItem(String title, int daysLate) { super(title, daysLate); }
    @Override public double fine() { return 1.0 * getDaysLate(); }
}

public class LibraryLateFineCounter {
    static LibraryItem create(String type, String title, int days) {
        switch (type.toUpperCase()) {
            case "BOOK":     return new BookItem(title, days);
            case "DVD":      return new DvdItem(title, days);
            case "MAGAZINE": return new MagazineItem(title, days);
            default: throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String title = sc.next();
            int days = sc.nextInt();
            LibraryItem item = create(type, title, days);
            double fine = item.fine();
            total += fine;
            System.out.println(String.format(Locale.US, "%s: %.2f", item.getTitle(), fine));
        }
        System.out.println(String.format(Locale.US, "Total Fines: %.2f", total));
    }
}
