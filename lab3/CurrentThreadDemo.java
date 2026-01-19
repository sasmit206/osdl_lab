

public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t=Thread.currentThread();
        System.out.println("Current thread: "+t.getName()+t.getPriority());
        t.setName("Lola");
        System.out.println("Post Name Change : "+t.getName()+t.getPriority());
        for(int i=0;i<5;i++){
            System.out.println(i+" , ");
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                System.out.println(t.getName()+"thread Interrupted");
                
            }
            
        }
        
        
    }
}
