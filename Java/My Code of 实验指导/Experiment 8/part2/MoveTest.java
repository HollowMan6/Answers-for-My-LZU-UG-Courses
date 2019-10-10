import java.io.*;
import java.nio.file.*;

class MoveTest{
    public static void main(String[] args) {
        Path source= Paths.get("horsebak.txt");
        Path target=Paths.get("D:/horsebak.txt");
        try{
            Files.move(source, target,StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("The file horsebak.txt mas moved to C:/horsebak.txt!");
    }
}