package frontend.classes.panes;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeadersPaneReadOnly extends GridPane{
    private ArrayList<HTTPHeader> headers;
    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
    }

    public HeadersPaneReadOnly(ArrayList<HTTPHeader> headers) {
        this.setHeaders(headers);
        this.launch_graph();
    }

    public void launch_graph(){
        int row = 0;
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);
        for (HTTPHeader httpHeader : headers) {
            this.add(new HeaderPaneReadOnly(httpHeader), 0, row);
            row++;
        }
        
    }
}
