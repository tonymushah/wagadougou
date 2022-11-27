package classes.requestTypes;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import classes.headers.HTTPHeader;
import classes.headers.HTTPRequestHeader;
import classes.headers.HTTPResponseHeader;
import classes.responseTypes.HTTPResponse;

public class HTTPRequestDefault extends HTTPRequest {
    
    public HTTPRequestDefault() {
    }

    public HTTPRequestDefault(HTTPRequestHeader requestHeader, ArrayList<HTTPHeader> headers, byte[] body) {
        super(requestHeader, headers, body);
    }

    public HTTPRequestDefault(ArrayList<HTTPHeader> headers) {
        super(headers);
    }

    public HTTPRequestDefault(String path, String method) {
        super(path, method);
    }

    @Override
    public HTTPResponse send(Socket target) throws Exception {
        // TODO Auto-generated method stub

        PrintStream out = new PrintStream(target.getOutputStream());

        out.println(this.getRequestHeader());

        if(this.getHeaders() != null){
            for (HTTPHeader header : this.getHeaders()) {
                out.println(header);
            }
        }
        out.println();

        if(this.getBody() != null){
            out.writeBytes(this.getBody());
        }
        out.flush();
        HTTPResponse response = new HTTPResponse();
        BufferedReader in =  new BufferedReader(new InputStreamReader(target.getInputStream()));
        Stream<String> getted = in.lines();
        boolean is_at_body = false;
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        List<String> response_string = getted.toList();
        for (int i = 0; i < response_string.size(); i++) {
            if(response_string.get(i).compareTo("") == 0){
                is_at_body = true;
            }
            if(i == 0){
                response.setResponseHeader(new HTTPResponseHeader(response_string.get(i)));
            }else if(i != 0 && is_at_body == false){
                response.addHeader(HTTPHeader.valueOf(response_string.get(i)));
            }else{
                body.write(response_string.get(i).getBytes());
            }
        }
        response.setBody(body.toByteArray());
        response.setTarget(target.getInetAddress());
        return response;
    }
}
