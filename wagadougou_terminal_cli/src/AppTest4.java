import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

/**
 * The forth tutorial, introducing the GUIScreen interface
 * @author Martin
 */
public class AppTest4 {
    public static void main(String[] args) {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = null;
        try {
            /*
            The DefaultTerminalFactory class doesn't provide any helper method for creating a Text GUI, you'll need to
             get a Screen like we did in the previous tutorial and start it so it puts the terminal in private mode.
             */
            screen = terminalFactory.createScreen();
            screen.startScreen();
            /*
            There are a couple of different constructors to MultiWindowTextGUI, we are going to go with the defaults for
            most of these values. The one thing to consider is threading; with the default options, lanterna will use
            the calling thread for all UI operations which mean that you are basically letting the calling thread block
            until the GUI is shut down. There is a separate TextGUIThread implementaiton you can use if you'd like
            Lanterna to create a dedicated UI thread and not lock the caller. Just like with AWT and Swing, you should
            be scheduling any kind of UI operation to always run on the UI thread but lanterna tries to be best-effort
            if you attempt to mutate the GUI from another thread. Another default setting that will be applied is that
            the background of the GUI will be solid blue.
             */
            WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

            /*
            Creating a new window is relatively uncomplicated, you can optionally supply a title for the window
             */
            Window window = new BasicWindow("My Window");

            Panel contentPanel = new Panel(new GridLayout(2));
            GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
            gridLayout.setHorizontalSpacing(3);

            contentPanel.addComponent(
                new Label("Some text")
                    .setLayoutData(
                        GridLayout.createLayoutData(
                            GridLayout.Alignment.BEGINNING, 
                            GridLayout.Alignment.CENTER,
                            true,
                            false,
                            1,
                            1
                        )
                    )
            );
            TextBox to_use = new TextBox();

            contentPanel.addComponent(to_use);

            contentPanel.addComponent(new Button("Message Box", new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    MessageDialog.showMessageDialog(textGUI, "Your text", to_use.getText(), MessageDialogButton.OK);
                }}));

            window.setComponent(contentPanel);

            textGUI.addWindowAndWait(window);

            /*
            When our call has returned, the window is closed and no longer visible. The screen still contains the last
            state the TextGUI left it in, so we can easily add and display another window without any flickering. In
            this case, we want to shut down the whole thing and return to the ordinary prompt. We just need to stop the
            underlying Screen for this, the TextGUI system does not require any additional disassembly.
             */
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