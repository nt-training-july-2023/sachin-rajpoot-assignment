package Subclass_Que_1;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] byteArray = {1,2,3,4};
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		int i=0;
		while((i=bais.read()) != -1) {
			System.out.print((byte)i);
		}
		bais.close();
	}

}
