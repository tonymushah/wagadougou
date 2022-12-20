package classes.windows;

import java.util.ArrayList;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;

import classes.panels.HeaderPanel;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class HeadersWindow extends Panel {
    private ArrayList<HTTPHeader> headers;
    private ArrayList<HeaderPanel> headerPanels;

    public ArrayList<HTTPHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(ArrayList<HTTPHeader> headers) {
        this.headers = headers;
    }

    public ArrayList<HeaderPanel> getHeaderPanels() {
        return headerPanels;
    }

    public void setHeaderPanels(ArrayList<HeaderPanel> headerPanels) {
        this.headerPanels = headerPanels;
    }

    public void push() {
        for (HeaderPanel headerPanel : headerPanels) {
            headerPanel.push_to_use();
        }
    }

    public void pull() {
        for (HeaderPanel headerPanel : headerPanels) {
            headerPanel.pull_from_to_use();
        }
    }

    public void generate_headerPanels() {
        ArrayList<HeaderPanel> headerPanels = new ArrayList<HeaderPanel>();
        for (HTTPHeader headerPanel : this.headers) {
            headerPanels.add(new HeaderPanel(headerPanel));
        }
        this.setHeaderPanels(headerPanels);
    }

    public HeadersWindow(ArrayList<HTTPHeader> headers) {
        
        this.setHeaders(headers);
        this.generate_headerPanels();
        this.start_graph();
    }
    public void create_header(){
        this.headers.add(new HTTPHeader("", ""));
        this.generate_headerPanels();
        this.start_graph();
    }
    public HeadersWindow start_graph() {
        Panel contentPanel = new Panel(new GridLayout(3));
        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
        gridLayout.setHorizontalSpacing(2);
        for (HeaderPanel headerPanel : headerPanels) {
            contentPanel.addComponent(headerPanel.setLayoutData(
                    GridLayout.createLayoutData(
                            GridLayout.Alignment.BEGINNING,
                            GridLayout.Alignment.CENTER,
                            true,
                            false,
                            3,
                            1)));
        }
        HeadersWindow this_ = this;
        contentPanel.addComponent(new Button("Push", new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                this_.push();
            }
        }));
        contentPanel.addComponent(new Button("Pull", new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                this_.pull();
            }
        }));
        contentPanel.addComponent(new Button("Add", new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                this_.create_header();
            }
        }));
        return this;
    }
}
