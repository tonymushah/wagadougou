package frontend.classes.panes;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import mg.wagadougou.lib.classes.utils.HTTPQueryParam;

public class QueriesPane extends GridPane{
    private ArrayList<HTTPQueryParam> queries;
    private Button add;
    public ArrayList<HTTPQueryParam> getQueries() {
        return queries;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }

    public void setQueries(ArrayList<HTTPQueryParam> queries) {
        this.queries = queries;
    }

    public QueriesPane(ArrayList<HTTPQueryParam> queries) {
        this.setQueries(queries);
        this.setAdd(new Button("Add Query Params"));
        final QueriesPane this_ = this;
        this.add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                queries.add(new HTTPQueryParam("", ""));
                this_.launch_graph();
            }
        });
        this.launch_graph();
    }

    public void launch_graph(){
        int row = 0;
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().add(this.add);
        this.add(btnBox, 0, row, 2, 1);
        row++;
        int header_index = 0;
        final QueriesPane this_ = this;
        for (HTTPQueryParam queryParam : queries) {
            final int index = header_index;
            this.add(new QueryPane(queryParam), 0, row);
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
        }
        
    }

    public void delete_header(int index) {
        this.queries.remove(index);
        this.getChildren().clear();
        this.launch_graph();
    }
}
