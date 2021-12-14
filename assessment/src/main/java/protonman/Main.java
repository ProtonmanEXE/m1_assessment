// Task 2 Main
package protonman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // assume I can read the command line as variable text
        String text, eachText, docNo, eachDir;
        int portNo = 0;
        ArrayList <String> directories = new ArrayList<>();

        System.out.println("Args is " +args);
        text = "--port 8080 --docRoot ./target:opt/tmp/www";
        
    //  Task 3 command line options

        // first check whether input is empty
        // if null, then File directory = new File("assessment\\static");


        // check inputs for correct command
        try {
            Scanner scan = new Scanner(text);
            
            while (scan.hasNext()) {
                eachText = scan.next();
                System.out.println(eachText);
                    
                //
                if ("--port".equals(eachText)) {
                    portNo = Integer.valueOf(scan.next());
    
                } else if ("--docRoot".equals(eachText)) {
                    System.out.println("specify doc");
                    docNo = scan.next();
                    Scanner pathDir = new Scanner(docNo);
                    pathDir.useDelimiter(":");
                    while (pathDir.hasNext()) {
                        eachDir = pathDir.next();
                        directories.add(eachDir);
                    }
                    System.out.println("doc no. is " +directories);
    
                } else {
                    // do nothing
                }
            }
            scan.close();

        } catch (Exception e) { // to catch null when there are no inputs or scan is finished
            System.out.println("error");
            portNo = 3000;
            System.out.println(portNo);      
        }  
        
        
        System.out.println(portNo);
        System.out.println(directories);
        

    // Task 4 call server
        HttpServer server = new HttpServer(portNo, directories);
        server.start();


    }

}
