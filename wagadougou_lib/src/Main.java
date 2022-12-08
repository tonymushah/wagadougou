import java.io.BufferedReader;
import java.io.InputStreamReader;

import mg.wagadougou.lib.classes.BodyParsers.Chunked;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.misc.RequestFolder;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;
import mg.wagadougou.lib.enums.HTTPMethods;

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
        RequestCollection collection = new RequestCollection("SpringCollection", client)
            .addFolder(
                new RequestFolder("root").addRequest(
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
                ).setId("spring_collexct")
            );
        RequestCollection collection2 = new RequestCollection("Actix collection", new HTTPClient("localhost", 8082))
            .addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("HELLO")
                .addHeader("Host", "localhost:8082")
                .addHeader("User-Agent", "wagadoudou/2022.0.0.1")
            );
        // Request GET1 
        System.out.println("request 1");
        InsomniaResponse response = (InsomniaResponse) collection.sendByID("GET1");
        System.out.println(response.getDuration() + "ms");
        System.out.println(response.getResponseHeader());
        for (HTTPHeader header : response.getHeaders()) {
            System.out.println(header);
        }
        Chunked chunked = new Chunked(response.getBodyStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(chunked.getData()));
        for (String string : reader.lines().toList()) {
            System.out.println(string);
        }
        System.out.println();
        // Request HELLO TEST
        System.out.println("request 2");
        InsomniaResponse response2 = (InsomniaResponse) collection2.sendByID("HELLO");
        System.out.println(response2.getDuration() + "ms");
        System.out.println(response2.getResponseHeader());
        for (HTTPHeader header : response2.getHeaders()) {
            System.out.println(header);
        }
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(response2.getBodyStream()));
        for (String string : reader2.lines().toList()) {
            System.out.println(string);
        }
    }
}