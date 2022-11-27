package classes.requestTypes;

import java.util.ArrayList;

import classes.headers.HTTPHeader;
import classes.headers.HTTPRequestHeader;
import classes.responseTypes.HTTPResponse;
import classes.utils.HTTPQueryParam;
import classes.utils.HTTPQueryParams;
import classes.utils.WGDGUrl_Base;
import enums.HTTPMethods;
import enums.HTTPVersion;

/*
 * > GET / HTTP/1.1
> Host: localhost:8090
> User-Agent: insomnia/2022.4.2
> x-random: hellooooo world
> Accept: *
 */

public abstract class HTTPRequest {
    private HTTPRequestHeader requestHeader;
    private ArrayList<HTTPHeader> headers;
    private byte[] body;
    private WGDGUrl_Base target;
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
    public byte[] getBody() {
        return body;
    }
    public void setBody(byte[] body) {
        this.body = body;
    }
    public WGDGUrl_Base getTarget() {
        return target;
    }
    public void setTarget(WGDGUrl_Base target) {
        this.target = target;
    }
    public HTTPRequest() {
        this.setHeaders(new ArrayList<HTTPHeader>());
        this.setRequestHeader(new HTTPRequestHeader());
    }
    public HTTPRequest(WGDGUrl_Base target, HTTPRequestHeader requestHeader, ArrayList<HTTPHeader> headers, byte[] body) {
        this.setBody(body);
        this.setHeaders(headers);
        this.setRequestHeader(requestHeader);
        this.setTarget(target);
    }
    public HTTPRequest(WGDGUrl_Base target, ArrayList<HTTPHeader> headers) {
        this.setBody(null);
        this.setHeaders(headers);
        this.setRequestHeader(new HTTPRequestHeader());
        this.setTarget(target);
    }
    public HTTPRequest(WGDGUrl_Base target) {
        this.setBody(null);
        this.setHeaders(new ArrayList<HTTPHeader>());
        this.setRequestHeader(new HTTPRequestHeader());
        this.setTarget(target);
    }
    public void addHeader(HTTPHeader header){
        this.headers.add(header);
    }
    public void addHeader(String name, String value){
        this.headers.add(new HTTPHeader(name, value));
    }
    public void setPath(String path){
        this.requestHeader.setPath(path);
    }
    public void setMethod(String method){
        this.requestHeader.setMethod(method);
    }
    public void setMethod(HTTPMethods method){
        this.requestHeader.setMethod(method);
    }
    public void setQueryParams(HTTPQueryParams queryParams){
        this.requestHeader.setQueryParams(queryParams);
    }
    public void addQueryParams(HTTPQueryParam queryParam){
        this.requestHeader.getQueryParams().add(queryParam);
    }
    public void addQueryParams(String name, String value){
        this.requestHeader.getQueryParams().add(new HTTPQueryParam(name, value));
    }
    public void setHTTPVersion(HTTPVersion version){
        this.requestHeader.setVersion(version);
    }
    public HTTPRequest(String host, int port, String path, String method){
        this.setTarget(new WGDGUrl_Base(host, port));
        this.setRequestHeader(new HTTPRequestHeader(method, HTTPVersion.HTTP1_1, path));
        this.setHeaders(new ArrayList<HTTPHeader>());
    }
    public abstract HTTPResponse send() throws Exception;
}
