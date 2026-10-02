// Problem 3: The Hostel Electricity Bill
import java.util.*;

abstract class Room {
    protected int units;

    public Room(int units) {
        this.units = units;
    }

    public abstract double calculateBill();

    public abstract String getType();
}

class SingleRoom extends Room {
    public SingleRoom(int units) {
        super(units);
    }

    public double calculateBill() {
        return units * 8.0;
    }

    public String getType() {
        return "SINGLE";
    }
}

class SharedRoom extends Room {
    private int occupants;

    public SharedRoom(int units, int occupants) {
        super(units);
        this.occupants = occupants;
    }

    public double calculateBill() {
        return (units * 6.0) / occupants;
    }

    public String getType() {
        return "SHARED";
    }
}

class ACRoom extends Room {
    public ACRoom(int units) {
        super(units);
    }

    public double calculateBill() {
        return units * 10.0 + 200.0;
    }

    public String getType() {
        return "AC";
    }
}

public class P3_HostelElectricityBill {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());

        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String type = st.nextToken();
            int units = Integer.parseInt(st.nextToken());

            // SHARED is the only type that carries the extra "occupants" value,
            // so it is the only one that needs it stored on the object.
            if (type.equals("SHARED")) {
                int occupants = Integer.parseInt(st.nextToken());
                rooms.add(new SharedRoom(units, occupants));
            } else if (type.equals("SINGLE")) {
                rooms.add(new SingleRoom(units));
            } else if (type.equals("AC")) {
                rooms.add(new ACRoom(units));
            } else {
                throw new IllegalArgumentException("Unknown room type: " + type);
            }
        }

        double total = 0;
        for (Room r : rooms) {
            double bill = r.calculateBill();
            total += bill;
            System.out.printf(Locale.US, "%s: %.2f%n", r.getType(), bill);
        }

        System.out.printf(Locale.US, "Total: %.2f%n", total);
    }
}
