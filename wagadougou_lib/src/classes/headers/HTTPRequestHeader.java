package classes.headers;

import classes.utils.HTTPQueryParams;
import enums.HTTPMethods;
import enums.HTTPVersion;

public class HTTPRequestHeader {
    private HTTPQueryParams queryParams;
    private String method;
    private HTTPVersion version;
    private String path;
    public HTTPQueryParams getQueryParams() {
        return queryParams;
    }
    public void setQueryParams(HTTPQueryParams queryParams) {
        this.queryParams = queryParams;
    }
    public String getMethod() {
        if(method == null || method.compareTo("") == 0 ){
            return HTTPMethods.GET.toString();
        }else{
            return method;
        }
        
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public HTTPVersion getVersion() {
        if(version == null){
            return HTTPVersion.HTTP1_1;
        }else{
            return version;
        }
    }
    public void setVersion(HTTPVersion version) {
        this.version = version;
    }
    public String getPath() {
        if(this.path == null || this.path.compareTo("") == 0){
            return "/";
        }else{
            return path;
        }
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setMethod(HTTPMethods methods){
        this.setMethod(methods.toString());
    }
    public HTTPRequestHeader() {
        this.setQueryParams(new HTTPQueryParams());
    }
    public HTTPRequestHeader(String method, HTTPVersion version, String path) {
        this();
        this.setMethod(method);
        this.setPath(path);
        this.setVersion(version);
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        try {
            if(queryParams.isEmpty()){
                return this.getMethod() + " " + this.getPath() + " " + this.getVersion();
            }else{
                return this.getMethod() + " " + this.getPath() + "?" + this.getQueryParams() + " " + this.getVersion();
            }
        } catch (Exception e) {
            //TODO: handle exception
            throw new NullPointerException(e.getMessage());
        }
    }
}
