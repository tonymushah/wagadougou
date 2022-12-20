package frontend.classes;

import java.util.ArrayList;
import java.util.function.Function;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeadersPane extends GridPane{
    private ArrayList<HTTPHeader> headers;
    private Button add;
    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }

    public void setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
    }

    public HeadersPane(ArrayList<HTTPHeader> headers) {
        this.setHeaders(headers);
        this.setAdd(new Button("Add Header"));
        final HeadersPane this_ = this;
        this.add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                headers.add(new HTTPHeader());
                this_.launch_graph();
            }
        });
        this.launch_graph();
    }

    public void delete_header(int index){
        this.headers.remove(index);
        this.getChildren().clear();
        this.launch_graph();
    }

    public void launch_graph(){
        int row = 0;
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().add(this.add);
        this.add(btnBox, 0, row, 2, 1);
        final HeadersPane this_ = this;
        row++;
        int header_index = 0;
        for (HTTPHeader httpHeader : this.headers) {
            final int index = header_index;
            this.add(new HeaderPane(httpHeader), 0, row);
            Button delete_Button = new Button("Delete");
            this.add(delete_Button, 1, row);
            delete_Button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    // TODO Auto-generated method stub
                    this_.delete_header(index);
                }
                
            });
            row++;
            header_index++;
        }
        
    }
}
