// Problem 2: The Campus Parking Charge Calculator
import java.util.*;

abstract class Vehicle {
    protected int hours;

    public Vehicle(int hours) {
        this.hours = hours;
    }

    public abstract double calculateCharge();

    public abstract String getType();
}

class Bike extends Vehicle {
    public Bike(int hours) {
        super(hours);
    }

    public double calculateCharge() {
        return hours * 10.0;
    }

    public String getType() {
        return "BIKE";
    }
}

class Car extends Vehicle {
    public Car(int hours) {
        super(hours);
    }

    public double calculateCharge() {
        if (hours <= 1) {
            return 30.0;
        }
        return 30.0 + (hours - 1) * 20.0;
    }

    public String getType() {
        return "CAR";
    }
}

class Truck extends Vehicle {
    public Truck(int hours) {
        super(hours);
    }

    public double calculateCharge() {
        double charge = hours * 50.0;
        return Math.max(charge, 100.0);
    }

    public String getType() {
        return "TRUCK";
    }
}

public class P2_ParkingCharge {

    static Vehicle createVehicle(String type, int hours) {
        switch (type) {
            case "BIKE":
                return new Bike(hours);
            case "CAR":
                return new Car(hours);
            case "TRUCK":
                return new Truck(hours);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            int hours = Integer.parseInt(st.nextToken());
            vehicles.add(createVehicle(type, hours));
        }

        double total = 0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            total += charge;
            System.out.printf(Locale.US, "%s: %.2f%n", v.getType(), charge);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
