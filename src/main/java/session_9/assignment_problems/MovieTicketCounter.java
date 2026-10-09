import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 1: Movie Ticket Counter
 * Abstract base class holds what every ticket shares (count + the single
 * convenience fee). Each seat type only supplies its own price.
 */
abstract class Ticket {
    // The convenience fee is written in exactly one place.
    protected static final double CONVENIENCE_FEE = 20;

    private final String seatName;
    private final int count;

    protected Ticket(String seatName, int count) {
        this.seatName = seatName;
        this.count = count;
    }

    protected abstract double pricePerTicket();

    public double amount() {
        return count * (pricePerTicket() + CONVENIENCE_FEE);
    }

    public String getSeatName() {
        return seatName;
    }
}

class RegularSeat extends Ticket {
    RegularSeat(int count) { super("REGULAR", count); }
    @Override protected double pricePerTicket() { return 150; }
}

class PremiumSeat extends Ticket {
    PremiumSeat(int count) { super("PREMIUM", count); }
    @Override protected double pricePerTicket() { return 250; }
}

class ReclinerSeat extends Ticket {
    ReclinerSeat(int count) { super("RECLINER", count); }
    @Override protected double pricePerTicket() { return 400; }
}

public class MovieTicketCounter {
    static Ticket create(String seat, int count) {
        switch (seat.toUpperCase()) {
            case "REGULAR":  return new RegularSeat(count);
            case "PREMIUM":  return new PremiumSeat(count);
            case "RECLINER": return new ReclinerSeat(count);
            default: throw new IllegalArgumentException("Unknown seat type: " + seat);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String seat = sc.next();
            int count = sc.nextInt();
            Ticket t = create(seat, count);
            double amt = t.amount();
            total += amt;
            System.out.println(String.format(Locale.US, "%s: %.2f", t.getSeatName(), amt));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}
