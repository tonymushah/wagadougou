package frontend.classes.TreeItems;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import mg.wagadougou.lib.classes.misc.RequestCollection;
import mg.wagadougou.lib.classes.misc.RequestFolder;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;

public class RequestCollectionTreeItem extends TreeItem<String> {
    private RequestCollection to_use;
    private ContextMenu contextMenu;

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public RequestCollection getTo_use() {
        return to_use;
    }

    public void setTo_use(RequestCollection to_use) {
        this.to_use = to_use;
    }

    public void deleteElement(HTTPRequestElement element) {
        try {
            this.to_use.removeFolderByIDLevel0(element.getId());
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            this.refresh();
        }
    }

    public void deleteFolder(RequestFolder folder) {
        try {
            this.to_use.removeFolderByIDLevel0(folder.getId());
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            this.refresh();
        }
    }

    public void addRequest() {
        try {
            this.to_use.addRequest(new InsomniaRequestBase().setId(""));
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            this.refresh();
        }
    }

    public void addFolder() {
        try {
            this.to_use.addFolder(new RequestFolder(""));
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            this.refresh();
        }
    }

    public void build_contextMenu() {
        final ContextMenu to_use = new ContextMenu();
        final RequestCollectionTreeItem this_ = this;
        MenuItem item1 = new MenuItem("Refresh");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                this_.refresh();
            }

        });
        MenuItem item3 = new MenuItem("Add New Request");
        item3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.addRequest();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        });
        MenuItem item4 = new MenuItem("Add New Folder");
        item4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.addFolder();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        });
        to_use.getItems().addAll(item1, item3, item4);
        this.setContextMenu(to_use);
    }

    public void refresh() {
        this.getChildren().clear();
        this.update_graphic();
        this.launch_graph();
    }

    public RequestCollectionTreeItem(RequestCollection to_use) {
        super(" Collection", new TextField(to_use.getName()));
        ((TextField) this.getGraphic()).setEditable(false);
        this.setTo_use(to_use);
        this.build_contextMenu();
        this.setGraphicContextMenu();
        final RequestCollectionTreeItem this_ = this;
        this.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
                // TODO Auto-generated method stub
                if (arg2.booleanValue() == true) {
                    if (this_.getChildren().isEmpty()) {
                        launch_graph();
                    }
                }
            }

        });
    }

    public void setGraphicContextMenu() {
        final RequestCollectionTreeItem this_ = this;
        TextField graphic = (TextField) this.getGraphic();
        graphic.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if (event.getButton().compareTo(MouseButton.PRIMARY) == 0) {
                    this_.setExpanded(!this_.isExpanded());
                }
            }

        });
        graphic.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.to_use.setName(graphic.getText());
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        });
        graphic.setContextMenu(this.contextMenu);
    }

    public void update_graphic(){
        TextField graphic = (TextField) this.getGraphic();
        graphic.setEditable(false);
        graphic.setText(this.to_use.getName());
    }

    public void launch_graph() {
        final ObservableList<TreeItem<String>> children = this.getChildren();

        for (RequestFolder children_Folders : this.to_use.getFolders()) {
            children.add(new RequestFolderTreeItem(children_Folders, null));
        }
        for (HTTPRequestElement requestElement : this.to_use.getRequests()) {
            children.add(new RequestElementTreeItem(requestElement, null));
        }
    }
}
