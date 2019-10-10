//用java模拟dos常用磁盘管理命令
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class CMD {
    private static String[] supportCmds={"dir","copy","del","type","comp","help"};
    public static int findCmd(String c){
        int index=-1;
        for(int i=0;i<supportCmds.length;i++){
            if(c.equalsIgnoreCase(supportCmds[i])){ 
                 index=i;
                 break;
            }
        }
        return index;
    }
    public static void main(String[] args){
        System.out.println("Java 命令行磁盘文件管理微系统！");
        String cmd="";
        Scanner keyin=new Scanner(System.in);
        while(true){
            System.out.print(":");
            cmd=keyin.nextLine();
            if(cmd.equals("exit")) System.exit(0);
            String[] cmd_params=null;
            if(cmd!=null){
                cmd_params=cmd.split(" ");
            }
            int cmdindex=findCmd(cmd_params[0]);
            if(cmdindex!=-1){
                 switch(cmdindex){
                     case 0:doDir(cmd_params);break;
                     case 1:doCopy(cmd_params);break;                     
                     case 2:doDel(cmd_params);break;
                     case 3:doType(cmd_params);break;
                     case 4:doComp(cmd_params);break;
                     case 5:doHelp();break;
                     default:
                 }
            }else{
                 System.out.println("暂时不支持此命令！");
            }
        }
    }
    public static void doDir(String[] args){
        File currentdir;
        String filename;
        int fcount=0;
        if(args.length==1){
            filename=".";
        }else{
            filename=args[1];
        }
        currentdir=new File(filename);
        SimpleDateFormat datefmt=new SimpleDateFormat("yyyy/MM/dd  HH:mm");
        if(currentdir.isDirectory()) {
            String[] mydirs=currentdir.list();
            for(int i=0;i<mydirs.length;i++){
               File mfile=new File(filename+"/"+mydirs[i]);
               Date mtime=new Date(mfile.lastModified());
               System.out.print(datefmt.format(mtime));
               if(mfile.isFile())
               {   System.out.print("\t\t");
                   System.out.println(mfile.length()+"\t"+mydirs[i]);
                   fcount++;
               }
               else
               {   System.out.print("\t<DIR>\t");
                   System.out.println("\t"+mydirs[i]);
               }
            }
        }else{
             fcount++;
             Date mtime=new Date(currentdir.lastModified());
             System.out.print(datefmt.format(mtime));
             System.out.print("\t\t");
             System.out.println(currentdir.length()+"\t"+currentdir.getName());
        }
        System.out.println("文件数："+fcount);
    }
    public static void doCopy(String[] args){
        if(args.length!=3){
            System.out.println("Usage: copy filename1 filename2");
        }else{
            Path source=Paths.get(args[1]);
            Path target=Paths.get(args[2]);
            try {
                Files.copy(source,target,StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("The file has copied.");
        }
    }
    public static void doDel(String[] args){
            if(args.length!=2){
            System.out.println("Usage: del filename");
        }else{
            Path source=Paths.get(args[1]);
            try {
                Files.deleteIfExists(source);
            }catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("The file has deleted.");
        }
    }
    public static void doType(String[] args){
        String line=null;
        FileReader reader=null;
        BufferedReader br=null;
        if(args.length!=2){
            System.out.println("Usage: type filename");
        }
        try {
            reader=new FileReader(args[1]);
            br=new BufferedReader(reader);
            line=br.readLine();
            while(line!=null){
                  System.out.println(line);
                  line=br.readLine();
            }
        }catch(FileNotFoundException fe){
            System.out.println(fe.getMessage());
        }catch(IOException ioe){
            System.out.println("Error reading/writing file");
        }finally{
            try{
                reader.close();
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void doComp(String[] args){
        Path path1=Paths.get(args[1]);
        Path path2=Paths.get(args[2]);
        if(Files.notExists(path1)){
            System.out.println(path1.toString()+" not existed!");
        }
        if(Files.notExists(path2)){
            System.out.println(path2.toString()+" not existed!");
        }
        try { 
            if(Files.size(path1)!=Files.size(path2)) {
                 System.out.println("文件不相同!");
                 return;
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        try(InputStream inputStream1=Files.newInputStream(
            path1,StandardOpenOption.READ);
            InputStream inputStream2=Files.newInputStream(
            path2,StandardOpenOption.READ)) {
            int i1,i2;
            do {
               i1=inputStream1.read();
               i2=inputStream2.read();
               if(i1!=i2){
                   System.out.println("文件不相同!");
                   return;
               }
            }while(i1!=-1);
            System.out.println("文件相同!");
        }catch(IOException e){
            System.out.println("文件不相同!");
        }
    }
    public static void doHelp(){
        System.out.println("目前仅支持以下命令:");
        for(int i=0;i<supportCmds.length;i++){
            System.out.print(supportCmds[i]+"   ");
        }
        System.out.println("exit");
    }
}