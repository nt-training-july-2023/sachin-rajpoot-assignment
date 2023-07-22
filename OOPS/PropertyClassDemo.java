package OOPS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyClassDemo {

    public static void main(String[] args) throws IOException {
        Properties p = new Properties();

        FileInputStream fis = new FileInputStream("C:\\Users\\Sachin\\Desktop\\Assignments\\OOPS\\Login.properties");

        p.load(fis);

        String Username = p.getProperty("Username");
        String Passward = p.getProperty("Passward");

        System.out.println("Username is "+Username+" and Passward is "+Passward);

        Enumeration e = p.propertyNames();

        while(e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }


        p.setProperty(Username, "Naruto Uzumaki")   ;

        FileOutputStream fos = new FileOutputStream("C:\\Users\\Sachin\\Desktop\\Assignments\\OOPS\\Login.properties");

        p.store(fos, "username changed");
        

    }

}
