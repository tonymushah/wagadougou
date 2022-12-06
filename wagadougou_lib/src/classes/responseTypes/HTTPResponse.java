package classes.responseTypes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public HTTPResponse setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
        return this;
    }
    public byte[] getBody() {
        return body;
    }
    public HTTPResponse setBody(byte[] body) {
        this.body = body;
        return this;
    }
    
    public InetAddress getTarget() {
        return target;
    }
    public HTTPResponse setTarget(InetAddress target) {
        this.target = target;
        return this;
    }
    public HTTPResponseHeader getResponseHeader() {
        return responseHeader;
    }
    public HTTPResponse setResponseHeader(HTTPResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
        return this;
    }
    public HTTPResponse(){
        this.setHeaders(new ArrayList<HTTPHeader>());
    }
    public HTTPResponse setResponseHeader(String input) throws Exception{
        this.setResponseHeader(new HTTPResponseHeader(input));
        return this;
    }
    public HTTPResponse addHeader(HTTPHeader header){
        this.headers.add(header);
        return this;
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
