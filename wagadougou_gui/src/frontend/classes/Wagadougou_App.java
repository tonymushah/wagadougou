package frontend.classes;

import java.util.HashMap;

import frontend.classes.TreeItems.RequestCollectionTreeItem;
import frontend.classes.TreeItems.RequestFolderTreeItem;
import frontend.classes.panes.ErrorPanel;
import frontend.classes.panes.HTTPClientPane;
import frontend.classes.panes.Request_Pane;
import frontend.classes.panes.Response_Panel;
import javafx.application.Application;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mg.wagadougou.lib.classes.clients.AbstractClient;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.misc.RequestFolder;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;
import mg.wagadougou.lib.enums.HTTPMethods;

public class Wagadougou_App extends Application {
    private static RequestCollection to_use;
    private static RequestCollectionTreeItem to_useRequestCollectionTreeItem;

    public static RequestCollectionTreeItem getTo_useRequestCollectionTreeItem() {
        return to_useRequestCollectionTreeItem;
    }

    public static void setTo_useRequestCollectionTreeItem(RequestCollectionTreeItem to_useRequestCollectionTreeItem) {
        Wagadougou_App.to_useRequestCollectionTreeItem = to_useRequestCollectionTreeItem;
    }

    public static RequestCollection getTo_useCollection() {
        return to_use;
    }

    public static void setTo_useCollection(RequestCollection to_use) {
        Wagadougou_App.to_use = to_use;
    }

    public static AbstractClient getTo_useClient() {
        return to_use.getClient();
    }

    public static void setTo_useClient(AbstractClient to_useClient) {
        Wagadougou_App.to_use.setClient(to_useClient);
    }

    public Scene testscene1(){
        InsomniaRequestBase requestBase = new InsomniaRequestBase();
        requestBase.setBody("sakdjabskdajdaasd".getBytes());
        HTTPClient client = new HTTPClient("localhost", 8082);
        
        HTTPClientPane clientPane = new HTTPClientPane(client);
        Request_Pane request_Pane = new Request_Pane(requestBase);

        final HashMap<String, Node> responses = new HashMap<String, Node>();
        
        final GridPane root = new GridPane();
        root.add(clientPane, 0, 0, 2, 1);
        root.add(request_Pane, 0, 1, 1, 1);
        request_Pane.getSend_button().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    responses.put("old", responses.get("current"));
                    responses.put("current", new Response_Panel((InsomniaResponse) clientPane.getClient().send(request_Pane.getTo_use())));
                    root.getChildren().remove(responses.get("old"));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    responses.put("old", responses.get("current"));
                    responses.put("current", new ScrollPane(new ErrorPanel(e)));
                    root.getChildren().remove(responses.get("old"));
                }finally{
                    root.add(responses.get("current"), 1, 1);
                }
            }
            
        });
        
        //root.add(view, 0, 2);
        Scene scene = new Scene(root, 500, 500);
        return scene;
    }

    public GridPane namePane(Stage primaryStage){
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.add(new Label("Name : "), 0, 0);

        TextField nameField = new TextField(Wagadougou_App.getTo_useCollection().getName());
        nameField.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                Wagadougou_App.getTo_useCollection().setName(nameField.getText());
                primaryStage.setTitle(Wagadougou_App.getTo_useCollection().getName());
                ((TextField) Wagadougou_App.getTo_useRequestCollectionTreeItem().getGraphic()).setText(Wagadougou_App.getTo_useCollection().getName());
            }
            
        });
        root.add(nameField, 1, 0);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        GridPane root = new GridPane();
        RequestCollection collection = new RequestCollection("Tony Collection", new HTTPClient("localhost", 8082)).addFolder(
            new RequestFolder("Tony")
                .setName("")
                .addFolder(
                    new RequestFolder("Get")
                        .setName("")
                        .addRequest((HTTPRequestElement) (
                            new InsomniaRequestBase()
                                .setId("Send data 1")
                                .setName("Just send some data")
                                .setPath("/")
                        ))
                        .addRequest((HTTPRequestElement) (
                            new InsomniaRequestBase()
                                .setId("Send data 2")
                                .setName("Just send some data")
                                .setPath("/")
                                .addHeader("sdad", "sdasdasd")
                                .setBody("wqqewqeq".getBytes())
                                .setMethod(HTTPMethods.POST)
                        )
                    )
                )
                .addFolder(
                    new RequestFolder("Get2")
                        .setName("")
                        .addRequest((HTTPRequestElement) (
                            new InsomniaRequestBase()
                                .setId("Send data 3")
                                .setName("Just send some data")
                                .setPath("/")
                        ))
                        .addRequest((HTTPRequestElement) (
                            new InsomniaRequestBase()
                                .setId("Send data 4")
                                .setName("Just send some data")
                                .setPath("/")
                                .addHeader("sdad", "sdasdasd")
                                .setBody("wqqewqeq".getBytes())
                                .setMethod(HTTPMethods.POST)
                        )
                    )
                )
                .addRequest((HTTPRequestElement) (
                    new InsomniaRequestBase()
                        .setId("HELLO")
                )
        ));
        Wagadougou_App.setTo_useCollection(collection);
        
        root.add(this.namePane(primaryStage), 0, 0);
        root.add(new HTTPClientPane(Wagadougou_App.to_use.getClient()), 0, 1);
        Wagadougou_App.setTo_useRequestCollectionTreeItem(new RequestCollectionTreeItem(Wagadougou_App.getTo_useCollection()));

        root.add((new TreeView<String>(Wagadougou_App.getTo_useRequestCollectionTreeItem())), 0, 2);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(Wagadougou_App.getTo_useCollection().getName());
        primaryStage.show();
    }
    public static void main(String[] args) {
        Wagadougou_App.launch(args);
    }
}
