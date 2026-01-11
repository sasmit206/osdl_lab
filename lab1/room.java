// Create a base class named Room to represent general room details in a hotel. The
// class should contain data members such as room number, room type, and base
// price. Implement multiple constructors (constructor overloading) in the Room
// class to initialize room objects in different ways, such as:
// Initializing only the room number and type
// Initializing room number, type, and base price
// Create a derived class named DeluxeRoom that inherits from the Room class
// using single inheritance. The derived class should include additional data
// members such as free Wi-Fi availability and complimentary breakfast.
// Implement appropriate constructors in the derived class that invoke the base
// class constructors using the super keyword.
// Create a main class to instantiate objects of both Room and DeluxeRoom using
// different constructors and display the room details. This application should
// clearly illustrate constructor overloading and inheritance.
public class room {
    int rno;
    String rt;
    double bp;
    room(int rno,String rt){
        this.rno=rno;
        this.rt=rt;
        this.bp=-1;
    }
    room(int rno,String rt,double bp){
        this.rno=rno;
        this.rt=rt;
        this.bp=bp;
    }
    void display(){
        if(bp==-1){
        System.out.println(rno+rt);
        }
        else{System.out.println(rno+rt+bp);}
    }
    public static void main(String[] args){
        room r=new room(12,"Queen");
        DeluxeRoom d=new DeluxeRoom(13,"King",7900,"Yes","No");
        r.display();
        d.display();
    }
}
class DeluxeRoom extends room{
    String wifi,bkfst;
    DeluxeRoom(int rno,String rt,double bp,String wifi,String bkfst){
        super(rno,rt,bp);
        this.wifi=wifi;
        this.bkfst=bkfst;
    }
    void display(){
        System.out.println(rno+rt+bp+wifi+bkfst);
    }
}

