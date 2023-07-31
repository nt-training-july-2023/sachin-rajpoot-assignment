package Subclass_Que_1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackInputStream;

public class PushbackInputStreamDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String in = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
		BufferedReader br = new BufferedReader(new FileReader(in));

		String content = br.readLine();
		System.out.println(content);

		byte[] bytArr = content.getBytes();
		PushbackInputStream pbis = new PushbackInputStream(new ByteArrayInputStream(bytArr));

		int i;
		while ((i = pbis.read()) != -1) {
			if (i == '#') {
//				System.out.println("$");
				int j;
				if ((j = pbis.read()) == '#') {

					System.out.print("<><>");

				} else {
					pbis.unread(j);
					System.out.print((char) i);
				}
			} else {
				System.out.print((char) i);
			}
		}

	}

}
