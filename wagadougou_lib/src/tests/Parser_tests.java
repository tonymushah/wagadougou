package tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import mg.wagadougou.lib.classes.headers.HTTPRequestHeader;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;
import mg.wagadougou.lib.classes.utils.HTTPQueryParam;
import mg.wagadougou.lib.classes.utils.HTTPQueryParams;
import mg.wagadougou.lib.classes.utils.UrlParser;
import mg.wagadougou.lib.enums.HTTPMethods;

public class Parser_tests {
    public static void main(String[] args) throws Exception {
        //System.out.println(HTTPQueryParams.valueOf("to_use=dsadads&fsdfsd=fdsfoop").toString() + "\sadsda");
        //System.out.println(HTTPRequestHeader.valueOf("GET /?toUse=dsadd HTTP/1.1"));
        String dsads = "http://localhost:8080/hello?name=tony";
        UrlParser parser = new UrlParser(dsads);
        InsomniaRequestBase requestBase = new InsomniaRequestBase(parser.getPath(), HTTPMethods.GET.toString());
        InsomniaResponse response = (InsomniaResponse) parser.getClient().send(requestBase);
        System.out.println(response.getDuration() + " ms");
        System.out.println(response.getResponseHeader());
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBodyStream()));
        for (String string : reader.lines().toList()) {
            System.out.println(string);
        }
    }
}
