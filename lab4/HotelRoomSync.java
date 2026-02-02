package week4;
// Design and implement a Java-based hotel room management application that 
// simulates concurrent room booking and room release operations using 
// multiple threads. The system must ensure data consistency when multiple 
// customers attempt to book or release rooms simultaneously. A hotel has a limited 
// number of rooms. Multiple customer threads attempt to book rooms at the same 
// time. If no rooms are available, the booking thread must wait. 
// When a room is released by another thread, the waiting booking thread must be 
// notified and allowed to proceed.

class RoomBook{
    int rno=2;

    synchronized void book(String cname){
        System.out.println(Thread.currentThread().getName()+": Trying to book a room...");
        while(rno==0){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Exception Encountered ! ");
            }
        }
        rno--;
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
        }
        System.out.println(cname+" has booked a room successfully! :) | Room(s) left : "+rno);
    }
    
    synchronized void release(String cname){
        rno++;
        System.out.println(Thread.currentThread().getName()+" : is trying to release a room :( ");
        try {
            Thread.sleep(2500);
        } catch (Exception e) {
        }
        System.out.println(cname+"Has released the room| Room(s) left : "+rno);        
        notify();
    }
}


class Customer extends Thread{
    String cname;
    RoomBook rb;
    int ch;
    public Customer(String name,String cname,RoomBook rb,int ch) {
        super(name);
        this.cname=cname;
        this.rb=rb;
        this.ch=ch;
    }
    @Override
    public void run(){
        if(ch==1){rb.book(cname);}
        else if(ch==2){rb.release(cname);}
    }
}


public class HotelRoomSync {
    public static void main(String[] args) {
        System.out.println("Hello");
        RoomBook rb= new RoomBook();
        Customer c1=new Customer("Thread 1", "AmanB", rb,1);
        c1.start();
        Customer c2=new Customer("Thread 2", "RahulB", rb,1);
        c2.start();
        Customer c3=new Customer("Thread 3", "RamB", rb,1);
        c3.start();
        Customer c4=new Customer("Thread 4", "LaxmanR", rb,2);
        c4.start();

    }
}
