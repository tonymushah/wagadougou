package classes.responseTypes;

public class HTTPResponseWTime extends HTTPResponse {
    private long startTime;
    private long endTime;
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
