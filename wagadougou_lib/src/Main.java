import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import classes.headers.HTTPHeader;
import classes.requestTypes.HTTPRequest;
import classes.requestTypes.HTTPRequestDefault;
import classes.responseTypes.HTTPResponse;
import enums.HTTPVersion;

/**
 * Main
 */
public class Main {
    /*
 * > GET / HTTP/1.1
> Host: localhost:8090
> User-Agent: insomnia/2022.4.2
> x-random: hellooooo world
> Accept: *
 */
    public static void main(String[] args) throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        
        SSLSocket target = (SSLSocket) factory.createSocket("api.mangadex.org", 443);
        HTTPRequest request = new HTTPRequestDefault("/manga", "GET");
        request.addHeader("Host", "api.mangadex.org");
        request.addHeader("user-agent", "wagadougou/2022.0.0.1");
        request.addHeader("accept", "*/*");
        request.setHTTPVersion(HTTPVersion.HTTP1_1);
        target.startHandshake();
        HTTPResponse response2 = request.send(target);
        target.close();
        System.out.println(response2.getResponseHeader());
        for (HTTPHeader header : response2.getHeaders()) {
            System.out.println(header);
        }
        for (String line : (new BufferedReader(new InputStreamReader(response2.getBodyStream()))).lines().toList()) {
            System.out.println(line);
        }
        
    }
}