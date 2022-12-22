package mg.wagadougou.lib.classes.requestTypes.element;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import mg.wagadougou.lib.classes.headers.HTTPHeader;
import mg.wagadougou.lib.classes.headers.HTTPRequestHeader;
import mg.wagadougou.lib.classes.headers.HTTPResponseHeader;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.responseTypes.HTTPResponse;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;

public class InsomniaRequestBase extends HTTPRequestElement {
    public InsomniaRequestBase() {
        super();
    }

    public InsomniaRequestBase(HTTPRequestHeader requestHeader, ArrayList<HTTPHeader> headers, byte[] body) {
        super(requestHeader, headers, body);
    }

    public InsomniaRequestBase(ArrayList<HTTPHeader> headers) {
        super(headers);
    }

    public InsomniaRequestBase(String path, String method) {
        super(path, method);
    }

    @Override
    public HTTPResponse send(Socket target) throws Exception {
        // TODO Auto-generated method stub
        InsomniaResponse response = new InsomniaResponse();
        response.setStartTime((new Date()).getTime());
        PrintStream timeline = response.getTimeLine_PrintStream();
        PrintStream out = new PrintStream(target.getOutputStream());

        timeline.println("// sending request");
        out.println(this.getRequestHeader());
        timeline.println(this.getRequestHeader());

        //System.out.println(this.getRequestHeader());

        if(this.getHeaders() != null){
            for (HTTPHeader header : this.getHeaders()) {
                out.println(header);
                timeline.println(header);
            }
        }
        out.println();
        timeline.println();

        if(this.getBody() != null){
            out.writeBytes(this.getBody());
            timeline.write(this.getBody());
        }
        timeline.println("\n// flushing output stream");
        out.flush();
        timeline.println("// shuting down output stream");
        target.shutdownOutput();
        timeline.println("// retreiving response");
        BufferedReader in =  new BufferedReader(new InputStreamReader(target.getInputStream()));
        Stream<String> getted = in.lines();
        boolean is_at_body = false;
        ByteArrayOutputStream body = new ByteArrayOutputStream();
        List<String> response_string = getted.toList();
        for (int i = 0; i < response_string.size(); i++) {
            if(response_string.get(i).compareTo("") == 0){
                is_at_body = true;
            }else{
                if(i == 0){
                    HTTPResponseHeader responseHeader= new HTTPResponseHeader(response_string.get(i));
                    response.setResponseHeader(responseHeader);
                    timeline.println(responseHeader);
                }else if(i != 0 && is_at_body == false){
                    HTTPHeader header = HTTPHeader.valueOf(response_string.get(i));
                    response.addHeader(header);
                    timeline.println(header);
                }else{
                    timeline.println(response_string.get(i));
                    body.write(response_string.get(i).getBytes());
                    body.write("\n".getBytes());
                }
            }
            
        }
        response.setBody(body.toByteArray());
        response.setTarget(target.getInetAddress());
        response.setEndTime((new Date()).getTime());
        return response;
    }
}
