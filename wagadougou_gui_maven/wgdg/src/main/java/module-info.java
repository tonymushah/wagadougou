module mg.wagadougou.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens mg.wagadougou.gui to javafx.fxml;
    exports mg.wagadougou.gui;
}
