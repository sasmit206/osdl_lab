package hotelq4;

public class Room {
   int rno;
   double bt;
   Room(int rno, double bt) {
      this.rno = rno;
      this.bt = bt;
   }
   double calculateTariff(){
    return bt;
   }
   void displayRoomDetails(){
        System.out.println(rno+bt);
   }

   public static void main(String[] args) {
        StandardRoom s=new StandardRoom(34, 7500, 1, 2);
        s.calculateTariff();
        LuxuryRoom l=new LuxuryRoom(34, 9900, 1, 2,2);
        l.calculateTariff();
        s.provideBreakfast();
        l.provideWifi();
   }
}

interface Amenities {
    void provideWifi();
    void provideBreakfast();
}

class StandardRoom extends Room implements Amenities{
    int ac,am;
    StandardRoom(int rno,double bt,int ac,int am){
        super(rno,bt);
        this.ac=ac;
        this.am=am;
    }
    public void provideWifi(){
        System.out.println("Wifi Provided for Standard Room ;)");
    }
    public void provideBreakfast(){
        System.out.println("Breakfast Provided for Standard Room ;)");
    }
    @Override
    double calculateTariff(){
        System.out.println("Standard Room Cost:"+bt+ac*3000+am*3400);
        return bt+ac*3000+am*3400;
    }
}

class LuxuryRoom extends Room implements Amenities{
    int ac,am,p;
    LuxuryRoom(int rno,double bt,int ac,int am,int p){
        super(rno,bt);
        this.ac=ac;
        this.am=am;
        this.p=p;
    }
    @Override
    public void provideWifi(){
        System.out.println("Wifi Provided for Luxury Room ;)");
    }
    public void provideBreakfast(){
        System.out.println("Breakfast Provided for Luxury Room ;)");
    }
    double calculateTariff(){
        System.out.println("Luxury Room Cost:"+bt+ac*3000+am*3400+p*6900);
        return bt+ac*3000+am*3400+p*6900;
    }
}
