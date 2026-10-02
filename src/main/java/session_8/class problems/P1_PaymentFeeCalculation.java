// Problem 1: Payment System Fee Calculation
import java.util.*;

abstract class Payment {
    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract double calculateAdjustedAmount();

    public abstract String getType();
}

class CardPayment extends Payment {
    public CardPayment(double amount) {
        super(amount);
    }

    public double calculateAdjustedAmount() {
        return amount * 1.02; // 2% processing fee
    }

    public String getType() {
        return "CARD";
    }
}

class WalletPayment extends Payment {
    public WalletPayment(double amount) {
        super(amount);
    }

    public double calculateAdjustedAmount() {
        return amount * 1.01; // 1% processing fee
    }

    public String getType() {
        return "WALLET";
    }
}

class BankTransferPayment extends Payment {
    public BankTransferPayment(double amount) {
        super(amount);
    }

    public double calculateAdjustedAmount() {
        return amount; // no processing fee
    }

    public String getType() {
        return "BANKTRANSFER";
    }
}

public class P1_PaymentFeeCalculation {

    static Payment createPayment(String type, double amount) {
        switch (type) {
            case "CARD":
                return new CardPayment(amount);
            case "WALLET":
                return new WalletPayment(amount);
            case "BANKTRANSFER":
                return new BankTransferPayment(amount);
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Payment> transactions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            double amount = Double.parseDouble(st.nextToken());
            transactions.add(createPayment(type, amount));
        }

        double total = 0;
        for (Payment p : transactions) {
            double adjusted = p.calculateAdjustedAmount();
            total += adjusted;
            System.out.printf(Locale.US, "%s: %.2f%n", p.getType(), adjusted);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
