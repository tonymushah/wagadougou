package classes.clients;

import classes.requestTypes.HTTPRequest;
import classes.responseTypes.HTTPResponse;
import classes.utils.WGDGUrl_Base;

public abstract class AbstractClient {
    private WGDGUrl_Base url;
     public WGDGUrl_Base getUrl() {
        return url;
    }
    public void setUrl(WGDGUrl_Base url) {
        this.url = url;
    }
    public AbstractClient(WGDGUrl_Base url){
        this.setUrl(url);
    }
    public abstract HTTPResponse send(HTTPRequest request) throws Exception;
}
