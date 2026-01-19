/*
To use threads:
    1. extends Thread
    2. implements Runnable
*/

//WEEK3 Q1 - SASMIT 240911564 ITB 46

class room_cleaning extends Thread{
    String rno;
    room_cleaning(String rno){this.rno=rno;}
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(rno+" being cleaned at step no. "+i);
            try {
                Thread.sleep(3000);
            } 
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted"); 
            }
        }
    }
}
class food_delivery extends Thread{
    String fname;
    food_delivery(String fname){this.fname=fname;}
    @Override
    public void run(){
        try {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted"); 
        }
        System.out.println(fname+ " is delivered");
    }
}

class maintenance extends Thread{
    String mname;
    maintenance(String mname){this.mname=mname;}
    @Override
    public void run(){
        try {
            Thread.sleep(6001);
        } 
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted"); 
        }
        System.out.println(mname+ " maintenance performed");
    }
}

public class hotel_room_mgmt_sys{
    public static void main(String[] args) {
        room_cleaning r1=new room_cleaning("B14 : 313");
        food_delivery f1=new food_delivery("chicken");
        r1.start();
        f1.start();
        maintenance m1= new maintenance("Fix pipes");
        m1.start();
    }
    
}
