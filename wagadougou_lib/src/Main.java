import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import classes.headers.HTTPHeader;
import classes.requestTypes.HTTPRequest;
import classes.requestTypes.HTTPRequestDefault;
import classes.responseTypes.HTTPResponse;

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
        HTTPResponse response = (new HTTPRequestDefault("localhost", 8082, "/otny", "GET")).send();
        System.out.println(response.getResponseHeader());
        for (HTTPHeader header : response.getHeaders()) {
            System.out.println(header);
        }

    }
}