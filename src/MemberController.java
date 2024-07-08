import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {

    private static final String NAME_FILE = "members.txt";
    private static final  String FIELD_SEPARATOR = ";";
    private static final String REGISTER_SEPARATOR = "\n";

    public static void  requestRegisterData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your membership number: ");
        String number = sc.nextLine();
        System.out.println("Enter your membership name: ");
        String name = sc.nextLine();
        System.out.println("Enter your membership address: ");
        String address = sc.nextLine();
        MemberController.register(new Member(number,name,address));
        System.out.println("Successful registration");
    }

    public static void register(Member member){
        try{
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(NAME_FILE,true));
            bufferedWriter.write(member.getNumber()+FIELD_SEPARATOR
                    +member.getName()+FIELD_SEPARATOR+member.getAddress()+REGISTER_SEPARATOR);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("File writing error: "+e.getMessage());
        }
    }

    public static ArrayList<Member> obtain() {
        ArrayList<Member> members = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(NAME_FILE);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] memberAsArray = line.split(FIELD_SEPARATOR);
                members.add(new Member(memberAsArray[0], memberAsArray[1], memberAsArray[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception reading file: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

            } catch (IOException e) {
                System.out.println("Exception closing the file: " + e.getMessage());
            }
        }
        return members;
    }

    public static void printMembers(ArrayList<Member> members){
        ArrayList<BookLoan> loans= ControllerBookLoans.obtain();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
        );
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. member", "Name", "Address",
                "Books Borrowed");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
        );
        for(int x =0; x < members.size(); x++){
            Member member = members.get(x);
            System.out.printf(
            "|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, member.getNumber(), member.getName(),
                    member.getAddress(), ControllerBookLoans.quantityBooksBorrowed(member.getNumber(), loans));
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
            );
        }
    }

    public static void printUnreliableMembers(ArrayList<Member> members){
        ArrayList<BookLoan> loans= ControllerBookLoans.obtain();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
        );
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. member", "Name", "Address",
                "Books Borrowed");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
        );
        for (int x = 0; x < members.size(); x++){
            Member member =members.get(x);
            int BookLoan = ControllerBookLoans.quantityBooksBorrowed(member.getNumber(), loans);
            if (BookLoan < 10){
                continue;
            }
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
            );
            System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, member.getNumber(),member.getName(),
                    member.getAddress(),"Books Borrowed");
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+"
            );
        }
    }

    public static int searchMemberByNumber(String number,ArrayList<Member> members){
        for (int x =0; x < members.size(); x++){
            Member member = members.get(x);
            if (member.getNumber().equals(number)){
                return x;
            }
        }
        return -1;
    }

    public static Member printMembersAndAskSelection(){
        ArrayList<Member> members= MemberController.obtain();
        Scanner sc = new Scanner(System.in);
        while (true){
            MemberController.printMembers(members);
            System.out.println("Enter membership number: ");
            String number = sc.nextLine();
            int index = MemberController.searchMemberByNumber(number,members);
            if (index == -1){
                System.out.println("Doesn´t exist a member with that number");
            }else {
                return members.get(index);
            }
        }
    }
}

