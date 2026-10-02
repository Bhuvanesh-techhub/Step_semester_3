// Problem 2: Library Item Due Date Calculator
import java.time.LocalDate;
import java.util.*;

abstract class LibraryItem {
    // "Current date" is fixed for this exercise, as stated in the problem.
    protected static final LocalDate CURRENT_DATE = LocalDate.of(2023, 10, 26);

    protected String title;

    public LibraryItem(String title) {
        this.title = title;
    }

    public abstract int getBorrowDays();

    public LocalDate getDueDate() {
        return CURRENT_DATE.plusDays(getBorrowDays());
    }

    public String getTitle() {
        return title;
    }
}

class Book extends LibraryItem {
    public Book(String title) {
        super(title);
    }

    public int getBorrowDays() {
        return 14;
    }
}

class Dvd extends LibraryItem {
    public Dvd(String title) {
        super(title);
    }

    public int getBorrowDays() {
        return 7;
    }
}

class Magazine extends LibraryItem {
    public Magazine(String title) {
        super(title);
    }

    public int getBorrowDays() {
        return 3;
    }
}

public class P2_LibraryDueDate {

    static LibraryItem createItem(String type, String title) {
        switch (type) {
            case "BOOK":
                return new Book(title);
            case "DVD":
                return new Dvd(title);
            case "MAGAZINE":
                return new Magazine(title);
            default:
                throw new IllegalArgumentException("Unknown item type: " + type);
        }
    }

    // Splits "TYPE "Quoted Title"" into the type word and the title with
    // its surrounding quotes stripped off.
    static String[] parseTypeAndTitle(String line) {
        line = line.trim();
        int firstSpace = line.indexOf(' ');
        String type = line.substring(0, firstSpace);
        String rest = line.substring(firstSpace + 1).trim();
        String title = rest.substring(1, rest.length() - 1); // strip quotes
        return new String[] { type, title };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<LibraryItem> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] parts = parseTypeAndTitle(sc.nextLine());
            items.add(createItem(parts[0], parts[1]));
        }

        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + ": " + item.getDueDate());
        }
    }
}
