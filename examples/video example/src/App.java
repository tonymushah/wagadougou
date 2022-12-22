import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;

/**
 * App
 */
public class App {

    public static void main(String[] args) throws Exception {
        RequestCollection collection = RequestCollection.from(new File("./mycollec.wgdg"));
        InsomniaResponse response = (InsomniaResponse) collection.sendByID("test_post");
        BufferedReader timeline = new BufferedReader(new InputStreamReader(response.getTimeline()));
        for (String string : timeline.lines().toList()) {
            System.out.println(string);
        }
    }
}