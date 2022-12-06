package classes.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import classes.requestTypes.base.HTTPRequest;
import classes.responseTypes.HTTPResponse;
import classes.utils.WGDGUrl_Base;

public class HTTPClient extends AbstractClient{
    private SocketFactory socketFactory;
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }
    public HTTPClient setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this;
    }
    public HTTPClient(WGDGUrl_Base url) {
        super(url);
        this.setSocketFactory(SocketFactory.getDefault());
    }
    public HTTPClient(WGDGUrl_Base url, SocketFactory socketFactory) {
        super(url);
        this.setSocketFactory(socketFactory);
    }
    public HTTPClient(String hostname, int port){
        super(new WGDGUrl_Base(hostname, port));
        this.setSocketFactory(SocketFactory.getDefault());
    }
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
