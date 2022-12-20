package frontend.classes;

import java.io.UnsupportedEncodingException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import mg.wagadougou.lib.classes.utils.HTTPQueryParam;

public class QueryPane extends GridPane {
    private HTTPQueryParam to_use;

    public HTTPQueryParam getTo_use() {
        return to_use;
    }

    public void setTo_use(HTTPQueryParam to_use) {
        this.to_use = to_use;
    }
    public QueryPane(HTTPQueryParam to_use) {
        this.setTo_use(to_use);
        launch_graph();
    }
    public void launch_graph(){
        final HTTPQueryParam queryParam = this.to_use;
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 25, 5, 25));
        TextField name = new TextField(queryParam.getName());
        this.add(name, 0, 0);
        name.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                queryParam.setName(name.getText());
            }
            
        });
        TextField value;
        try {
            value = new TextField(this.to_use.getValue());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            this.to_use.setValue("");
            value = new TextField("");
        }
        this.add(value, 1, 0);
        final TextField value_ = value;
        value.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                queryParam.setValue(value_.getText());
            }
            
        });
    }
}
