import java.io.BufferedReader;
import java.io.InputStreamReader;

import classes.clients.HTTPClient;
import classes.headers.HTTPHeader;
import classes.requestTypes.HTTPRequest;
import classes.requestTypes.HTTPRequestWTime;
import classes.requestTypes.InsomniaRequest;
import classes.responseTypes.HTTPResponse;
import classes.responseTypes.HTTPResponseWTime;
import classes.responseTypes.InsomniaResponse;
import enums.HTTPMethods;

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
    public static void main(String[] args) throws Exception{
        HTTPClient client = new HTTPClient("localhost", 8081);
        InsomniaRequest httpRequest = new InsomniaRequest();
        httpRequest.addHeader("Host", "localhost:8081");
        httpRequest.addHeader("User-Agent", "wagadoudou/2022.0.0.1");
        httpRequest.setMethod(HTTPMethods.POST);
        httpRequest.setPath("/testPost");
        byte[] body_req = "dasdkhasidsabdka".getBytes();
        httpRequest.addHeader("Content-Type", "text/plain");
        httpRequest.addHeader("Content-Length", "" + body_req.length);
        httpRequest.setBody(body_req);
        InsomniaResponse response = (InsomniaResponse) client.send(httpRequest);
        System.out.println(response.getResponseHeader());
        System.out.println("Duration : " + response.getDuration() + " ms");
        for (HTTPHeader header : response.getHeaders()) {
            System.out.println(header);
        }
        BufferedReader body = new BufferedReader(new InputStreamReader(response.getBodyStream()));
        for (String string : body.lines().toList()) {
            System.out.println(string);
        }
    }
}