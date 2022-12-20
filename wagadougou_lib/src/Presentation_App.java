import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;

public class Presentation_App {
    public static void sending_request_1() throws Exception {
        Socket server = new Socket("localhost", 8082);
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        
        // print request 
        out.println("GET / HTTP/1.1");
        out.println();
        out.flush();

        server.shutdownOutput();

        // showing the response

        for (String getted : in.lines().toList()) {
            System.out.println(getted);
        }
        server.close();
    }
    public static void sending_request_2() throws Exception {
        Socket server = new Socket("localhost", 8081);
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        
        // print request 
        out.println("GET /?name=Tony HTTP/1.1");
        out.println("Host: localhost:8081");
        out.println("user-agent: wagadougou/2022.0.0.1");
        out.println();
        out.flush();

        server.shutdownOutput();

        // showing the response

        for (String getted : in.lines().toList()) {
            System.out.println(getted);
        }
        server.close();
    }
    public static void sending_request_post() throws Exception {
        Socket server = new Socket("localhost", 8082);
        PrintStream out = new PrintStream(server.getOutputStream());
        
        byte[] body = "dsajuidsahduiashduhaudhidshasd".getBytes();
        // print request 
        out.println("POST /test_post HTTP/1.1");
        out.println("content-type: text/plain");
        out.println("content-length: " + body.length);
        out.println();
        out.writeBytes(body);
        out.flush();
        server.shutdownOutput();

        // showing the response
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        for (String getted : in.lines().toList()) {
            System.out.println(getted);
        }
        server.close();
    }
    public static void sending_request_test_express() throws Exception {
        Socket server = new Socket("localhost", 8083);
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        
        // print request 
        out.println("GET / HTTP/1.1");
        out.println();
        out.flush();

        server.shutdownOutput();

        // showing the response

        for (String getted : in.lines().toList()) {
            System.out.println(getted);
        }
        server.close();
    }
    public static void sending_request_wagadougou_1() throws Exception {
        Socket server = new Socket("localhost", 8082);
        HTTPRequest request = HTTPRequest.getDefault();
        HTTPResponse response = request.send(server);
        server.close();
        System.out.println(response.getResponseHeader());
        for (HTTPHeader header : response.getHeaders()) {
            System.out.println(header);
        };
        BufferedReader body = new BufferedReader(new InputStreamReader(response.getBodyStream()));
        for (String string : body.lines().toList()) {
            System.out.println(string);
        }
    }
    public static void sending_request_wagadougou_2() throws Exception {
        Socket server = new Socket("localhost", 8081);
        HTTPRequest request = HTTPRequest.getDefault()
            .addHeader("Host", "localhost:8081")
            .addHeader("User-Agent", "wagadougou/2022.0.0.1")
            .addQueryParams("name", "Tony");
        HTTPResponse response = request.send(server);
        server.close();
        System.out.println(response.getResponseHeader());
        for (HTTPHeader header : response.getHeaders()) {
            System.out.println(header);
        };
        BufferedReader body = new BufferedReader(new InputStreamReader(response.getBodyStream()));
        for (String string : body.lines().toList()) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) throws Exception {
        Presentation_App.sending_request_2();
    }
}
