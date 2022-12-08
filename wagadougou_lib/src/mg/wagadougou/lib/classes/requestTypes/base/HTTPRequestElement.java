package mg.wagadougou.lib.classes.requestTypes.base;

import java.util.ArrayList;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.headers.HTTPRequestHeader;
import mg.wagadougou.lib.classes.misc.RequestFolder;

/**
 * HTTPRequestElement
 */
public abstract class HTTPRequestElement extends HTTPRequest {
    private String name;
    private String id;
    private String doc;
    private RequestFolder parent;
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
    public RequestFolder getParent() {
        return parent;
    }
    public HTTPRequestElement setParent(RequestFolder parent) {
        this.parent = parent;
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
