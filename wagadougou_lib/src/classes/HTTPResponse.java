package classes;

import java.util.ArrayList;

import classes.headers.HTTPHeader;
import classes.headers.HTTPResponseHeader;


public class HTTPResponse {
    private ArrayList<HTTPHeader> headers;
    private HTTPResponseHeader responseHeader;
    private Object body;
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
    
    public HTTPResponseHeader getResponseHeader() {
        return responseHeader;
    }
    public void setResponseHeader(HTTPResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }
    public HTTPResponse(){
        this.setHeaders(new ArrayList<HTTPHeader>());
    }
    public void setResponseHeader(String input) throws Exception{
        this.setResponseHeader(new HTTPResponseHeader(input));
    }
    public void addHeader(HTTPHeader header){
        this.headers.add(header);
    }
}
