package mg.wagadougou.lib.classes.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.utils.WGDGUrl_Base;

public class HTTPClient extends AbstractClient{
    public final String type = "HTTP";
    @JsonIgnore
    public SocketFactory getSocketFactory() {
        return SocketFactory.getDefault();
    }
    public HTTPClient(WGDGUrl_Base url) {
        super(url);
    }
    public HTTPClient(String hostname, int port){
        super(new WGDGUrl_Base(hostname, port));
    }
    @Override
    public Socket getSocket() throws UnknownHostException, IOException{
        return this.getSocketFactory().createSocket(this.getUrl().getHostname(), this.getUrl().getPort());
    }
    @Override
    public HTTPResponse send(HTTPRequest request) throws Exception{
        Socket to_use = this.getSocket();
        HTTPResponse response = request.send(to_use);
        to_use.close();
        return response;
    }
}
