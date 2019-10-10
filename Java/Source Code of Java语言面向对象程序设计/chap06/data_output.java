import java.io.*;
class data_output
{ public static void main(String args[]) throws  IOException
  { FileOutputStream fos=new FileOutputStream("datatest.dat");
     DataOutputStream dos=new DataOutputStream (fos);
     try{ 	
        dos.writeBoolean(true);
        dos.writeByte((byte)123);
	dos.writeChar('J');
  	dos.writeDouble(3.141592654);
	dos.writeFloat(2.7182f);
	dos.writeInt(1234567890);
	dos.writeLong(998877665544332211L);
	dos.writeShort((short)11223);
        dos.writeUTF("中华人民共和国");
     }finally{  dos.close(); fos.close();}
    }
}