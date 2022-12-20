package frontend.classes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;

public class Wagadougou_App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        InsomniaRequestBase requestBase = new InsomniaRequestBase();
        HTTPClient client = new HTTPClient("localhost", 8082);
        primaryStage.setTitle("Request");

        HTTPClientPane clientPane = new HTTPClientPane(client);
        Request_Pane request_Pane = new Request_Pane(requestBase);

        

        final GridPane root = new GridPane();
        root.add(clientPane, 0, 0, 2, 1);
        root.add(request_Pane, 0, 1, 1, 1);
        request_Pane.getSend_button().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    root.add(new Response_Panel((InsomniaResponse) clientPane.getClient().send(request_Pane.getTo_use())), 1, 1);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    root.add(new ScrollPane(new ErrorPanel(e)), 1, 1);
                }
            }
            
        });
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Wagadougou_App.launch(args);
    }
}
