package cuaroma.com.cuaromavendor;

public class Order {

    String username;
    String itemname;
    String contactno;
    String quantity;

    public Order(){

    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Order(String username, String itemname, String contactno, String quantity) {
        this.username = username;
        this.itemname = itemname;
        this.quantity = quantity;
        this.contactno = contactno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

}