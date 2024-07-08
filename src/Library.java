import java.util.Scanner;

public class Library {

    public static Scanner sc = new Scanner(System.in);
    public static String selection= "";
    public static void menu(){
        while(!selection.equals("9")){
            System.out.println(
                    "1. Member registration\n" +
                    "2.Book registration\n" +
                    "3.Borrow registration\n" +
                    "4.Show Members\n" +
                    "5.Show books\n" +
                    "6.Show borrows\n" +
                    "7.Show Unreliable members\n" +
                    "8.Change location\n" +
                    "9.Exit"


            );
            selection=sc.nextLine();
            if (selection.equals("1")){
                MemberController.requestRegisterData();
            }
            if (selection.equals("2")){
                ControllerBooks.bookDataRegister();
            }
            if (selection.equals("3")){
                ControllerBookLoans.requestDataAndCreateBorrow();
            }
            if (selection.equals("4")){
                MemberController.printMembers(MemberController.obtain());
            }
            if (selection.equals("5")){
                ControllerBooks.printBooks(ControllerBooks.obtain());
            }
            if (selection.equals("6")){
                ControllerBookLoans.printBorrows(ControllerBookLoans.obtain());
            }
            if (selection.equals("7")){
                MemberController.printUnreliableMembers(MemberController.obtain());
            }
            if (selection.equals("8")){
                ControllerBooks.requetDataToChangeSignature();
            }

        }
    }

}
