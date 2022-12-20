package frontend.classes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeaderPane extends GridPane {
    private HTTPHeader header;

    public HTTPHeader getHeader() {
        return header;
    }

    public void setHeader(HTTPHeader header) {
        this.header = header;
    }

    public HeaderPane(HTTPHeader header) {
        this.setHeader(header);
        launch_graph();
    }

    public void launch_graph(){
        final HTTPHeader header = this.header;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 25, 5, 25));
        TextField name = new TextField(header.getContext());
        this.add(name, 0, 0);
        name.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                header.setContext(name.getText());
            }
            
        });
        TextField value = new TextField(this.header.getValue());
        this.add(value, 1, 0);
        value.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                header.setValue(value.getText());
            }
            
        });
    }
}
