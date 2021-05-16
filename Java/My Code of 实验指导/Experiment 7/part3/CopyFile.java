import java.io.*;
class CopyFile{
    public static void main(String[] args)throws IOException {
        FileInputStream fis=new FileInputStream("CopyFile.java");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        FileOutputStream fos = new FileOutputStream(new File("CopyFile.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        String line="";
        int count=1;
        while ((line=br.readLine())!=null) {
            bw.write(count+":    "+line+"\n");
            count++;
        }
        br.close();
        isr.close();
        fis.close();
        bw.close();
        osw.close();
        fos.close();
    }
}