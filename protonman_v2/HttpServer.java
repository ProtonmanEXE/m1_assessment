// Task 2 HttpServer
package protonman;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private Socket socket;
    private boolean Check1, Check2, Check3;
    private ArrayList <String> list = new ArrayList<>();
    int socketNo;

    public HttpServer(int socketNo, ArrayList <String> list) {
        this.socketNo = socketNo;
        this.list = list;
    }

    public void start () throws IOException {
        Socket socket;
        ServerSocket serverSocket = new ServerSocket();
        String inputFile = "cookie_file.txt";

        try {
            // Task 4 set-up listening port
            serverSocket = new ServerSocket(socketNo);
            System.out.println("Server listening at port " +socketNo + " ...");
        } catch (Exception e) {
            serverSocket.close();
            System.out.println("Search failure.");
            System.exit(1);
        }

            // Task 4 check path
            System.out.println("Your directories are " +list);
            for (int i = 0; i < list.size(); i++) {
                Path path = Paths.get(list.get(i));
                System.out.println(path);
                Check1 = Files.exists(path);
                Check2 = Files.isDirectory(path);
                Check3 = Files.isReadable(path);

                if(Check1 && Check2 && Check3) {

                } else {
                    serverSocket.close();
                    System.out.println("Search failure.");
                    System.exit(1);
                }
    
            }
        
        // Task 5 creating a thread pool with three threads
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {

            while (true) {
                socket = serverSocket.accept();
                HttpClientConnection worker = new HttpClientConnection(socket, inputFile);
                threadPool.submit(worker);
            }

        } finally {
            serverSocket.close();
        }
    }
}
