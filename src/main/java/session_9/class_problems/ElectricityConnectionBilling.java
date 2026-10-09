import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 4: Electricity Connection Billing
 * Abstract Connection holds the shared "units" and an abstract bill().
 */
abstract class Connection {
    private final String type;
    private final double units;

    protected Connection(String type, double units) {
        this.type = type;
        this.units = units;
    }

    public String getType() { return type; }
    protected double getUnits() { return units; }

    public abstract double bill();
}

class HomeConnection extends Connection {
    HomeConnection(double units) { super("HOME", units); }

    @Override public double bill() {
        double u = getUnits();
        if (u <= 100) return 5 * u;
        return 5 * 100 + 7 * (u - 100);
    }
}

class ShopConnection extends Connection {
    private static final double FIXED_CHARGE = 100;

    ShopConnection(double units) { super("SHOP", units); }

    @Override public double bill() { return 8 * getUnits() + FIXED_CHARGE; }
}

class FactoryConnection extends Connection {
    private static final double MIN_BILL = 1000;

    FactoryConnection(double units) { super("FACTORY", units); }

    @Override public double bill() { return Math.max(6 * getUnits(), MIN_BILL); }
}

public class ElectricityConnectionBilling {
    static Connection create(String type, double units) {
        switch (type.toUpperCase()) {
            case "HOME":    return new HomeConnection(units);
            case "SHOP":    return new ShopConnection(units);
            case "FACTORY": return new FactoryConnection(units);
            default: throw new IllegalArgumentException("Unknown connection type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double units = sc.nextDouble();
            Connection c = create(type, units);
            double bill = c.bill();
            total += bill;
            System.out.println(String.format(Locale.US, "%s: %.2f", c.getType(), bill));
        }
        System.out.println(String.format(Locale.US, "Total: %.2f", total));
    }
}
