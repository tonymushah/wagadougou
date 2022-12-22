package frontend.classes.TreeItems;

import frontend.classes.Wagadougou_App;
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
import mg.wagadougou.lib.classes.misc.RequestFolder;
import mg.wagadougou.lib.classes.requestTypes.base.HTTPRequestElement;
import mg.wagadougou.lib.classes.requestTypes.element.InsomniaRequestBase;
import mg.wagadougou.lib.exections.ElementNotFoundException;

public class RequestFolderTreeItem extends TreeItem<String> {
    private RequestFolder folder;
    private ContextMenu contextMenu;
    private RequestFolderTreeItem parentFolderTreeItem;

    public RequestFolderTreeItem getParentFolderTreeItem() {
        return parentFolderTreeItem;
    }

    public void setParentFolderTreeItem(RequestFolderTreeItem parentFolderTreeItem) {
        this.parentFolderTreeItem = parentFolderTreeItem;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public RequestFolder getFolder() {
        return folder;
    }

    public void setFolder(RequestFolder folder) {
        this.folder = folder;
    }

    public void refresh() {
        this.getChildren().clear();
        this.launch_graph();
    }

    public void deleteFolder(String id) {
        try {
            this.folder.remove_childrenFolderByID(id);
        } catch (ElementNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.refresh();
    }

    public void deleteElement(String id) {
        try {
            this.folder.remove_childrenElementsByID(id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.refresh();
    }

    public void deleteFolder(RequestFolder folder) {
        try {
            this.folder.remove_childrenFolder(folder);
        } catch (ElementNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.refresh();
    }

    public void deleteElement(HTTPRequestElement requestElement) {
        try {
            this.folder.remove_childrenElements(requestElement);
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.refresh();
    }

    public void addRequest() {
        this.folder.addRequest(new InsomniaRequestBase());
        this.refresh();
    }

    public void addFolder() {
        this.folder.addFolder(new RequestFolder(""));
        this.refresh();
    }

    public void build_contextMenu() {
        final ContextMenu to_use = new ContextMenu();
        final RequestFolderTreeItem this_ = this;
        MenuItem item1 = new MenuItem("Refresh");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                this_.refresh();
            }

        });
        MenuItem item2 = new MenuItem("Delete");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                try {
                    this_.parentFolderTreeItem.deleteFolder(this_.folder);
                } catch (Exception e) {
                    // TODO: handle exception
                    Wagadougou_App.getTo_useRequestCollectionTreeItem().deleteFolder(this_.folder);
                }
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
        to_use.getItems().addAll(item1, item3, item4, item2);
        this.setContextMenu(to_use);
    }

    public RequestFolderTreeItem(RequestFolder folder, RequestFolderTreeItem parentRoot) {
        super(" Folder", new TextField(folder.getId()));
        this.setFolder(folder);
        this.setParentFolderTreeItem(parentRoot);
        this.build_contextMenu();
        this.setGraphicContextMenu();
        final RequestFolderTreeItem this_ = this;
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
        final RequestFolderTreeItem this_ = this;
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
                    this_.folder.setId(graphic.getText());
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        });
        graphic.setContextMenu(this.contextMenu);
    }

    public void launch_graph() {
        final ObservableList<TreeItem<String>> children = this.getChildren();

        for (RequestFolder children_Folders : this.folder.getChildrenFolders()) {
            children.add(new RequestFolderTreeItem(children_Folders, this));
        }
        for (HTTPRequestElement requestElement : this.folder.getChildrenElements()) {
            children.add(new RequestElementTreeItem(requestElement, this));
        }

    }
}
