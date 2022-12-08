package mg.wagadougou.lib.classes.responseTypes;

public class HTTPResponseWTime extends HTTPResponse {
    private long startTime;
    private long endTime;
    public long getStartTime() {
        return startTime;
    }
    public HTTPResponseWTime setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }
    public long getEndTime() {
        return endTime;
    }
    public HTTPResponseWTime setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }
    public long getDuration(){
        return this.getEndTime() - this.getStartTime();
    }
}
