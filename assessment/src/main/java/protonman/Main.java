// Task 2 Main
package protonman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // assume I can read the command line as variable text
        String eachText, eachDir;
        int portNo = 0;
        ArrayList <String> directories = new ArrayList<>();

    // text = "--port 8080 --docRoot ./target:opt/tmp/www";
        
    //  Task 3 command line options

        // first check whether input is empty
        if (args.length==0) {
            portNo = 3000;
            directories.add("\\assessment\\static");
        } else {
            
            for (int i = 0; i < args.length; i++) {
                eachText = args[i];

                if ("--port".equals(eachText)) {
                    i++;
                    portNo = Integer.valueOf(args[i]);
                    
                } else if ("--docRoot".equals(eachText)) {
                    i++;
                    Scanner pathDir = new Scanner(args[i]);
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
                
        }
        
    // Task 4 call server
        HttpServer server = new HttpServer(portNo, directories);
        server.start();

    }

}
