import java.io.*;
class Exception1 {
    public static void main(String[] args)
        throws IOException {
        if (args.length == 0) {
            System.out.println("Must give filename as first arg.");
            return;
        }
        FileInputStream in;
        try {
            in = new FileInputStream(args[0]);
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + args[0]);
            return;
        }
        int ch;
        while ((ch = in.read()) != -1) {
            System.out.print((char) ch);
        }
        System.out.println();
        in.close();
    }
}
