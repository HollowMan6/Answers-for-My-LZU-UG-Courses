import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream out1 = new FileOutputStream("data.txt");
		BufferedOutputStream out2 = new BufferedOutputStream(out1);
		DataOutputStream out = new DataOutputStream(out2);
		out.writeByte(-12);
		out.writeLong(12);
		out.writeChar('A');
		out.writeUTF("中国");
		out.close();
		InputStream in1 = new FileInputStream("data.txt");
		BufferedInputStream in2 = new BufferedInputStream(in1);
		DataInputStream in = new DataInputStream(in2);
		System.out.println(in.readByte());
		System.out.println(in.readLong());
		System.out.println(in.readChar());
		System.out.println(in.readUTF());
		in.close();
	}
}
