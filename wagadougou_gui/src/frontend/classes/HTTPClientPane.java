package frontend.classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import mg.wagadougou.lib.classes.clients.AbstractClient;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.clients.HTTPSClient;
import mg.wagadougou.lib.classes.utils.WGDGUrl_Base;

public class HTTPClientPane extends GridPane{
    private AbstractClient client;
    private WGDGUrl_Base url_Base;
    public AbstractClient getClient() {
        return client;
    }

    public void setClient(AbstractClient client) {
        this.client = client;
    }

    public WGDGUrl_Base getUrl_Base() {
        return url_Base;
    }

    public void setUrl_Base(WGDGUrl_Base url_Base) {
        this.url_Base = url_Base;
    }

    public HTTPClientPane(AbstractClient client) {
        this.setClient(client);
        this.setUrl_Base(client.getUrl());
        this.launch_graph();
    }
    
    public void launch_graph(){
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));
        final HTTPClientPane this_ = this;
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().add("HTTP");
        comboBox.getItems().add("HTTPS");
        comboBox.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(comboBox.getValue().compareTo("HTTP") == 0){
                    this_.setClient(new HTTPClient(this_.getUrl_Base()));
                }else{
                    this_.setClient(new HTTPSClient(this_.getUrl_Base()));
                }
            }
        });
        this.add(comboBox, 0, 0);

        TextField hostname = new TextField(this.url_Base.getHostname());
        hostname.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                this_.url_Base.setHostname(hostname.getText());
            }
            
        });
        this.add(hostname, 1, 0);

        TextField port = new TextField(String.valueOf(this.url_Base.getPort()));
        port.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                this_.url_Base.setPort(Integer.valueOf(port.getText()).intValue());
            }
            
        });
        this.add(port, 2, 0);
    }
}
