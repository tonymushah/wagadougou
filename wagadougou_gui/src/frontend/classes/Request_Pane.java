package frontend.classes;

import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequest;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.enums.HTTPMethods;

public class Request_Pane extends GridPane {
    private Parent header_scene;
    private Parent query_scene;
    private InsomniaRequestBase to_use;
    private Button send_button;

    public Parent getQuery_scene() {
        return query_scene;
    }

    public void setQuery_scene(Parent query_scene) {
        this.query_scene = query_scene;
    }

    public Parent getHeader_scene() {
        return header_scene;
    }

    public void setHeader_scene(Parent header_scene) {
        this.header_scene = header_scene;
    }

    public InsomniaRequestBase getTo_use() {
        return to_use;
    }

    public void setTo_use(InsomniaRequestBase to_use) {
        this.to_use = to_use;
    }

    public Button getSend_button() {
        return send_button;
    }

    public void setSend_button(Button send_button) {
        this.send_button = send_button;
    }

    public Request_Pane(InsomniaRequestBase to_use) {
        this.setTo_use(to_use);
        this.setHeader_scene(new ScrollPane(new HeadersPane(this.to_use.getHeaders())));
        this.setQuery_scene(new ScrollPane(new QueriesPane(this.to_use.getRequestHeader().getQueryParams())));
        this.launch_graph();
    }

    public void launch_graph() {
        final InsomniaRequestBase requestBase = this.to_use;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        // TextField method = new TextField(this.to_use.getRequestHeader().getMethod());
        ComboBox<HTTPMethods> comboBox = new ComboBox<HTTPMethods>();
        comboBox.getItems().addAll(HTTPMethods.values());
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                requestBase.getRequestHeader().setMethod(comboBox.getValue());
            }
        });
        this.add(comboBox, 0, 0);
        // this.add(method, 0, 0);
        TextField path = new TextField(this.to_use.getRequestHeader().getPath());
        path.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                if (path.getText().contains("?")) {
                    path.setText(requestBase.getRequestHeader().getPath());
                }
                requestBase.getRequestHeader().setPath(path.getText());
            }
        });
        this.add(path, 1, 0);
        this.setSend_button(new Button("Send"));
        this.add(this.send_button, 2, 0);

        TabPane tabPane = new TabPane();
        tabPane.setPrefHeight(500);
        
        Tab header_pane = new Tab("Headers", this.header_scene);
        header_pane.setClosable(false);

        Tab query_pane = new Tab("Queries", this.query_scene);
        query_pane.setClosable(false);

        Tab body_pane = new Tab("Body", this.bodyPane());
        body_pane.setClosable(false);

        tabPane.getTabs().add(header_pane);
        tabPane.getTabs().add(query_pane);
        tabPane.getTabs().add(body_pane);

        this.add(tabPane, 0, 1, 3, 1);
    }
    public Node bodyPane(){
        final HTTPRequest request = this.to_use;
        TextArea body;
        try {
            body = new TextArea(new String(this.to_use.getBody()));
        } catch (Exception e) {
            //TODO: handle exception
            body = new TextArea("");
        }
        final TextArea body_ = body;
        body.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                request.setBody(body_.getText().getBytes());
            }
            
        });
        return body;
    }
}
