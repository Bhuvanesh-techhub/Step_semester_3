import java.util.Locale;
import java.util.Scanner;

/**
 * Problem 1: Garden Plot Area Report
 * Common base type Plot (owner + abstract area). The report loop only knows
 * about Plot, so a new shape = a new subclass; the report code never changes.
 */
abstract class Plot {
    private final String owner;

    protected Plot(String owner) { this.owner = owner; }

    public String getOwner() { return owner; }

    public abstract String shapeName();

    public abstract double area();
}

class CirclePlot extends Plot {
    private final double radius;

    CirclePlot(String owner, double radius) {
        super(owner);
        this.radius = radius;
    }

    @Override public String shapeName() { return "CIRCLE"; }
    @Override public double area() { return Math.PI * radius * radius; }
}

class RectanglePlot extends Plot {
    private final double length, width;

    RectanglePlot(String owner, double length, double width) {
        super(owner);
        this.length = length;
        this.width = width;
    }

    @Override public String shapeName() { return "RECTANGLE"; }
    @Override public double area() { return length * width; }
}

class TrianglePlot extends Plot {
    private final double base, height;

    TrianglePlot(String owner, double base, double height) {
        super(owner);
        this.base = base;
        this.height = height;
    }

    @Override public String shapeName() { return "TRIANGLE"; }
    @Override public double area() { return 0.5 * base * height; }
}

public class GardenPlotAreaReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Plot[] plots = new Plot[n];

        for (int i = 0; i < n; i++) {
            String shape = sc.next();
            String owner = sc.next();
            switch (shape.toUpperCase()) {
                case "CIRCLE":
                    plots[i] = new CirclePlot(owner, sc.nextDouble());
                    break;
                case "RECTANGLE":
                    plots[i] = new RectanglePlot(owner, sc.nextDouble(), sc.nextDouble());
                    break;
                case "TRIANGLE":
                    plots[i] = new TrianglePlot(owner, sc.nextDouble(), sc.nextDouble());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown shape: " + shape);
            }
        }

        // Report code: depends only on the Plot abstraction.
        double total = 0;
        for (Plot p : plots) {
            double a = p.area();
            total += a;
            System.out.println(String.format(Locale.US, "%s (%s): %.2f",
                    p.getOwner(), p.shapeName(), a));
        }
        System.out.println(String.format(Locale.US, "Total Area: %.2f", total));
    }
}
