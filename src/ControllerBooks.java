import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerBooks {

    private static final String NAME_FILE = "books.txt";
    private static final String FIELD_SEPARATOR = ";";
    private static final String REGISTER_SEPARATOR = "\n";

public static void  bookDataRegister(){
    Scanner sc =new Scanner(System.in);
    System.out.println("Enter the book code: ");
    String code= sc.nextLine();
    System.out.println("Enter the book title: ");
    String title = sc.nextLine();
    System.out.println("Enter the author name: ");
    String author = sc.nextLine();
    System.out.println("This book is available? [true/false]");
    boolean available = Boolean.valueOf(sc.nextLine());
    System.out.println("Enter the location of the book");
    String location = sc.nextLine();
    System.out.println("Enter the signature of this book: ");
    String signature =sc.nextLine();
    ControllerBooks.register(new Book(code,title,author,location,signature,available));
    System.out.println("Successful registration");
}

    private static void register(Book book) {
    try{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NAME_FILE,true));
        bufferedWriter.write(book.getCode()
        +FIELD_SEPARATOR+book.getTitle()
        +FIELD_SEPARATOR+book.getAuthor()
        +FIELD_SEPARATOR+String.valueOf(book.isAvailable())
        +FIELD_SEPARATOR+book.getLocation()
        +FIELD_SEPARATOR+book.getSignature()
        +REGISTER_SEPARATOR);
        bufferedWriter.close();
    } catch (IOException e) {
        System.out.println("File writing error "+ e.getMessage());
    }
    }

    public static void safeBooks(ArrayList<Book> books){
    try{
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NAME_FILE,false));
        for(int x = 0; x < books.size();x++){
            Book book=books.get(x);
            bufferedWriter.write(book.getCode()
                    +FIELD_SEPARATOR+book.getTitle()
                    +FIELD_SEPARATOR+book.getAuthor()
                    +FIELD_SEPARATOR+String.valueOf(book.isAvailable())
                    +FIELD_SEPARATOR+book.getLocation()
                    +FIELD_SEPARATOR+book.getSignature()
                    +REGISTER_SEPARATOR);
        }
        bufferedWriter.close();
    } catch (IOException e) {
        System.out.println("Error writing the file: "+e.getMessage());
    }
    }
    public static ArrayList<Book> obtain(){
        ArrayList<Book> books=new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader= new FileReader(NAME_FILE);
            bufferedReader=new BufferedReader((fileReader));
            String line;
            while ((line=bufferedReader.readLine())!=null){
             String[] bookAsArray=line.split(FIELD_SEPARATOR);
             books.add(new Book(bookAsArray[0],bookAsArray[1],bookAsArray[2],bookAsArray[4],bookAsArray[5],Boolean.valueOf(bookAsArray[3])));
            }
        } catch (IOException e) {
            System.out.println("Exception reading the file: "+e.getMessage());
        } finally {
            try{
                if (fileReader != null){
                    fileReader.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("exception closing teh file "+e.getMessage());
            }
        }
        return books;
    }

    public static int searchByCode(String code,ArrayList<Book> books ){
    for (int x = 0; x < books.size(); x++){
        Book actualBook=books.get(x);
        if (actualBook.getCode().equals(code)){
            return x;
        }
    }
    return -1;
    }

    public static void markAsBorrow(String bookCode){
        ArrayList<Book> books = ControllerBooks.obtain();
        int index = ControllerBooks.searchByCode(bookCode,books);
        if (index == -1){
            return;
        }
        books.get(index).setAvailable(false);
        ControllerBooks.safeBooks(books);
    }

    public static void changeSignature(String bookCode,String newSignature,String newLocation){
        ArrayList<Book> books= ControllerBooks.obtain();
        int index = ControllerBooks.searchByCode(bookCode,books);
        if (index == -1){
            return;
        }
        books.get(index).setSignature(newSignature);
        books.get(index).setLocation(newLocation);
        ControllerBooks.safeBooks(books);

    }
    public static void requetDataToChangeSignature(){
    Scanner sc =new Scanner(System.in);
    Book book = ControllerBooks.printBooksAndRequestSelection();
    if (!book.isAvailable()){
        System.out.println("Couldn´t change a book that isn´t available");
        return;
    }
        System.out.println("Enter new location: ");
    String newLocation =sc.nextLine();
        System.out.println("Enter a new signature: ");
        String newSignature =sc.nextLine();
        ControllerBooks.changeSignature(book.getCode(),newSignature,newLocation);
        System.out.println("New location updated ");
    }
    public static void printBooks(ArrayList<Book> books){
        System.out.println(
                "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-20s|%-10s|%-30s|%-30s|\n", "No", "Code", "Title", "Author",
                "Available",
                "Location", "Signature");
        System.out.println(
                "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");
        for (int x = 0; x < books.size();x++){
            Book book = books.get(x);
            System.out.printf("|%-5d|%-10s|%-40s|%-20s|%-10s|%-30s|%-30s|\n", x + 1, book.getCode(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.isAvailable() ? "Yes" : "No",
                    book.getLocation(),
                    book.getSignature());
            System.out.println(
                    "+-----+----------+----------------------------------------+--------------------+----------+------------------------------+------------------------------+");

        }

    }

    public static Book printBooksAndRequestSelection(){
        ArrayList<Book> books = ControllerBooks.obtain();
        Scanner sc = new Scanner(System.in);
        while(true){
            ControllerBooks.printBooks(books);
            System.out.println("Enter the code: ");
            String code = sc.nextLine();
            int index = ControllerBooks.searchByCode(code,books);
            if (index == -1){
                System.out.println("Book with this code does not exist");
            }else {
                Book book = books.get(index);
                if (book.isAvailable()){
                    return book;
                }else {
                    System.out.println("Borrowed book");
                }
            }
        }
    }
}
