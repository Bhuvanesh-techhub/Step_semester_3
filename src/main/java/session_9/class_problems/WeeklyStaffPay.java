import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 2: Weekly Staff Pay
 * Abstract Staff shares the name and forces every subclass to define pay().
 * Because it is abstract, a generic "staff member" can never be created.
 */
abstract class Staff {
    private final String name;

    protected Staff(String name) { this.name = name; }

    public String getName() { return name; }

    public abstract double pay();
}

class FullTimeStaff extends Staff {
    private final double weeklySalary;

    FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override public double pay() { return weeklySalary; }
}

class HourlyStaff extends Staff {
    private static final double REGULAR_HOURS = 40;
    private static final double OVERTIME_MULTIPLIER = 1.5;

    private final double hours, rate;

    HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override public double pay() {
        double regular = Math.min(hours, REGULAR_HOURS);
        double overtime = Math.max(0, hours - REGULAR_HOURS);
        return regular * rate + overtime * rate * OVERTIME_MULTIPLIER;
    }
}

class InternStaff extends Staff {
    private final double stipend;

    InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override public double pay() { return stipend; }
}

public class WeeklyStaffPay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            String name = sc.next();
            Staff s;
            switch (type.toUpperCase()) {
                case "FULLTIME":
                    s = new FullTimeStaff(name, sc.nextDouble());
                    break;
                case "HOURLY":
                    double hours = sc.nextDouble();
                    double rate = sc.nextDouble();
                    s = new HourlyStaff(name, hours, rate);
                    break;
                case "INTERN":
                    s = new InternStaff(name, sc.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown staff type: " + type);
            }
            double pay = s.pay();
            total += pay;
            System.out.println(String.format(Locale.US, "%s: %.2f", s.getName(), pay));
        }
        System.out.println(String.format(Locale.US, "Total Payroll: %.2f", total));
    }
}
