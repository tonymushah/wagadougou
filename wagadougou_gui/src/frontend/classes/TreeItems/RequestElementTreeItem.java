package frontend.classes.TreeItems;

import frontend.classes.Wagadougou_App;
import frontend.classes.windows.RequestWindow;
import frontend.classes.windows.ResponseWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;

public class RequestElementTreeItem extends TreeItem<String> {
    private HTTPRequestElement to_use;
    private ContextMenu contextMenu;
    private RequestWindow window;
    private RequestFolderTreeItem parentFolder;

    public RequestFolderTreeItem getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(RequestFolderTreeItem parentFolder) {
        this.parentFolder = parentFolder;
    }

    public RequestWindow getWindow() {
        return window;
    }

    public void setWindow(RequestWindow window) {
        this.window = window;
    }

    public void update_graphic(){
        TextField graphic = (TextField) this.getGraphic();
        graphic.setText(this.to_use.getId());
    }

    public void build_editPopUp(){
        this.setWindow(new RequestWindow((InsomniaRequestBase) this.to_use));
    }

    public void build_contextMenu() {
        final ContextMenu to_use = new ContextMenu();
        final RequestElementTreeItem this_ = this;
        MenuItem item1 = new MenuItem("Edit Request");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                if(this_.getWindow() == null){
                    this_.build_editPopUp();
                }
                this_.getWindow().showAndWait();
            }

        });
        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.parentFolder.deleteElement(this_.to_use);
                } catch (Exception e) {
                    //TODO: handle exception
                    Wagadougou_App.getTo_useRequestCollectionTreeItem().deleteElement(this_.to_use);
                }
                
            }

        });
        MenuItem item3 = new MenuItem("Send");
        item3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                new ResponseWindow((InsomniaRequestBase)this_.to_use);
            }

        });
        to_use.getItems().addAll(item1, item3, item2);
        this.setContextMenu(to_use);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public HTTPRequestElement getTo_use() {
        return to_use;
    }

    public void setTo_use(HTTPRequestElement to_use) {
        this.to_use = to_use;
    }

    public RequestElementTreeItem(HTTPRequestElement to_use, RequestFolderTreeItem parentRoot) {
        super(" Request", new TextField(to_use.getId()));
        this.setTo_use(to_use);
        this.setParentFolder(parentRoot);
        this.build_contextMenu();
        this.launch_graph();
    }

    public void launch_graph() {
        TextField graphic = (TextField) this.getGraphic();
        final RequestElementTreeItem this_ = this;
        graphic.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.to_use.setId(graphic.getText());
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        });
        graphic.setContextMenu(this.contextMenu);
    }
}
