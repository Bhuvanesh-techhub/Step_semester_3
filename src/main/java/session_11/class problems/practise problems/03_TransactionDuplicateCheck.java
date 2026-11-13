import java.util.HashSet;
import java.util.Set;

class TransactionDuplicateCheck {
    public static boolean hasDuplicate(String[] transactionIds) {
        Set<String> seen = new HashSet<>();
        for (String id : transactionIds) {
            if (!seen.add(id)) return true;
        }
        return false;
    }

    // Lower-memory alternative: compare each pair, O(n^2) time and O(1) extra space.
    public static boolean hasDuplicateBruteForce(String[] ids) {
        for (int i = 0; i < ids.length; i++)
            for (int j = i + 1; j < ids.length; j++)
                if (ids[i].equals(ids[j])) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(hasDuplicate(new String[]{"TXN1001", "TXN1002", "TXN1003", "TXN1002", "TXN1004"}));
        System.out.println(hasDuplicate(new String[]{"TXN2001", "TXN2002", "TXN2003"}));
    }
}
