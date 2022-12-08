package tests;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Test_client
 */
public class Test_client {
/*
 * > GET / HTTP/1.1
> Host: localhost:8090
> User-Agent: insomnia/2022.4.2
> x-random: hellooooo world
> Accept: *
 */
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8081);
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            out.println("GET / HTTP/1.1");
            out.println("Host: localhost:8081 ");
            out.println("User-Agent: wagadougou/2022.0.0");
            out.println();
            out.flush();
        clientSocket.shutdownOutput();
        BufferedReader azo =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        for (String object : azo.lines().toList()) {
            System.out.println(object);
        }
        clientSocket.close();
    }
}
