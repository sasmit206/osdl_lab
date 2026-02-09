import java.io.*;
import java.util.*;
public class CompleteFileDemo {
    static void byte_me(){
        System.out.println("Using Stream...");
        String fip,fop;
        Scanner in = new Scanner(System.in);
        System.out.println("I/P file from which data is to be copied: ");
        fip=in.nextLine();
        System.out.println("O/P file to which data is to be copied: ");
        fop=in.nextLine();
        try {
            FileInputStream inp=new FileInputStream(fip);
            int data;
            FileOutputStream out=new FileOutputStream(fop);
            while((data=inp.read())!=-1){
                out.write(data);
            }
        } 
        catch (Exception e){
        }
    }
    static void fyle_me(){
        System.out.println("Using File Reader...");
        String fip,fop;
        Scanner in = new Scanner(System.in);
        System.out.println("I/P file from which data is to be copied: ");
        fip=in.nextLine();
        System.out.println("O/P file to which data is to be copied: ");
        fop=in.nextLine();
        try {
            FileReader inp=new FileReader(fip);
            int data;
            FileWriter out=new FileWriter(fop);
            while((data=inp.read())!=-1){
                System.out.println((char)data);
                
                out.write(data);
            }
            inp.close();
                out.close();
        } 
        catch (Exception e){
        }
        
    }
    public static void main(String[] args) {
        int ch;
        System.out.println("Enter 1 for Byte Stream, 2 for File Reader/Writer");
        ch=in.nextInt();
        switch(ch){
          case 1:
            byte_me();
            break;
          case 2:
            fyle_me();
            break();
          default:
              System.out.println("Wrong Choice entered");
            
        }
    }
}
