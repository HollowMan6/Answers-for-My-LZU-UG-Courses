import java.io.*;
class data_input
{
public static void main(String[] args) throws IOException
{
   DataInputStream dis=new DataInputStream
    (new FileInputStream("datatest.dat"));
   try{ System.out.println("\t "+dis.readBoolean());
	    System.out.println("\t "+dis.readByte());
	    System.out.println("\t "+dis.readChar());
	    System.out.println("\t "+dis.readDouble());
	    System.out.println("\t "+dis.readFloat());
	    System.out.println("\t "+dis.readInt());
	    System.out.println("\t "+dis.readLong());
	    System.out.println("\t "+dis.readShort());
            System.out.println("\t "+dis.readUTF());
	}finally{dis.close();}
}}
