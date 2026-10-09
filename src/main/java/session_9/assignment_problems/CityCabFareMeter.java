import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 4: City Cab Fare Meter
 * The minimum-fare rule lives once in the abstract Cab class (shared by all).
 * Night service is an ability -> NightService interface, implemented only by
 * Sedan and SUV. A new cab type opts in by implementing the interface.
 */
abstract class Cab {
    private static final double MIN_FARE = 100;

    private final String name;
    private final double km;

    protected Cab(String name, double km) {
        this.name = name;
        this.km = km;
    }

    protected abstract double ratePerKm();

    public String getName() { return name; }

    /** Day fare with the shared minimum-fare rule applied. */
    public double dayFare() {
        return Math.max(km * ratePerKm(), MIN_FARE);
    }
}

interface NightService {
    double NIGHT_SURCHARGE = 0.20;

    default double nightFare(double baseFare) {
        return baseFare * (1 + NIGHT_SURCHARGE);
    }
}

class MiniCab extends Cab {
    MiniCab(double km) { super("MINI", km); }
    @Override protected double ratePerKm() { return 10; }
}

class SedanCab extends Cab implements NightService {
    SedanCab(double km) { super("SEDAN", km); }
    @Override protected double ratePerKm() { return 14; }
}

class SuvCab extends Cab implements NightService {
    SuvCab(double km) { super("SUV", km); }
    @Override protected double ratePerKm() { return 18; }
}

public class CityCabFareMeter {
    static Cab create(String type, double km) {
        switch (type.toUpperCase()) {
            case "MINI":  return new MiniCab(km);
            case "SEDAN": return new SedanCab(km);
            case "SUV":   return new SuvCab(km);
            default: throw new IllegalArgumentException("Unknown cab type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double km = sc.nextDouble();
            String time = sc.next();
            Cab cab = create(type, km);

            double fare = cab.dayFare();
            if (time.equalsIgnoreCase("NIGHT")) {
                if (cab instanceof NightService) {
                    fare = ((NightService) cab).nightFare(fare);
                } else {
                    System.out.println(cab.getName() + ": night service not available");
                    continue;
                }
            }
            total += fare;
            System.out.println(String.format(Locale.US, "%s: %.2f", cab.getName(), fare));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}
