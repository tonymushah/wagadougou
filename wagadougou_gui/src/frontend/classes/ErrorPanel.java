package frontend.classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ErrorPanel extends GridPane {
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ErrorPanel(Exception exception) {
        this.setException(exception);
        this.launch_graph();
    }
    
    public void launch_graph(){
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(5, 25, 5, 25));
        this.add(new Text(this.exception.getMessage()), 0, 0);
        int count = 1;
        for (StackTraceElement stackTraceElement : this.exception.getStackTrace()) {
            this.add(new Text(stackTraceElement.toString()), 0, count);
            count++;
        }
    }
}
