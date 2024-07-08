import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookLoan {

    private String bookCode, memberNumber;
    private LocalDateTime date;

    public BookLoan(String s, String s1, LocalDateTime parse) {
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // format the date

    public String getFormatDate(){
        String format = "yyy-mm-dd HH:mm:ss";
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern(format);
        return formatter.format(this.date);
    }

    @Override
    public  String toString(){
        return "Borrowed Book {"+"Book Code: "+bookCode+","
                +"Member Number: "+memberNumber+","+"Date: "+
                this.getFormatDate()+"}";
    }
}
