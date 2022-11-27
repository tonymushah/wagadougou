package classes.responseTypes;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.naming.NameNotFoundException;

import classes.headers.HTTPHeader;
import classes.headers.HTTPResponseHeader;


public class HTTPResponse {
    private InetAddress target;
    private ArrayList<HTTPHeader> headers;
    private HTTPResponseHeader responseHeader;
    private byte[] body;
    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }
    public void setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
    }
    public byte[] getBody() {
        return body;
    }
    public void setBody(byte[] body) {
        this.body = body;
    }
    
    public InetAddress getTarget() {
        return target;
    }
    public void setTarget(InetAddress target) {
        this.target = target;
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
    public HTTPResponse(ArrayList<HTTPHeader> headers, HTTPResponseHeader responseHeader, byte[] body) {
        this.headers = headers;
        this.responseHeader = responseHeader;
        this.body = body;
    }
    public InputStream getBodyStream() throws IOException{
        ByteArrayInputStream stream = new ByteArrayInputStream(this.body);
        return stream;
    }
    public HTTPHeader getHeader(String name) throws NameNotFoundException{
        for (HTTPHeader header : this.headers) {
            if(name.compareTo(header.getContext()) == 0){
                return header;
            }
        }
        throw new NameNotFoundException("the header with name " + name + " is not found");
    }
}
