import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * App
 */
public class AppTest1 {
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
            AppTest1.writeString(terminal, "hello world\n");
            terminal.flush();
            Thread.sleep(2000);
            TerminalPosition startPosition = terminal.getCursorPosition();
            terminal.setCursorPosition(startPosition.withRelativeColumn(3).withRelativeRow(2));
            terminal.flush();
            Thread.sleep(2000);
            terminal.enableSGR(SGR.BOLD);
            terminal.setBackgroundColor(TextColor.ANSI.BLUE);
            terminal.setForegroundColor(TextColor.ANSI.YELLOW);
            AppTest1.writeString(terminal, "Yellow and blue\n");
            terminal.flush();
            Thread.sleep(2000);
            terminal.resetColorAndSGR();
            terminal.setCursorPosition(terminal.getCursorPosition().withColumn(0).withRelativeRow(1));
            terminal.putCharacter('D');
            terminal.putCharacter('o');
            terminal.putCharacter('n');
            terminal.putCharacter('e');
            terminal.putCharacter('\n');
            terminal.flush();
            terminal.bell();

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