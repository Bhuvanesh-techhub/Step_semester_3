// M5. Employee and Company Information Management
// Note: class renamed to EmployeeCompany (instead of Employee) to avoid a
// duplicate-class name clash with the Employee class used in M3 within this
// same folder.
class EmployeeCompany {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public EmployeeCompany(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class M5_EmployeeCompany {
    public static void main(String[] args) {
        EmployeeCompany e1 = new EmployeeCompany("Ravi", 40000);
        EmployeeCompany e2 = new EmployeeCompany("Divya", 50000);
        EmployeeCompany e3 = new EmployeeCompany("Arjun", 45000);

        EmployeeCompany.printCompanyInfo();
    }
}
