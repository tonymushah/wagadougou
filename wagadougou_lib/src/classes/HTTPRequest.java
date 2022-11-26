package classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import classes.headers.HTTPHeader;
import classes.headers.HTTPRequestHeader;
import enums.HTTPMethods;
import enums.HTTPVersion;

/*
 * > GET / HTTP/1.1
> Host: localhost:8090
> User-Agent: insomnia/2022.4.2
> x-random: hellooooo world
> Accept: *
 */

public class HTTPRequest {
    private HTTPRequestHeader requestHeader;
    private ArrayList<HTTPHeader> headers;
    private Object body;
    private URL to_send;
    public HTTPRequestHeader getRequestHeader() {
        return requestHeader;
    }
    public void setRequestHeader(HTTPRequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }
    public void setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }
    public URL getTo_send() {
        return to_send;
    }
    public void setTo_send(URL to_send) {
        this.to_send = to_send;
    }
    
}
