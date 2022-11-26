import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import classes.HTTPRequest;
import classes.HTTPResponse;
import classes.headers.HTTPHeader;

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
        HTTPRequest req = new HTTPRequest();
        HTTPHeader[] headers = {
            new HTTPHeader("Host", "localhost:8082"),
            new HTTPHeader("User-Agent", "wagadougou/v0.0.1"),
            new HTTPHeader("x-random", "hellooooo world")
        };
        ArrayList<HTTPHeader> list = new ArrayList<>();
        for (HTTPHeader header : headers) {
            list.add(header);
        }
        req.setTo_send(new URL("http://127.0.0.1:8082"));
        req.setHeaders(list);
        HTTPResponse response = req.send();
        System.out.println(response.getResponseHeader().getStatus_code());
    }
}