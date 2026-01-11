// Create a Book class with private data members including book ID, book title,
// author name, price, and availability status. Provide public setter methods to assign
// values to these data members and public getter methods to retrieve their values.
// Include validation in setter methods to ensure that the price is a positive value.
import java.util.*;
class book{
    private int bid;
    private double price;
    private String btitle,author,stat;
    void setter(int bid,double price,String btitle,String author,String stat){
        this.bid=bid;
        this.btitle=btitle;
        this.author=author;
        if(price>0)
            this.price=price;
        else    
            this.price=-1;
        this.stat=stat;
    }
    int getBID(){
        return bid;
    }
    String getBtitle(){
        return btitle;
    } 
    String getAuthor(){
        return author;
    } 
    double getPrice(){
        return price;
    } 
    String getStat(){
        return stat;
    } 
    public static void main(String args[]){
        Scanner in=new Scanner(System.in);
        book b= new book();
        b.setter(12,399,"The Namesake","Jhumpa Lahiri","Available");
        System.out.println("The Details: "+b.getBID()+b.getPrice()+b.getBtitle()+b.getStat());

    }
}