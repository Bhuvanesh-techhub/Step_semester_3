import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 5: Travel Booking with a Common Fee
 * The booking fee is written once (BOOKING_FEE) in the abstract base class.
 * Subclasses only implement baseFare(). Change the fee = change one line.
 */
abstract class Booking {
    protected static final double BOOKING_FEE = 50;

    private final String mode;
    private final double distanceKm;

    protected Booking(String mode, double distanceKm) {
        this.mode = mode;
        this.distanceKm = distanceKm;
    }

    public String getMode() { return mode; }
    protected double getDistanceKm() { return distanceKm; }

    protected abstract double baseFare();

    public double total() { return baseFare() + BOOKING_FEE; }
}

class BusBooking extends Booking {
    BusBooking(double km) { super("BUS", km); }
    @Override protected double baseFare() { return 2 * getDistanceKm(); }
}

class TrainBooking extends Booking {
    TrainBooking(double km) { super("TRAIN", km); }
    @Override protected double baseFare() { return 1.5 * getDistanceKm(); }
}

class FlightBooking extends Booking {
    FlightBooking(double km) { super("FLIGHT", km); }
    @Override protected double baseFare() { return 2500 + 4 * getDistanceKm(); }
}

public class TravelBookingCommonFee {
    static Booking create(String mode, double km) {
        switch (mode.toUpperCase()) {
            case "BUS":    return new BusBooking(km);
            case "TRAIN":  return new TrainBooking(km);
            case "FLIGHT": return new FlightBooking(km);
            default: throw new IllegalArgumentException("Unknown mode: " + mode);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String mode = sc.next();
            double km = sc.nextDouble();
            Booking b = create(mode, km);
            System.out.println(String.format(Locale.US, "%s: %.2f", b.getMode(), b.total()));
        }
    }
}
