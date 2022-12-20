package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.enums.HTTPVersion;

public class SSLTest {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("api.mangadex.org");
        HTTPRequest request = new InsomniaRequestBase("/manga", "GET");
        request.addHeader("Host", "api.mangadex.org");
        request.addQueryParams("title", "tonikaku");
        request.addHeader("x-random", "helloooo world");
        request.addHeader("accept", "*/*");
        request.setHTTPVersion(HTTPVersion.HTTP1_1);
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket target = (SSLSocket) factory.createSocket(address, 443);
        target.startHandshake();
        PrintWriter out = new PrintWriter(target.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(target.getInputStream()));
        
        // print request 
        out.println("GET /manga?title=tonikaku HTTP/1.1");
        out.println("Host: api.mangadex.org");
        out.println("x-random: helloooo world");
        out.println("accept: */*");
        out.println();
        out.flush();

        target.shutdownOutput();

        // showing the response

        for (String getted : in.lines().toList()) {
            System.out.println(getted);
        }
        target.close();
    }
}
