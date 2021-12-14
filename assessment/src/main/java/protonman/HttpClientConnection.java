// Task 2 HttpClientConnection
package protonman;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HttpClientConnection implements Runnable {
    private final Socket socket;
    private ArrayList <String> directories = new ArrayList<>();
    private String command, command2, fileName, extension, resource, directory;
    private boolean fileUpload = false;

    public HttpClientConnection(Socket socket, ArrayList<String> inputFile) {
        this.socket = socket;
        this.directories = inputFile;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        String line = "";

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            line = in.readLine();
        } catch (IOException ioe) {
            System.out.println("Something went wrong...");
        }

        // String line = "GET /index.html HTTP/1.1";
        Scanner scan = new Scanner(line);
        command = scan.next();
        command2 = scan.nextLine();

        // Task 6-1 Not a Get method
        if (!"GET".equals(command)) {
            out.println("405 Method Not Allowed");
            out.println(command +" not supported");
            out.flush();
            closeEverything(socket, in, out);
        
        } else {                
            Scanner scan2 = new Scanner(command2);
            resource = scan2.next();

            for (int j = 0; j < directories.size(); j++) {
                directory = directories.get(0);
                File database = new File(directory);
                int i = 0;
                    for (File f: database.listFiles()) {
                    i++;

        // Task 6-3 Resource exist
                    if (f.getName()==resource) {
                        fileName = f.getName();
                        int index = fileName.lastIndexOf('.');
                            if(index > 0) 
                            extension = fileName.substring(index + 1);
                            fileUpload = true;
                            // add in code to send file to website
                            
        // Task 6-4 Resource exist and is a PNG
                            if ("png".equals(extension)) {
                            fileUpload = true;
                            // add in code to send file to website
                            out.println("200 OK");
                            out.println("Content-Type: image/png");
                            out.println("<resource contents as bytes>");
                            out.flush();
                            closeEverything(socket, in, out);
                            break;
                            } else {
                                out.println("200 OK");
                                out.println("<resource contents as bytes>");
                                out.flush();
                                closeEverything(socket, in, out);
                                break;
                            }
        
                    }
                
                    }
                    
            }
        // Task 6-2 Resource does not exist
            if (!fileUpload) {
                out.println("404 Method Not Allowed");
                out.println(resource +" not found");
                out.flush();
                closeEverything(socket, in, out);
            } 

        }
    
    }

    public void closeEverything(Socket socket, BufferedReader in, PrintWriter out) {
            try {
                if (socket != null) {
                    socket.close();
                } 
                if (in != null) {
                    in.close();
                }  
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}


