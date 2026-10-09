import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 5: Home Appliance Energy Report
 * Abstract Appliance: what all share (power rating -> units, cost).
 * SaverMode interface: only AC and Washer can do it; the report asks
 * "instanceof SaverMode" instead of a chain of if-else on appliance names.
 */
abstract class Appliance {
    protected static final double RATE_PER_UNIT = 8;

    private final String name;

    protected Appliance(String name) { this.name = name; }

    public String getName() { return name; }

    protected abstract double powerWatts();

    public double units(double hours) {
        return powerWatts() * hours / 1000.0;
    }

    public double cost(double units) {
        return units * RATE_PER_UNIT;
    }
}

interface SaverMode {
    double SAVING = 0.25;

    default double applySaver(double units) {
        return units * (1 - SAVING);
    }
}

class Fridge extends Appliance {
    Fridge() { super("FRIDGE"); }
    @Override protected double powerWatts() { return 150; }
}

class AirConditioner extends Appliance implements SaverMode {
    AirConditioner() { super("AC"); }
    @Override protected double powerWatts() { return 1500; }
}

class Television extends Appliance {
    Television() { super("TV"); }
    @Override protected double powerWatts() { return 100; }
}

class WashingMachine extends Appliance implements SaverMode {
    WashingMachine() { super("WASHER"); }
    @Override protected double powerWatts() { return 500; }
}

public class ApplianceEnergyReport {
    static Appliance create(String name) {
        switch (name.toUpperCase()) {
            case "FRIDGE": return new Fridge();
            case "AC":     return new AirConditioner();
            case "TV":     return new Television();
            case "WASHER": return new WashingMachine();
            default: throw new IllegalArgumentException("Unknown appliance: " + name);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        double totalCost = 0;
        int read = 0;
        while (read < n && sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            read++;

            String[] parts = line.split("\\s+");
            Appliance a = create(parts[0]);
            double hours = Double.parseDouble(parts[1]);
            boolean saver = parts.length > 2 && parts[2].equalsIgnoreCase("SAVER");

            double units = a.units(hours);
            if (saver) {
                if (a instanceof SaverMode) {
                    units = ((SaverMode) a).applySaver(units);
                } else {
                    System.out.println(a.getName() + ": saver mode not supported");
                    continue;
                }
            }
            double cost = a.cost(units);
            totalCost += cost;
            System.out.println(String.format(Locale.US, "%s: Units=%.2f Cost=%.2f",
                    a.getName(), units, cost));
        }
        System.out.println(String.format(Locale.US, "Total Cost: %.2f", totalCost));
    }
}
