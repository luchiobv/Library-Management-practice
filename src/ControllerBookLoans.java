import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class ControllerBookLoans {

    private static final String NAME_FILE = "borrowers.txt";
    private static final String FIELD_SEPARATOR = ";";
    private static final String REGISTER_SEPARATOR = "\n";


    public static void register(BookLoan bookLoan) {
        ControllerBooks.markAsBorrow(bookLoan.getBookCode());
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NAME_FILE, true));
            bufferedWriter.write(bookLoan.getBookCode()
                    + FIELD_SEPARATOR + bookLoan.getMemberNumber()
                    + FIELD_SEPARATOR + bookLoan.getFormatDate()
                    + REGISTER_SEPARATOR);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing the file " + e.getMessage());
        }
    }

    public static ArrayList<BookLoan> obtain() {
        ArrayList<BookLoan> borrows = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(NAME_FILE);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] borrowAsArray = line.split(FIELD_SEPARATOR);
                borrows.add(new BookLoan(borrowAsArray[0], borrowAsArray[1], LocalDateTime.parse(borrowAsArray[2],
                        new DateTimeFormatterBuilder().parseCaseInsensitive()
                                .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter())));

            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception reading the file " + e.getMessage());
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("Exception closed the file " + e.getMessage());
            }
            return borrows;
        }
    }

    public static int quantityBooksBorrowed(String number, ArrayList<BookLoan> loans) {
        int quantity = 0;
        for (int x = 0; x < loans.size(); x++) {
            BookLoan bookLoan = loans.get(x);
            if (bookLoan.getMemberNumber().equals(number)) {
                quantity++;
            }
        }
        return quantity;
    }

    public static void printBorrows(ArrayList<BookLoan> bookLoans) {
        System.out.
                println("+-----+------------------------------+------------------------------+--------------------+");
        System.out
                .printf("|%-5s|%-30s|%-30s|%-20s|\n", "No", "Book code", "No. Member", "Date");
        System.out
                .println("+-----+------------------------------+------------------------------+--------------------+");
        for (int x = 0; x < bookLoans.size(); x++) {
            BookLoan bookLoan = bookLoans.get(x);
            System.out.printf("|%-5d|%-30s|%-30s|%-20s|\n", x, bookLoan.getBookCode(), bookLoan.getMemberNumber(),
                    bookLoan.getFormatDate());
            System.out.println(
                    "+-----+------------------------------+------------------------------+--------------------+");

        }

    }

    public static void requestDataAndCreateBorrow(){
        Book book = ControllerBooks.printBooksAndRequestSelection();
        Member member=MemberController.printMembersAndAskSelection();
        ControllerBookLoans.register(new BookLoan(book.getCode(), member.getNumber(), LocalDateTime.now()));
        System.out.println("Borrow successfully registered");
    }

}

