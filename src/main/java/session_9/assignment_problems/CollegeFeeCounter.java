import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 3: College Fee Counter
 * Abstract Student holds name + tuition rule. The BusUser interface carries
 * the transport fee (written once) and marks students who use the bus, so the
 * counter never has to check the student type by name.
 */
interface BusUser {
    double TRANSPORT_FEE = 12000;

    default double transportFee() {
        return TRANSPORT_FEE;
    }
}

abstract class Student {
    private final String name;

    protected Student(String name) { this.name = name; }

    public String getName() { return name; }

    protected abstract double baseFee();

    public double totalFee() {
        double fee = baseFee();
        if (this instanceof BusUser) {
            fee += ((BusUser) this).transportFee();
        }
        return fee;
    }
}

class DayScholar extends Student implements BusUser {
    DayScholar(String name) { super(name); }
    @Override protected double baseFee() { return 40000; }
}

class Hosteller extends Student {
    private static final double HOSTEL_FEE = 60000;

    Hosteller(String name) { super(name); }
    @Override protected double baseFee() { return 40000 + HOSTEL_FEE; }
}

class ScholarshipStudent extends Student implements BusUser {
    ScholarshipStudent(String name) { super(name); }
    @Override protected double baseFee() { return 40000 / 2.0; }
}

public class CollegeFeeCounter {
    static Student create(String type, String name) {
        switch (type.toUpperCase()) {
            case "DAY_SCHOLAR": return new DayScholar(name);
            case "HOSTELLER":   return new Hosteller(name);
            case "SCHOLAR":     return new ScholarshipStudent(name);
            default: throw new IllegalArgumentException("Unknown student type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;
        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            Student s = create(type, name);
            double fee = s.totalFee();
            total += fee;
            System.out.println(String.format(Locale.US, "%s: %.2f", s.getName(), fee));
        }
        System.out.println(String.format(Locale.US, "Total Collected: %.2f", total));
    }
}
