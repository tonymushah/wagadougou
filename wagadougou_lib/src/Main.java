import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> envirVar = HashMap.newHashMap(1); 
        envirVar.put("actix_body", "sdandaisdjnksada");
        byte[] body_req = "dasdkhasidsabdka".getBytes();
        RequestCollection collection = new RequestCollection("SpringCollection", new HTTPClient("localhost", 8081))
            .addFolder(
                new RequestFolder("root").addRequest(
                    (HTTPRequestElement) (new InsomniaRequestBase()
                        .setId("POST1")
                        .setName("Test POST to Spring")
                        .addHeader("Host" , "localhost:8081")
                        .addHeader("user-agent", "wagadoudou/2022.0.0.1")
                        .setMethod(HTTPMethods.POST)
                        .setPath("/testPost")
                        .setBody(body_req)
                        .addHeader("zontent-type", "text/plain")
                        .addHeader("content-length", String.valueOf(body_req.length)))
                ).addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                        .setId("GET1")
                        .addHeader("Host" , "localhost:8081")
                        .addHeader("user-agent", "wagadoudou/2022.0.0.1"))
                ).setId("spring_collexct")
                );
            byte[] bodyActix0 = envirVar.get("actix_body").getBytes();
            RequestCollection collection2 = new RequestCollection("Actix collection", new HTTPClient("localhost", 8082))
                .addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("HELLO")
                .addHeader("Host", "localhost:8082")
                .addHeader("user-agent", "wagadoudou/2022.0.0.1")
            ).addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("test_post")
                .setMethod(HTTPMethods.POST)
                .setBody(bodyActix0)
                .setPath("/test_post")
                .addHeader("Host", "localhost:8082")
                .addHeader("user-agent", "wagadoudou/2022.0.0.1")
                .addHeader("content-type", "text/plain")
                .addHeader("content-length", "" + bodyActix0.length)
            );
        byte[] bodyActix = "dasdijasdnasoddsa".getBytes();
        RequestCollection collection3 = new RequestCollection("Express collection", new HTTPClient("localhost", 8083))
            .addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("HELLO")
                .addHeader("Host", "localhost:8083")
                .addHeader("user-Agent", "wagadoudou/2022.0.0.1")
                .addHeader("date", (new Date()).toString())
            ).addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("test_Post")
                .setPath("/testPost")
                .setBody(bodyActix)
                .addHeader("content-length", "" + bodyActix.length)
                .setMethod(HTTPMethods.POST)
                .addHeader("Host", "localhost:8083")
                .addHeader("user-agent", "wagadoudou/2022.0.0.1")
                .addHeader("date", (new Date()).toString())
            );
        // Request GET1 
        try {
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
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
        }
        
        System.out.println();
        // Request HELLO TEST
        try {
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
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
        }
        
        System.out.println();
        // Request test Post
        try {
            System.out.println("request 3");
            InsomniaResponse response3 = (InsomniaResponse) collection3.sendByID("test_Post");
            System.out.println(response3.getDuration() + "ms");
            System.out.println(response3.getResponseHeader());
            for (HTTPHeader header : response3.getHeaders()) {
                System.out.println(header);
            }
            BufferedReader reader3 = new BufferedReader(new InputStreamReader(response3.getBodyStream()));
            for (String string : reader3.lines().toList()) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
        }
        
        System.out.println();
        // Request post test
        try {
            System.out.println("request 4");
            InsomniaResponse response4 = (InsomniaResponse) collection3.sendByID("HELLO");
            System.out.println(response4.getDuration() + "ms");
            System.out.println(response4.getResponseHeader());
            for (HTTPHeader header : response4.getHeaders()) {
                System.out.println(header);
            }
            BufferedReader reader4 = new BufferedReader(new InputStreamReader(response4.getBodyStream()));
            for (String string : reader4.lines().toList()) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
        }
        System.out.println();
        // Request post test
        try {
            envirVar.replace("actix_body", "absala");
            System.out.println("request 5");
            InsomniaResponse response5 = (InsomniaResponse) collection2.sendByID("test_post");
            System.out.println(response5.getDuration() + "ms");
            System.out.println(response5.getResponseHeader());
            for (HTTPHeader header : response5.getHeaders()) {
                System.out.println(header);
            }
            BufferedReader reader5 = new BufferedReader(new InputStreamReader(response5.getBodyStream()));
            for (String string : reader5.lines().toList()) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
            e.printStackTrace();
        }
        try {
            System.out.println("request 6");
            InsomniaResponse response5 = (InsomniaResponse) collection.sendByID("POST1");
            System.out.println(response5.getDuration() + "ms");
            System.out.println(response5.getResponseHeader());
            for (HTTPHeader header : response5.getHeaders()) {
                System.out.println(header);
            }
            BufferedReader reader5 = new BufferedReader(new InputStreamReader(response5.getBodyStream()));
            for (String string : reader5.lines().toList()) {
                System.out.println(string);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("message " + e.getMessage());
            e.printStackTrace();
        }
    }
}