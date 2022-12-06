package classes.clients;

import classes.requestTypes.base.HTTPRequest;
import classes.responseTypes.HTTPResponse;
import classes.utils.WGDGUrl_Base;

public abstract class AbstractClient {
    private WGDGUrl_Base url;
     public WGDGUrl_Base getUrl() {
        return url;
    }
    public AbstractClient setUrl(WGDGUrl_Base url) {
        this.url = url;
        return this;
    }
    public AbstractClient(WGDGUrl_Base url){
        this.setUrl(url);
    }
    public abstract HTTPResponse send(HTTPRequest request) throws Exception;
}
