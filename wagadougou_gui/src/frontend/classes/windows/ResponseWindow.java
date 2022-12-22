package frontend.classes.windows;

import frontend.classes.Wagadougou_App;
import frontend.classes.panes.ErrorPanel;
import frontend.classes.panes.Response_Panel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.classes.responseTypes.InsomniaResponse;

public class ResponseWindow extends Stage {
    private InsomniaRequestBase requestBase;
    private InsomniaResponse response;
    public InsomniaRequestBase getRequestBase() {
        return requestBase;
    }
    public void setRequestBase(InsomniaRequestBase requestBase) {
        this.requestBase = requestBase;
    }
    public InsomniaResponse getResponse() {
        return response;
    }
    public void setResponse(InsomniaResponse response) {
        this.response = response;
    }
    public Scene suspense_scene(){
        GridPane root = new GridPane();
        Text text = new Text("Sending request...");
        text.setTextAlignment(TextAlignment.CENTER);
        root.add(text, 0, 0);
        root.setAlignment(Pos.CENTER);
        return new Scene(root, 250 , 250);
    }
    public Scene response_Scene(){
        return new Scene(new ScrollPane(new Response_Panel(this.getResponse())));
    }
    public Scene exception_scene(Exception exception){
        return new Scene(new ScrollPane(new ErrorPanel(exception)));
    }
    public void send(){
        this.setScene(this.suspense_scene());
        try {
            this.setResponse((InsomniaResponse) Wagadougou_App.getTo_useClient().send(this.requestBase));
            this.setScene(this.response_Scene());
        } catch (Exception e) {
            //TODO: handle exception
            this.setScene(this.exception_scene(e));
        }
    }
    public ResponseWindow(InsomniaRequestBase requestBase) {
        this.setTitle(requestBase.getId() + " response");
        this.setScene(this.suspense_scene());
        this.show();
        this.setRequestBase(requestBase);
        this.send();
    }
}
