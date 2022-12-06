import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import classes.clients.HTTPClient;
import classes.headers.HTTPHeader;
import classes.misc.RequestFolder;
import classes.requestTypes.base.HTTPRequest;
import classes.requestTypes.base.HTTPRequestElement;
import classes.requestTypes.element.HTTPRequestWTime;
import classes.requestTypes.element.InsomniaRequestBase;
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
        /*InsomniaRequestBase httpRequest = new InsomniaRequestBase();
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
        }*/
        byte[] body_req = "dasdkhasidsabdka".getBytes();
        RequestFolder spring_Request = new RequestFolder("root").addRequest(
            (HTTPRequestElement) (new InsomniaRequestBase()
                .setId("POST1")
                .setName("Test POST to Spring")
                .addHeader("Host" , "localhost:8081")
                .addHeader("User-Agent", "wagadoudou/2022.0.0.1")
                .setMethod(HTTPMethods.POST)
                .setPath("/testPost")
                .setBody(body_req)
                .addHeader("Content-Type", "text/plain")
                .addHeader("Content-Length", String.valueOf(body_req.length)))
        ).addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                .setId("GET1")
                .addHeader("Host" , "localhost:8081")
                .addHeader("User-Agent", "wagadoudou/2022.0.0.1"))
        );
    }
}