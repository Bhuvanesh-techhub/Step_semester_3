// Problem 4. The Locker Code
class Locker {
    private String code;
    private final int lockerNumber;

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.code = initialCode;
    }

    public void changeCode(String currentCode, String newCode) {
        if (currentCode.equals(code)) {
            code = newCode;
            System.out.println("changeCode(" + currentCode + ", " + newCode + ") -> success");
        } else {
            System.out.println("changeCode(" + currentCode + ", " + newCode + ") -> rejected, code unchanged");
        }
    }
}

public class P4_Locker {
    public static void main(String[] args) {
        Locker l = new Locker(101, "1234");
        l.changeCode("1234", "5678");
        l.changeCode("0000", "9999");
    }
}
