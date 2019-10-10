import java.io.*;
public class FileNameFilterExample{
    public static void main(String[] args){
        File folder=new File(args[0]);
        String[] list=folder.list(new FilenameFilter(){
            public boolean accept(File folder,String fileName){
                return fileName.endsWith(".java");
            }
        });
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }
    }
}