import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import classes.panels.HeaderPanel;
import classes.windows.HeadersWindow;
import mg.wagadougou.lib.classes.headers.HTTPHeader;

public class Main {
    public static void main(String[] args) {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        try {
            ArrayList<HTTPHeader> headers = new ArrayList<HTTPHeader>();
            headers.add((new HTTPHeader("Host", "localhost:8082")));
            headers.add((new HTTPHeader("user-agent", "dsaidhasuidhadskjd")));
            

            screen = terminalFactory.createScreen();
            screen.startScreen();
            
            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            textGUI.addWindowAndWait(new HeadersWindow(headers));

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }finally{
            if(screen != null){
                try{
                    screen.stopScreen();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
