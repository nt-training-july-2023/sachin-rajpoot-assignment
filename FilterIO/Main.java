package FilterIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;

class OneClass extends FilterInputStream {

    protected OneClass(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int data = super.read();
        if (data != -1) {
            data *= 2;
        }
        return data;
    }
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String in = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
	     String out = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\out.txt";
	     
	     try {
	            
	            FileInputStream fis = new FileInputStream(in);
	            OneClass flis = new OneClass(fis);

	            
	            FileOutputStream fos = new FileOutputStream(out);
	            FilterOutputStream flos = new FilterOutputStream(fos);

	            int data;
	            while ((data = flis.read()) != -1) {
	            	flos.write(data);
	            }

	            flis.close();
	            flos.close();

	            System.out.println("File content filtered successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
