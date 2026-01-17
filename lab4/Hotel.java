// Design and implement a Java-based hotel room management application that
// simulates concurrent room booking and room release operations using
// multiple threads. The system must ensure data consistency when multiple
// customers attempt to book or release rooms simultaneously. A hotel has a limited
// number of rooms. Multiple customer threads attempt to book rooms at the same
// time. If no rooms are available, the booking thread must wait.
// When a room is released by another thread, the waiting booking thread must be
// notified and allowed to proceed.

import java.util.Scanner;

class HotelRoomManagement{
    int avlRoom=0;
    public HotelRoomManagement(int r){this.avlRoom=r;}
    synchronized void bookRoom(String custName){
        System.out.println(custName+"is trying to book a room..");
        while(avlRoom==0){
            System.out.println(custName+"is waiting..");
            try{
                wait();
            }
            catch(InterruptedException e){
                System.out.println("Thread Interrupted");
                
            }
        
        avlRoom--;
        System.out.println(custName+" booked the Room Sucessfully !");
    }
    synchronized void releaseRoom(String custName){
        System.out.println(custName+"relased the Room");   
        avlRoom++;
         notify();
        
    }
    public static void main(String args[]){

    }
}

class Customer extends Thread{
    private HotelRoomManagement h;
    private boolean isBook;
    private String name;
    Customer(boolean isBook,String name,HotelRoomManagement h){
        super(name);
        this.isBook=isBook;
        this.h=h;
    }
    @Override
    public void run(){
        if(isBook){h.bookRoom(getName());}
        else{h.releaseRoom(getName());}
    }
}

public class Hotel{
    public static void main(String args[]){
        HotelRoomManagement h= new HotelRoomManagement(1);
        Customer c1= new Customer(true, "Ola", h);
        Customer c2=new Customer(false, "Negaros", h);
        c1.start();
        c2.start();

        
    }
}