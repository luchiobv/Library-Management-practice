public class Book {

    private String code, title,author,location, signature;
    private boolean available;

    public Book(String code,String title, String author,String location, String signature,boolean available){
        this.code =code;
        this.title=title;
        this.author=author;
        this.location=location;
        this.signature=signature;
        this.available=available;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Book{"+"Code: "+code+",Title: "+title+","+"Author: "
                +author+","+"Location: "+location+","+"Signature: "+signature+
                ","+"Avalible: "+available+"}";
    }
}
