public class Member {
    private String number, name, address;

    public Member(String number, String name, String address){
        this.number=number;
        this.name=name;
        this.address=address;

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{"+"Number: "+number+","+"Name: "+name+","
                +"Address: "+address+"}";
    }
}
