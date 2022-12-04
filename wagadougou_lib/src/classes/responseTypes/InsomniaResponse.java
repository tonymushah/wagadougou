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

    public void setTimeline(OutputStream timeline) {
        this.timeline = timeline;
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
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public long getEndTime() {
        return endTime;
    }
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
    public long getDuration(){
        return this.getEndTime() - this.getStartTime();
    }
}
