package frontend.classes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeaderPaneReadOnly extends GridPane {
    private HTTPHeader header;

    public HTTPHeader getHeader() {
        return header;
    }

    public void setHeader(HTTPHeader header) {
        this.header = header;
    }

    public HeaderPaneReadOnly(HTTPHeader header) {
        this.setHeader(header);
        launch_graph();
    }

    public void launch_graph(){
        final HTTPHeader header = this.header;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 25, 5, 25));

        Text name = new Text(header.getContext());
        this.add(name, 0, 0);

        Text double_dot = new Text(" : ");
        this.add(double_dot, 1, 0);

        Text value = new Text(this.header.getValue());
        this.add(value, 2, 0);
        
    }
}