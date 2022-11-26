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
    public HTTPMethods getMethods() {
        return HTTPMethods.valueOf(this.requestHeader.getMethod());
    }
    public void setMethods(HTTPMethods methods) {
        this.requestHeader.setMethod(methods);;
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
    public HTTPResponse send() throws Exception{
        Socket server = new Socket(this.to_send.getHost(), this.to_send.getPort());
        OutputStream outStream = server.getOutputStream();
        PrintStream out = new PrintStream(outStream);
        out.println(this.requestHeader);
        for (HTTPHeader httpHeader : headers) {
            out.println(httpHeader);
        }
        out.println();
        try {
            out.writeBytes(null);
        } catch (Exception e) {
            //TODO: handle exception
        }
        out.flush();
        BufferedReader azo =  new BufferedReader(new InputStreamReader(server.getInputStream()));
        Object[] getted = azo.lines().toList().toArray().clone();
        server.close();
        //out.close();
        HTTPResponse response = new HTTPResponse();
        boolean is_at_body = false;
        for (int i = 0; i < getted.length; i++) {
            if(getted[i] instanceof String){
                if(i == 0){
                    response.setResponseHeader((String) getted[i]);
                }else if(((String) getted[i]).compareTo("") != 0 && is_at_body == false){
                    response.addHeader(HTTPHeader.valueOf((String) getted[i]));
                }else{
                    is_at_body = true;
                }
                if(is_at_body){
                    response.setBody(getted[i]);
                }
            }
        }
        return response;
    }
    public void test_send() throws Exception{
        PrintStream out = System.out;
        out.println(this.requestHeader);
        for (HTTPHeader httpHeader : headers) {
            out.println(httpHeader);
        }
        out.println();
        //out.close();
    }
    public HTTPRequest() {
        this.setRequestHeader(new HTTPRequestHeader());
    }
    public HTTPRequestHeader getRequestHeader() {
        return requestHeader;
    }
    public void setRequestHeader(HTTPRequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }
}
