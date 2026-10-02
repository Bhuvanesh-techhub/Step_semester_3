// Problem 4: The Festival Bonus Calculator
import java.util.*;

abstract class Employee {
    protected String name;
    protected double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public abstract double calculateBonus();

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double salary) {
        super(name, salary);
    }

    public double calculateBonus() {
        return 2000.0;
    }
}

public class P4_FestivalBonus {

    static Employee createEmployee(String type, String name, double salary) {
        switch (type) {
            case "FULLTIME":
                return new FullTimeEmployee(name, salary);
            case "PARTTIME":
                return new PartTimeEmployee(name, salary);
            case "INTERN":
                return new InternEmployee(name, salary);
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            String name = st.nextToken();
            double salary = Double.parseDouble(st.nextToken());
            employees.add(createEmployee(type, name, salary));
        }

        double total = 0;
        for (Employee e : employees) {
            double bonus = e.calculateBonus();
            total += bonus;
            System.out.printf(Locale.US, "%s: %.2f%n", e.getName(), bonus);
        }

        System.out.printf(Locale.US, "Total Bonus: %.2f%n", total);
    }
}
