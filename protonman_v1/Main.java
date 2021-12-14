package protonman;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // assume I can read the command line as variable text
        String text;
        String eachText, newText, docNo;
        int portNo;
        ArrayList <String> command = new ArrayList<>();

        text = "--port 8080 --docRoot ./target:opt/tmp/www";
        
        // command line options
        // first check whether input is empty
        try {
            Scanner scan = new Scanner(text);
            eachText = scan.next();
            while (!eachText.isEmpty()) {
                System.out.println(eachText);
                    
                //
                if ("--port".equals(eachText)) {
                    System.out.println("specify port");
                    portNo = Integer.valueOf(scan.next());
                    System.out.println("Port no. is " +portNo);
    
                } else if ("--docRoot".equals(eachText)) {
                    System.out.println("specify doc");
                    docNo = scan.next();
                    System.out.println("doc no. is " +docNo);
    
                } else {
                    // do nothing
                }
    
                newText = scan.nextLine();
                System.out.println(newText);
                Scanner scan2 = new Scanner(newText);
                eachText = scan2.next();
                        
            }
        } catch (Exception e) { // to catch null when there are no inputs or scan is finished
            System.out.println("error");
            portNo = 3000;
            System.out.println(portNo);      
    }
    

        
        
        
    }
}
