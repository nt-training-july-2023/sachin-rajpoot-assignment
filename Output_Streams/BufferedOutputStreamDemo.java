package Output_Streams;



import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo {
    public static void main(String[] args) {
        String outFile = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\out.txt";

        try {
            String content = "this is an example using BufferedOutputStream!";
            FileOutputStream fos = new FileOutputStream(outFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bos.write(content.getBytes());
            bos.close();
            System.out.println("Data written to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

