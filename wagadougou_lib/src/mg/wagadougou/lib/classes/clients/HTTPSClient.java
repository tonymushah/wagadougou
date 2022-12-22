package mg.wagadougou.lib.classes.clients;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.utils.WGDGUrl_Base;

public class HTTPSClient extends AbstractClient {
    public SSLSocketFactory getSocketFactory() {
        return (SSLSocketFactory) SSLSocketFactory.getDefault();
    }

     public HTTPSClient(WGDGUrl_Base url) {
        super(url);
    }
    public HTTPSClient(String hostname, int port){
        super(new WGDGUrl_Base(hostname, port));
    }
    @Override
    public Socket getSocket() throws UnknownHostException, IOException{
        return (SSLSocket) (this.getSocketFactory().createSocket(this.getUrl().getHostname(), this.getUrl().getPort()));
    }
    @Override
    public HTTPResponse send(HTTPRequest request) throws Exception {
        // TODO Auto-generated method stub
        SSLSocket to_use = (SSLSocket) this.getSocket();
        to_use.startHandshake();
        HTTPResponse response = request.send(to_use);
        to_use.close();
        return response;
    }
    
}
