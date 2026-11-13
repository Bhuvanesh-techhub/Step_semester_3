import java.util.Arrays;

public class Problem3_LibraryBookShelf {
    // Insertion sort: each new book is inserted into the sorted prefix.
    public static void shelf(int[] books) {
        for (int i = 1; i < books.length; i++) {
            int newBook = books[i];
            int j = i - 1;
            while (j >= 0 && books[j] > newBook) {
                books[j + 1] = books[j];
                j--;
            }
            books[j + 1] = newBook;
            System.out.println("After placing " + newBook + ": " + Arrays.toString(books));
        }
    }
    public static void main(String[] args) {
        shelf(new int[]{30, 10, 40, 20});
        System.out.println("Custom test:");
        shelf(new int[]{8, 3, 6, 1, 5});
    }
}
