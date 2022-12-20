package frontend.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;

public class Response_Panel extends GridPane {
    private InsomniaResponse response;

    public InsomniaResponse getResponse() {
        return response;
    }

    public void setResponse(InsomniaResponse response) {
        this.response = response;
    }

    public Response_Panel(InsomniaResponse response) {
        this.setResponse(response);
        this.launch_graph();
    }
    public void launch_graph(){
        this.setAlignment(Pos.TOP_CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setWidth(250);
        this.setPadding(new Insets(5, 25, 5, 25));
        this.add(new Text(String.valueOf(this.response.getResponseHeader())), 0, 0);
        this.add(new Text("Duration : " + this.response.getDuration() + "ms"), 0, 1);

        TabPane tabPane = new TabPane();
        
        Tab body = new Tab("Body", new ScrollPane(this.getBody()));
        body.setClosable(false);
        
        Tab headers = new Tab("Headers", new HeadersPaneReadOnly(this.response.getHeaders()));
        headers.setClosable(false);

        Tab timeline = new Tab("Timeline", new ScrollPane(this.getTimeline()));
        timeline.setClosable(false);
        
        tabPane.getTabs().add(body);
        tabPane.getTabs().add(headers);
        tabPane.getTabs().add(timeline);

        this.add(tabPane, 0, 2);
    }
    public TextFlow getBody(){
        TextFlow textFlow = new TextFlow();
        try (BufferedReader body = new BufferedReader(new InputStreamReader(this.response.getBodyStream()))) {
            for (String line : body.lines().toList()) {
                textFlow.getChildren().add(new Text(line + "\n"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return textFlow;
    }
    public TextFlow getTimeline(){
        TextFlow textFlow = new TextFlow();
        try (BufferedReader body = new BufferedReader(new InputStreamReader(this.response.getTimeline()))) {
            for (String line : body.lines().toList()) {
                textFlow.getChildren().add(new Text(line + "\n"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return textFlow;
    }
}
