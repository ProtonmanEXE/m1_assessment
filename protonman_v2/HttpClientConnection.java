// Task 2 HttpClientConnection
package protonman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HttpClientConnection implements Runnable {
    private final Socket socket;
    private int id;
    private String inputFile;


    public HttpClientConnection(Socket socket, String inputFile) {
        this.socket = socket;
        this.inputFile = inputFile;
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

        while (!"close".equals(line) && null != line) {
            
            try {
                
                // if ("get-cookie".equals(line)) {
                //     System.out.println("Sending a cookie to client " + id);
                //     out.println("cookie-text " + 
                //             new Cookie().getCookie(inputFile));
                //     out.flush();
                //     line = in.readLine();
                // } else {
                //     out.println("Server: you said " + line);
                //     out.flush();
                //     line = in.readLine();
                // }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            } 
        }
    }
}

public static void main(String[] args) {
    String line = "GET /index.html HTTP/1.1";
    Scanner scan = new Scanner(line);
    


}
