package hotelq3;

public class room {
   int rno;
   double bt;
   room(int rno, double bt) {
      this.rno = rno;
      this.bt = bt;
   }
   double calculateTarriff(){
    return bt;
   }

   public static void main(String[] args) {
        StandardRoom s=new StandardRoom(34, 7500, 1, 2);
        s.calculateTarriff();
        LuxuryRoom l=new LuxuryRoom(34, 9900, 1, 2,2);
        l.calculateTarriff();
   }
}
class StandardRoom extends room{
    int ac,am;
    StandardRoom(int rno,double bt,int ac,int am){
        super(rno,bt);
        this.ac=ac;
        this.am=am;
    }
    @Override
    double calculateTarriff(){
        System.out.println(bt+ac*3000+am*3400);
        return bt+ac*3000+am*3400;
    }
}

class LuxuryRoom extends room{
    int ac,am,p;
    LuxuryRoom(int rno,double bt,int ac,int am,int p){
        super(rno,bt);
        this.ac=ac;
        this.am=am;
        this.p=p;
    }
    @Override
    double calculateTarriff(){
        System.out.println(bt+ac*3000+am*3400+p*6900);
        return bt+ac*3000+am*3400+p*6900;
    }
}