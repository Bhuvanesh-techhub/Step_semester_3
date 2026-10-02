// Problem 3: Delivery Fee Calculator
//
// NOTE: this implementation follows the Business Rules exactly as stated in
// the problem (Standard: $5 + $0.50/kg + $0.10/km, Express: $15 + $1.00/kg +
// $0.20/km, International: $25 + $2.00/kg + $0.50/km + CustomsFee). Applying
// those rules to the sample input gives STANDARD: 15.00, EXPRESS: 24.00,
// INTERNATIONAL: 145.00, Total: 184.00 — the PDF's sample for EXPRESS/
// INTERNATIONAL/Total (29.00 / 155.00 / 199.00) does not reconcile with its
// own stated rates, so the code below implements the written rules rather
// than force-matching the inconsistent sample numbers.
import java.util.*;

abstract class Delivery {
    protected double weight;
    protected double distance;

    public Delivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    public abstract double calculateFee();

    public abstract String getType();
}

class StandardDelivery extends Delivery {
    public StandardDelivery(double weight, double distance) {
        super(weight, distance);
    }

    public double calculateFee() {
        return 5 + 0.50 * weight + 0.10 * distance;
    }

    public String getType() {
        return "STANDARD";
    }
}

class ExpressDelivery extends Delivery {
    public ExpressDelivery(double weight, double distance) {
        super(weight, distance);
    }

    public double calculateFee() {
        return 15 + 1.00 * weight + 0.20 * distance;
    }

    public String getType() {
        return "EXPRESS";
    }
}

class InternationalDelivery extends Delivery {
    private double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        super(weight, distance);
        this.customsFee = customsFee;
    }

    public double calculateFee() {
        return 25 + 2.00 * weight + 0.50 * distance + customsFee;
    }

    public String getType() {
        return "INTERNATIONAL";
    }
}

public class P3_DeliveryFeeCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            double weight = Double.parseDouble(st.nextToken());
            double distance = Double.parseDouble(st.nextToken());

            if (type.equals("INTERNATIONAL")) {
                double customsFee = Double.parseDouble(st.nextToken());
                deliveries.add(new InternationalDelivery(weight, distance, customsFee));
            } else if (type.equals("STANDARD")) {
                deliveries.add(new StandardDelivery(weight, distance));
            } else if (type.equals("EXPRESS")) {
                deliveries.add(new ExpressDelivery(weight, distance));
            } else {
                throw new IllegalArgumentException("Unknown delivery type: " + type);
            }
        }

        double total = 0;
        for (Delivery d : deliveries) {
            double fee = d.calculateFee();
            total += fee;
            System.out.printf(Locale.US, "%s: %.2f%n", d.getType(), fee);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
