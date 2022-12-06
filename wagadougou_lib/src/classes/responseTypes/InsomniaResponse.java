package classes.responseTypes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class InsomniaResponse extends HTTPResponse {
    private long startTime;
    private long endTime;
    private OutputStream timeline;

    public OutputStream getTimelineOutputStream() {
        return timeline;
    }

    public InsomniaResponse setTimeline(OutputStream timeline) {
        this.timeline = timeline;
        return this;
    }
    public PrintStream getTimeLine_PrintStream(){
        return new PrintStream(this.timeline);
    }

    public InsomniaResponse() {
        this.setTimeline(new ByteArrayOutputStream());
    }
    
    public InputStream getTimeline(){
        return new ByteArrayInputStream(((ByteArrayOutputStream) this.timeline).toByteArray());
    }
    public long getStartTime() {
        return startTime;
    }
    public InsomniaResponse setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }
    public long getEndTime() {
        return endTime;
    }
    public InsomniaResponse setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }
    public long getDuration(){
        return this.getEndTime() - this.getStartTime();
    }
}
