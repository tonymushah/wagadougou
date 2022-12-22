package frontend.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import frontend.classes.TreeItems.RequestCollectionTreeItem;
import frontend.classes.panes.ErrorPanel;
import frontend.classes.panes.HTTPClientPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mg.wagadougou.lib.classes.clients.AbstractClient;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.misc.RequestFolder;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.enums.HTTPMethods;

public class Wagadougou_App extends Application {
    private static RequestCollection to_use;
    private static RequestCollectionTreeItem to_useRequestCollectionTreeItem;
    private static File to_useCurrentFile;

    public static File getTo_useCurrentFile() {
        return to_useCurrentFile;
    }

    public static void setTo_useCurrentFile(File to_useCurrentFile) {
        Wagadougou_App.to_useCurrentFile = to_useCurrentFile;
    }

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
        Wagadougou_App.setTo_useClient(to_use.getClient());
    }

    public static AbstractClient getTo_useClient() {
        return to_use.getClient();
    }

    public static void setTo_useClient(AbstractClient to_useClient) {
        Wagadougou_App.to_use.setClient(to_useClient);
    }

    public Node defaultScene(Stage primaryStage) {
        primaryStage.setTitle(Wagadougou_App.getTo_useCollection().getName());
        GridPane root = new GridPane();
        root.add(this.namePane(primaryStage), 0, 0);
        root.add(new HTTPClientPane(Wagadougou_App.to_use.getClient()), 0, 1);
        Wagadougou_App.setTo_useRequestCollectionTreeItem(
                new RequestCollectionTreeItem(Wagadougou_App.getTo_useCollection()));

        root.add((new TreeView<String>(Wagadougou_App.getTo_useRequestCollectionTreeItem())), 0, 2);
        return root;
    }

    public void setDefaultScene(Stage primaryStage) {
        final Wagadougou_App this_ = this;
        GridPane root = new GridPane();
        root.add(this.top_menu(primaryStage), 0, 0);
        root.add(this.defaultScene(primaryStage), 0, 1);
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(root);
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            final KeyCombination combination = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_ANY);

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                if (combination.match(arg0)) {
                    this_.saveToFile_Def(primaryStage);
                }
            }

        });
        primaryStage.setScene(scene);
    }

    public Scene suspenseScene() {
        GridPane root = new GridPane();
        Text text = new Text("Loading...");
        text.setFont(Font.font(50));
        text.setTextAlignment(TextAlignment.CENTER);
        root.add(text, 0, 0);
        root.setAlignment(Pos.CENTER);
        return new Scene(root, 250, 250);
    }

    public void suspense(Stage primaryStage) {
        primaryStage.setScene(this.suspenseScene());
    }

    public GridPane namePane(Stage primaryStage) {
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
                ((TextField) Wagadougou_App.getTo_useRequestCollectionTreeItem().getGraphic())
                        .setText(Wagadougou_App.getTo_useCollection().getName());
            }

        });
        root.add(nameField, 1, 0);
        return root;
    }

    public Node exception_scene(Exception exception) {
        return new ScrollPane(new ErrorPanel(exception));
    }

    public void showException(Stage primaryStage, Exception exception) {
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.add(this.top_menu(primaryStage), 0, 0);
        root.add(this.exception_scene(exception), 0, 1);
        primaryStage.setScene(new Scene(root));
    }

    public void openFile(Stage primaryStage, File to_useFile) {
        this.suspense(primaryStage);
        Wagadougou_App.setTo_useCurrentFile(to_useFile);
        try {
            Wagadougou_App.setTo_useCollection(RequestCollection.from(to_useFile));
            this.setDefaultScene(primaryStage);
        } catch (Exception e) {
            // TODO: handle exception
            this.showException(primaryStage, e);
        }
    }

    public void saveToFile(File to_use) throws FileNotFoundException, IOException {
        Wagadougou_App.getTo_useCollection().export_to(to_use);
    }

    public void saveToFileDefault() throws FileNotFoundException, IOException {
        Wagadougou_App.getTo_useCollection().export_to(Wagadougou_App.getTo_useCurrentFile());
    }

    public void defaultCollection() {
        RequestCollection collection = new RequestCollection("Untitled Collection", new HTTPClient("localhost", 8080))
                .addRequest(new InsomniaRequestBase("/", HTTPMethods.GET.name()));
        Wagadougou_App.setTo_useCollection(collection);
    }

    public void home_(final Stage primaryStage) {
        final Wagadougou_App this_ = this;
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.CENTER);

        Text title = new Text("Welcome to Wagadougou");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(new Font(25));
        Text desc = new Text("A HTTP Client builded in Java");
        desc.setTextAlignment(TextAlignment.CENTER);
        root.add(title, 0, 0, 2, 1);
        root.add(desc, 0, 1, 2, 1);

        Button open_file = new Button("Open a file");

        open_file.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                this_.suspense(primaryStage);
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setInitialDirectory(new File("."));
                    this_.openFile(primaryStage, fileChooser.showOpenDialog(primaryStage));

                } catch (Exception e) {
                    // TODO: handle exception
                    this_.showException(primaryStage, e);
                }
            }

        });

        Button create_new = new Button("Create new request collection");

        create_new.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                this_.suspense(primaryStage);
                this_.initNew(primaryStage);
            }
            
        });

        root.add(open_file, 0, 2);

        root.add(create_new, 1, 2);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Welcome to Wagadougou");
    }

    public MenuBar top_menu(Stage primaryStage) {
        final Wagadougou_App this_ = this;
        MenuBar root = new MenuBar();

        MenuItem item1 = new MenuItem("Go home");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                this_.suspense(primaryStage);
                this_.home_(primaryStage);
            }

        });

        Menu home = new Menu("Wagadougou");
        home.getItems().add(item1);
        root.getMenus().add(home);
        return root;
    }

    public void initNew(final Stage primaryStage) {
        Wagadougou_App.setTo_useCurrentFile(null);
        this.defaultCollection();
        this.setDefaultScene(primaryStage);
    }

    public void saveToFile_Def(final Stage primaryStage) {
        try {
            try {
                this.saveToFileDefault();
            } catch (RuntimeException e) {
                // TODO: handle exception
                FileChooser chooser = new FileChooser();
                Wagadougou_App.setTo_useCurrentFile(chooser.showSaveDialog(primaryStage));
                this.saveToFileDefault();
            }
            Date now = new Date();
            primaryStage.setTitle("Saved " + Wagadougou_App.getTo_useCollection().getName() + " at " + now.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            this.showException(primaryStage, e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        this.home_(primaryStage);
        primaryStage.show();
    }

    public RequestCollection tRequestCollection() {
        return new RequestCollection("Tony Collection", new HTTPClient("localhost", 8082)).addFolder(
                new RequestFolder("Tony")
                        .setName("")
                        .addFolder(
                                new RequestFolder("Get")
                                        .setName("")
                                        .addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                                                .setId("Send data 1")
                                                .setName("Just send some data")
                                                .setPath("/")))
                                        .addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                                                .setId("Send data 2")
                                                .setName("Just send some data")
                                                .setPath("/")
                                                .addHeader("sdad", "sdasdasd")
                                                .setBody("wqqewqeq".getBytes())
                                                .setMethod(HTTPMethods.POST))))
                        .addFolder(
                                new RequestFolder("Get2")
                                        .setName("")
                                        .addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                                                .setId("Send data 3")
                                                .setName("Just send some data")
                                                .setPath("/")))
                                        .addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                                                .setId("Send data 4")
                                                .setName("Just send some data")
                                                .setPath("/")
                                                .addHeader("sdad", "sdasdasd")
                                                .setBody("wqqewqeq".getBytes())
                                                .setMethod(HTTPMethods.POST))))
                        .addRequest((HTTPRequestElement) (new InsomniaRequestBase()
                                .setId("HELLO"))));
    }
}
