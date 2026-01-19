class ThreadDemo implements Runnable {
    ThreadDemo(){
        Thread t=new Thread(this);
        System.out.println(t.getName());
        t.start();
    }
    public void run(){
        try{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                Thread.sleep(2000);
            }
        }
        catch(InterruptedException e){
            System.out.println("interrupted");
            
        }
    }
}
public class ThreadRunnableDemo{
    public static void main(String[] args) {
        ThreadDemo t=new ThreadDemo();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("main : "+i);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {System.out.println("Main thread interrupted");
        
        }
        
    }
}
