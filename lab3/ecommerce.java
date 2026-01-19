/*
To use threads:
    1. extends Thread
    2. implements Runnable
*/

//WEEK3 Q2 - SASMIT 240911564 ITB 46

class validation extends Thread{
    String oname;
    validation(String oname){this.oname=oname;}
    @Override
    public void run(){
        for(int i=1;i<=5;i++){
            System.out.println(oname+" being validated at step no. "+i);
            try {
                Thread.sleep(3000);
            } 
            catch (InterruptedException e) {
                System.out.println("Thread was interrupted"); 
            }
        }
    }
}
class payment extends Thread{
    String fname;
    payment(String fname){this.fname=fname;}
    @Override
    public void run(){
        try {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted"); 
        }
        System.out.println(fname+ " payment processed");
    }
}

class shipped extends Thread{
    String oname;
    shipped(String oname){this.oname=oname;}
    @Override
    public void run(){
        try {
            Thread.sleep(6001);
        } 
        catch (InterruptedException e) {
            System.out.println("Thread was interrupted"); 
        }
        System.out.println(oname+ " shipped successfully !");
    }
}

public class ecommerce{
    public static void main(String[] args) throws InterruptedException {
        validation v1=new validation("Toys ");
        payment p1=new payment("Toys ");
        shipped s1= new shipped("Toys ");
        v1.start();
        v1.join();

        p1.start();
        p1.join();
    
        s1.start();
    }
    
}
