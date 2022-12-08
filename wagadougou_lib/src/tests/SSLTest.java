package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.requestTypes.element.HTTPRequestDefault;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;
import mg.wagadougou.lib.enums.HTTPVersion;

public class SSLTest {
    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("api.mangadex.org");
        HTTPRequest request = new InsomniaRequestBase("/manga", "GET");
        request.addHeader("Host", "api.mangadex.org");
        request.addQueryParams("title", "tonikaku");
        request.addHeader("user-agent", "wagadougou/2022.0.0.1");
        request.addHeader("x-random", "helloooo world");
        request.addHeader("accept", "*/*");
        request.setHTTPVersion(HTTPVersion.HTTP1_1);
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket target = (SSLSocket) factory.createSocket(address, 443);
        target.startHandshake();
        InsomniaResponse response2 = (InsomniaResponse) request.send(target);
        target.close();
        for (String line : (new BufferedReader(new InputStreamReader(response2.getTimeline()))).lines().toList()) {
            System.out.println(line);
        }
        System.out.println(( response2).getDuration() + " ms");
    }
}
