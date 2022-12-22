package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;


public class DeserealisationTest {
    public static void main(String[] args) throws Exception {
        File data = new File("./collection.wgdg");
        RequestCollection collection = RequestCollection.from(data);
        InsomniaRequestBase request =  (InsomniaRequestBase) collection.getRequestByID("test_post");
        InsomniaResponse response =  (InsomniaResponse) collection.send(request);
        BufferedReader timeline = new BufferedReader(new InputStreamReader(response.getTimeline()));
        for (String string : timeline.lines().toList()) {
            System.out.println(string);
        }
    }
}
