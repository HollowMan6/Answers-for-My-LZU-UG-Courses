import java.io.*;

public class SecretExample {
    public static void main(String[] args) {
        File fileOne = new File("hello.txt");
        File fileTwo = new File("hello.secret");
        byte b[] = new byte[100];
        try {
            FileInputStream in = new FileInputStream(fileOne);
            FileOutputStream out = new FileOutputStream(fileTwo);
            int n = -1;
            while ((n = in.read(b)) != -1) {
                for (int i = 0; i < n; i++) {
                    b[i] = (byte) (b[i] ^ 'a');
                }
                out.write(b, 0, n);
            }
            out.close();
            in = new FileInputStream(fileTwo);
            System.out.println("加密后的文件内容：");
            while ((n = in.read(b)) != -1) {
                String str = new String(b, 0, n);
                System.out.println(str);
            }
            in = new FileInputStream(fileTwo);
            System.out.println("解密后的文件内容：");
            while ((n = in.read(b)) != -1) {
                for (int i = 0; i < n; i++) {
                    b[i] = (byte) (b[i] ^ 'a');
                }
                System.out.printf(new String(b, 0, n));
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}