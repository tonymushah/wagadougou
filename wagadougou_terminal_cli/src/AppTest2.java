import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;

/**
 * App
 */
public class AppTest2 {
    public static void writeString(Terminal terminal, String toWrite) throws IOException{
        for (char character : toWrite.toCharArray()) {
            terminal.putCharacter(character);
        }
    }
    public static void main(String[] args) {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = null;
        try {
            terminal = terminalFactory.createTerminal();
            terminal.enterPrivateMode();
            terminal.setCursorVisible(false);
            TextGraphics textGraphics = terminal.newTextGraphics();
            KeyStroke keyStroke = terminal.readInput();
            terminal.setCursorPosition(0, 0);
            while(keyStroke.getKeyType() != KeyType.Escape) {
                TerminalPosition position= terminal.getCursorPosition();
                textGraphics.putString(position.getColumn(), position.getRow(), keyStroke.getCharacter().charValue() + "");
                keyStroke = terminal.readInput();
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }finally{
            if(terminal != null){
                try {
                    terminal.close();
                } catch (Exception e) {
                    //TODO: handle exception
                }
            }
        }
    }
}