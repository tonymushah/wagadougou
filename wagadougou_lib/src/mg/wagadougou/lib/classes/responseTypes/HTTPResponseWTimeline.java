package mg.wagadougou.lib.classes.responseTypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class HTTPResponseWTimeline extends HTTPResponse {
    private OutputStream timeline;

    public OutputStream getTimelineOutputStream() {
        return timeline;
    }

    public HTTPResponseWTimeline setTimeline(OutputStream timeline) {
        this.timeline = timeline;
        return this;
    }
    public PrintStream getTimeLine_PrintStream(){
        return new PrintStream(this.timeline);
    }

    public HTTPResponseWTimeline() {
        this.setTimeline(new ByteArrayOutputStream());
    }
    
    public InputStream getTimeline(){
        return new ByteArrayInputStream(((ByteArrayOutputStream) this.timeline).toByteArray());
    }
}
