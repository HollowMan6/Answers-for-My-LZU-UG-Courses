import java.io.*;
class Exception2 {
    public static void main(String[] args)
        throws IOException {
        if (args.length == 0) {
            System.out.println("Must give filename as first arg.");
            return;
        }
        FileInputStream fin;
        try {
            fin = new FileInputStream(args[0]);
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + args[0]);
            return;
        }
        DataInputStream din = new DataInputStream(fin);
        try {
            int i;
            for (;;) {
                i = din.readInt();
                System.out.println(i);
            }
        }
        catch (EOFException e) {
        }
        fin.close();
    }
}
