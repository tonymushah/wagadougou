package mg.wagadougou.lib.classes.requestTypes.base;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.headers.HTTPRequestHeader;
import mg.wagadougou.lib.classes.requestTypes.element.HTTPRequestDefault;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.utils.HTTPQueryParam;
import mg.wagadougou.lib.classes.utils.HTTPQueryParams;
import mg.wagadougou.lib.enums.HTTPMethods;
import mg.wagadougou.lib.enums.HTTPVersion;

/*
 * > GET / HTTP/1.1
> Host: localhost:8090
> User-Agent: insomnia/2022.4.2
> x-random: hellooooo world
> Accept: *
 */

public abstract class HTTPRequest implements Serializable{
    private HTTPRequestHeader requestHeader;
    private ArrayList<HTTPHeader> headers;
    private byte[] body;
    public HTTPRequestHeader getRequestHeader() {
        return requestHeader;
    }
    public HTTPRequest setRequestHeader(HTTPRequestHeader requestHeader) {
        this.requestHeader = requestHeader;
        return this;
    }
    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }
    public HTTPRequest setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
        return this;
    }
    public byte[] getBody() {
        return body;
    }
    public HTTPRequest setBody(byte[] body) {
        this.body = body;
        return this;
    }
    public HTTPRequest() {
        this.setBody(null);
        this.setHeaders(new ArrayList<HTTPHeader>());
        this.setRequestHeader(new HTTPRequestHeader());
    }
    public HTTPRequest(HTTPRequestHeader requestHeader, ArrayList<HTTPHeader> headers, byte[] body) {
        this.setBody(body);
        this.setHeaders(headers);
        this.setRequestHeader(requestHeader);
    }
    public HTTPRequest(ArrayList<HTTPHeader> headers) {
        this.setBody(null);
        this.setHeaders(headers);
        this.setRequestHeader(new HTTPRequestHeader());
    }
    public HTTPRequest addHeader(HTTPHeader header){
        this.headers.add(header);
        return this;
    }
    public HTTPRequest addHeader(String name, String value){
        this.headers.add(new HTTPHeader(name, value));
        return this;
    }
    public HTTPRequest setPath(String path){
        this.requestHeader.setPath(path);
        return this;
    }
    public HTTPRequest setMethod(String method){
        this.requestHeader.setMethod(method);
        return this;
    }
    public HTTPRequest setMethod(HTTPMethods method){
        this.requestHeader.setMethod(method);
        return this;
    }
    public HTTPRequest setQueryParams(HTTPQueryParams queryParams){
        this.requestHeader.setQueryParams(queryParams);
        return this;
    }
    public HTTPRequest addQueryParams(HTTPQueryParam queryParam){
        this.requestHeader.getQueryParams().add(queryParam);
        return this;
    }
    public HTTPRequest addQueryParams(String name, String value){
        this.requestHeader.getQueryParams().add(new HTTPQueryParam(name, value));
        return this;
    }
    public HTTPRequest setHTTPVersion(HTTPVersion version){
        this.requestHeader.setVersion(version);
        return this;
    }
    public HTTPRequest(String path, String method){
        this.setRequestHeader(new HTTPRequestHeader(method, HTTPVersion.HTTP1_1, path));
        this.setHeaders(new ArrayList<HTTPHeader>());
    }
    public abstract HTTPResponse send(Socket target) throws Exception;
    public static HTTPRequest getDefault(){
        return new HTTPRequestDefault();
    }
}
