package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.requestTypes.element.HTTPRequestDefault;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.enums.HTTPVersion;

public class SSLTest {
    public static void main(String[] args) throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket target = (SSLSocket) factory.createSocket("api.mangadex.org", 443);
        HTTPRequest request = new HTTPRequestDefault("/manga", "GET");
        request.addHeader("Host", "api.mangadex.org");
        request.addQueryParams("title", "tonikaku");
        request.addHeader("user-agent", "wagadougou/2022.0.0.1");
        request.addHeader("accept", "*/*");
        request.setHTTPVersion(HTTPVersion.HTTP1_1);
        target.startHandshake();
        HTTPResponse response2 = request.send(target);
        target.close();
        System.out.println(response2.getResponseHeader());
        for (HTTPHeader header : response2.getHeaders()) {
            System.out.println(header);
        }
        for (String line : (new BufferedReader(new InputStreamReader(response2.getBodyStream()))).lines().toList()) {
            System.out.println(line);
        }
    }
}
