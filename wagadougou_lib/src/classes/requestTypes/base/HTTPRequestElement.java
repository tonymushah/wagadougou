package classes.requestTypes.base;

import java.util.ArrayList;

import classes.headers.HTTPHeader;
import classes.headers.HTTPRequestHeader;

/**
 * HTTPRequestElement
 */
public abstract class HTTPRequestElement extends HTTPRequest {
    private String name;
    private String id;
    private String doc;
    public String getName() {
        return name;
    }
    public HTTPRequestElement setName(String name) {
        this.name = name;
        return this;
    }
    public String getId() {
        return id;
    }
    public HTTPRequestElement setId(String id) {
        this.id = id;
        return this;
    }
    public String getDoc() {
        return doc;
    }
    public HTTPRequestElement setDoc(String doc) {
        this.doc = doc;
        return this;
    }
    public HTTPRequestElement() {
        super();
    }
    public HTTPRequestElement(HTTPRequestHeader requestHeader, ArrayList<HTTPHeader> headers, byte[] body) {
        super(requestHeader, headers, body);
    }
    public HTTPRequestElement(ArrayList<HTTPHeader> headers) {
        super(headers);
    }
    public HTTPRequestElement(String path, String method) {
        super(path, method);
    }
}
