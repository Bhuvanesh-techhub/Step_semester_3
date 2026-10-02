// Problem 1: The Canteen Billing Counter
import java.util.*;

abstract class Customer {
    protected double billAmount;

    public Customer(double billAmount) {
        this.billAmount = billAmount;
    }

    // Each customer type knows how to compute its own final amount.
    public abstract double calculateFinalAmount();

    public abstract String getType();
}

class StudentCustomer extends Customer {
    public StudentCustomer(double billAmount) {
        super(billAmount);
    }

    public double calculateFinalAmount() {
        return billAmount * 0.90; // 10% discount
    }

    public String getType() {
        return "STUDENT";
    }
}

class StaffCustomer extends Customer {
    public StaffCustomer(double billAmount) {
        super(billAmount);
    }

    public double calculateFinalAmount() {
        return billAmount * 0.95; // 5% discount
    }

    public String getType() {
        return "STAFF";
    }
}

class GuestCustomer extends Customer {
    public GuestCustomer(double billAmount) {
        super(billAmount);
    }

    public double calculateFinalAmount() {
        return billAmount + 10; // full amount + service charge
    }

    public String getType() {
        return "GUEST";
    }
}

public class P1_CanteenBilling {

    // The only place that checks the type string, purely to build the right object.
    static Customer createCustomer(String type, double amount) {
        switch (type) {
            case "STUDENT":
                return new StudentCustomer(amount);
            case "STAFF":
                return new StaffCustomer(amount);
            case "GUEST":
                return new GuestCustomer(amount);
            default:
                throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Customer> bills = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            double amount = Double.parseDouble(st.nextToken());
            bills.add(createCustomer(type, amount));
        }

        double total = 0;
        // No if-else needed here: every Customer knows how to calculate itself.
        for (Customer c : bills) {
            double finalAmount = c.calculateFinalAmount();
            total += finalAmount;
            System.out.printf(Locale.US, "%s: %.2f%n", c.getType(), finalAmount);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
