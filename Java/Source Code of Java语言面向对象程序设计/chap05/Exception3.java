// Exception3.java
// This program prints the white-space separated tokens of an
// ASCII file in reverse order of their appearance in the file.
import java.io.*;
import java.util.*;
class Exception3 {
    public static void main(String[] args)
        throws IOException {
        if (args.length == 0) {
            System.out.println("Must give filename as first arg.");
            return;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(args[0]);
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find file: " + args[0]);
            return;
        }
        // Read file into a StringBuffer
        StringBuffer buf = new StringBuffer();
        try {
            int ch;
            while ((ch = in.read()) != -1) {
                buf.append((char) ch);
            }
        }
        finally {
            in.close();
        }
        // Separate StringBuffer into tokens and
        // push each token into a Stack
        StringTokenizer tok = new StringTokenizer(buf.toString());
        Stack stack = new Stack();
        while (tok.hasMoreTokens()) {
            stack.push(tok.nextToken());
        }
        // Print out tokens in reverse order.
        while (!stack.empty()) {
            System.out.println((String) stack.pop());
        }
    }
}
