package frontend.classes.windows;

import frontend.classes.panes.Request_Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;

public class RequestWindow extends Stage {
    private InsomniaRequestBase to_use;
    private Request_Pane to_usePane;

    public Request_Pane getTo_usePane() {
        return to_usePane;
    }

    public void setTo_usePane(Request_Pane to_usePane) {
        this.to_usePane = to_usePane;
    }

    public InsomniaRequestBase getTo_use() {
        return to_use;
    }

    public void setTo_use(InsomniaRequestBase to_use) {
        this.to_use = to_use;
    }

    public RequestWindow(InsomniaRequestBase to_use) {
        this.setTo_use(to_use);
        this.setTitle(to_use.getId());
        this.setTo_usePane(new Request_Pane(this.getTo_use()));
        //this.setScene(new Scene(this.to_usePane));
        final RequestWindow this_ = this;
        this.to_usePane.getSend_button().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new ResponseWindow(this_.to_use);
            }
            
        });
        this.launch_graph();
    }
    public GridPane name_editPane(){
        final RequestWindow this_ = this;
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.add(new Text("Name : "), 0, 0);
        TextField name_Field = new TextField();
        root.add(name_Field, 1, 0);
        name_Field.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                this_.to_use.setName(name_Field.getText());
            }
            
        });
        return root;
    }
    public void launch_graph(){
        final RequestWindow this_ = this;
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.add(this.name_editPane(), 0, 0);
        TabPane top_content = new TabPane();

        Tab request = new Tab("Request Details", this.to_usePane);
        request.setClosable(false);

        TextArea doc_Field = new TextArea(this.to_use.getDoc());
        doc_Field.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                this_.to_use.setDoc(doc_Field.getText());
            }
            
        });

        Tab doc = new Tab("Documentation", doc_Field);
        doc.setClosable(false);

        top_content.getTabs().add(request);
        top_content.getTabs().add(doc);

        root.add(top_content, 0, 1);
        this.setScene(new Scene(root));
    }
}
