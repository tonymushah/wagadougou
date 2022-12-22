package mg.wagadougou.lib.classes.clients;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.utils.WGDGUrl_Base;

public abstract class AbstractClient implements Serializable{
    private WGDGUrl_Base url;
     public WGDGUrl_Base getUrl() {
        return url;
    }
    public AbstractClient setUrl(WGDGUrl_Base url) {
        this.url = url;
        return this;
    }
    public abstract Socket getSocket() throws UnknownHostException, IOException;
    public AbstractClient(WGDGUrl_Base url){
        this.setUrl(url);
    }
    public abstract HTTPResponse send(HTTPRequest request) throws Exception;
}
