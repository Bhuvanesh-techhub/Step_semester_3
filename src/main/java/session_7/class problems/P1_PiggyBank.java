// Problem 1. The Piggy Bank
class PiggyBank {
    private double savings;
    private final String id;

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0;
    }

    public void deposit(double amount) {
        savings += amount;
    }

    public void withdraw(double amount) {
        if (amount > savings) {
            System.out.println("Withdraw rejected: insufficient savings");
        } else {
            savings -= amount;
        }
    }

    public double getSavings() {
        return savings;
    }
}

public class P1_PiggyBank {
    public static void main(String[] args) {
        PiggyBank pb = new PiggyBank("PB-1");
        System.out.println("Start -> savings = " + pb.getSavings());

        pb.deposit(100);
        System.out.println("After deposit(100) -> savings = " + pb.getSavings());

        pb.withdraw(30);
        System.out.println("After withdraw(30) -> savings = " + pb.getSavings());

        pb.withdraw(500);
        System.out.println("After withdraw(500) -> savings = " + pb.getSavings());
    }
}
