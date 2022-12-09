package classes.panels;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeaderPanel extends Panel {
    private HTTPHeader to_use;
    private TextBox name;
    private TextBox value;

    public TextBox getName() {
        return name;
    }

    public HeaderPanel setName(TextBox name) {
        this.name = name;
        return this;
    }

    public HTTPHeader getTo_use() {
        return to_use;
    }

    public HeaderPanel setTo_use(HTTPHeader to_use) {
        this.to_use = to_use;
        return this;
    }

    public TextBox getValue() {
        return value;
    }

    public HeaderPanel setValue(TextBox value) {
        this.value = value;
        return this;
    }
    public HeaderPanel(HTTPHeader to_use){
        super(new GridLayout(2));
        this.setTo_use(to_use);
        this.setName(new TextBox(to_use.getContext()));
        this.setValue(new TextBox(to_use.getValue()));
        this.start_graph();
    }
    public HeaderPanel push_to_use(){
        this.to_use.setValue(this.getValue().getText());
        this.to_use.setContext(this.getName().getText());
        return this;
    }
    public HeaderPanel pull_from_to_use(){
        this.value.setText(this.to_use.getValue());
        this.name.setText(this.to_use.getContext());
        return this;
    }
    public HeaderPanel start_graph(){
        GridLayout gridLayout = (GridLayout) this.getLayoutManager();
        gridLayout.setHorizontalSpacing(3);
        this.addComponent(this.name);
        this.addComponent(this.value);
        return this;
    }
}
