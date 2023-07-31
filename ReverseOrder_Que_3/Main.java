package ReverseOrder_Que_3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String in = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\in.txt";
        String out = "C:\\Users\\Sachin\\eclipse-workspace\\JavaIO\\src\\Subclass_Que_1\\out.txt";
        
        try {
            
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

          
            Collections.reverse(lines);

           
            BufferedWriter bw = new BufferedWriter(new FileWriter(out));
            for (String str : lines) {
                bw.write(str);
                bw.newLine();
            }
            bw.close();

            System.out.println("Operation is successfull");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
