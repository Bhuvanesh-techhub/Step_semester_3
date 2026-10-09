import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 2: Parcel Shipping Desk
 * "How much does shipping cost?" -> abstract class Parcel (every parcel answers).
 * "Can it be insured?"           -> interface Insurable (only some parcels).
 * Making another parcel insurable later = just implement Insurable on it.
 */
abstract class Parcel {
    private final String type;
    private final double weightKg;
    private final double declaredValue;

    protected Parcel(String type, double weightKg, double declaredValue) {
        this.type = type;
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    public abstract double shippingCharge();

    public String getType() { return type; }
    public double getWeightKg() { return weightKg; }
    public double declaredValue() { return declaredValue; }
}

interface Insurable {
    double INSURANCE_RATE = 0.02;

    double declaredValue();

    default double insurance() {
        return declaredValue() * INSURANCE_RATE;
    }
}

class StandardParcel extends Parcel {
    StandardParcel(double w, double v) { super("STANDARD", w, v); }

    @Override public double shippingCharge() { return 40 + 10 * getWeightKg(); }
}

class ExpressParcel extends Parcel implements Insurable {
    ExpressParcel(double w, double v) { super("EXPRESS", w, v); }

    @Override public double shippingCharge() { return 80 + 15 * getWeightKg(); }
}

class FragileParcel extends Parcel implements Insurable {
    private static final double HANDLING_FEE = 50;

    FragileParcel(double w, double v) { super("FRAGILE", w, v); }

    @Override public double shippingCharge() {
        // standard charge + handling fee
        return (40 + 10 * getWeightKg()) + HANDLING_FEE;
    }
}

public class ParcelShippingDesk {
    static Parcel create(String type, double w, double v) {
        switch (type.toUpperCase()) {
            case "STANDARD": return new StandardParcel(w, v);
            case "EXPRESS":  return new ExpressParcel(w, v);
            case "FRAGILE":  return new FragileParcel(w, v);
            default: throw new IllegalArgumentException("Unknown parcel type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double grand = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double w = sc.nextDouble();
            double v = sc.nextDouble();
            Parcel p = create(type, w, v);

            double charge = p.shippingCharge();
            double insurance = (p instanceof Insurable) ? ((Insurable) p).insurance() : 0.0;
            double total = charge + insurance;
            grand += total;

            System.out.println(String.format(Locale.US,
                    "%s: Charge=%.2f Insurance=%.2f Total=%.2f",
                    p.getType(), charge, insurance, total));
        }
        System.out.println(String.format(Locale.US, "Grand Total: %.2f", grand));
    }
}
