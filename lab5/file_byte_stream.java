import java.io.*;
public class file_writer {
    public static void main(String[] args) {
        try(FileWriter fw=new FileWriter("output.txt");FileReader fr=new FileReader("input.txt"); ){
            System.out.println("Copying contents...\n");
            int ch;
            while((ch=fr.read())!=-1){
                fw.write(ch);
            } 
        }
        catch(Exception e){
            System.out.println("File not opened ! xD");
        }
    }
}
