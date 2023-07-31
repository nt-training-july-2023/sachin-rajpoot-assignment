package Output_Streams;



import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) {
        try {
            String content = "this is an example using ByteArrayOutputStream";
            byte[] data = content.getBytes();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(data);

       
            byte[] byteArray = baos.toByteArray();
          
            String outFile = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\out.txt";
            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(byteArray);
            fos.close();

            System.out.println("Data written to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
