// Problem 5: Public Transport Fare Calculator
import java.util.*;

abstract class Transport {
    protected double distance;

    public Transport(double distance) {
        this.distance = distance;
    }

    public abstract double calculateFare();

    public abstract String getType();
}

class Bus extends Transport {
    public Bus(double distance) {
        super(distance);
    }

    public double calculateFare() {
        double fare = 2 + 0.10 * distance;
        return Math.min(fare, 10.0);
    }

    public String getType() {
        return "BUS";
    }
}

class Train extends Transport {
    public Train(double distance) {
        super(distance);
    }

    public double calculateFare() {
        return 3 + 0.15 * distance;
    }

    public String getType() {
        return "TRAIN";
    }
}

class Metro extends Transport {
    private double peakHourFactor;

    public Metro(double distance, double peakHourFactor) {
        super(distance);
        this.peakHourFactor = peakHourFactor;
    }

    public double calculateFare() {
        return (1.50 + 0.20 * distance) * peakHourFactor;
    }

    public String getType() {
        return "METRO";
    }
}

public class P5_TransportFareCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Transport> journeys = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            double distance = Double.parseDouble(st.nextToken());

            if (type.equals("METRO")) {
                double peakHourFactor = Double.parseDouble(st.nextToken());
                journeys.add(new Metro(distance, peakHourFactor));
            } else if (type.equals("BUS")) {
                journeys.add(new Bus(distance));
            } else if (type.equals("TRAIN")) {
                journeys.add(new Train(distance));
            } else {
                throw new IllegalArgumentException("Unknown transport type: " + type);
            }
        }

        double total = 0;
        for (Transport t : journeys) {
            double fare = t.calculateFare();
            total += fare;
            System.out.printf(Locale.US, "%s: %.2f%n", t.getType(), fare);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
