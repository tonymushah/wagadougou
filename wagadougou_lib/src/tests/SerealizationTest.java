package tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.enums.HTTPMethods;

public class SerealizationTest {
    public static void main(String[] args) throws IOException {
        File data = new File("./collection.wgdg");
        data.createNewFile();
        byte[] bodyActix0 = "sdjhadasdads".getBytes();
            RequestCollection collection2 = new RequestCollection("Actix collection", new HTTPClient("localhost", 8082))
                .addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("HELLO")
                .addHeader("Host", "localhost:8082")
                .addHeader("user-agent", "wagadoudou/2022.0.0.1")
            ).addRequest((HTTPRequestElement) new InsomniaRequestBase()
                .setId("test_post")
                .setMethod(HTTPMethods.POST)
                .setBody(bodyActix0)
                .setPath("/test_post")
                .addHeader("Host", "localhost:8082")
                .addHeader("user-agent", "wagadoudou/2022.0.0.1")
                .addHeader("content-type", "text/plain")
                .addHeader("content-length", "" + bodyActix0.length)
            );
        collection2.export_to(data);
    }
}
