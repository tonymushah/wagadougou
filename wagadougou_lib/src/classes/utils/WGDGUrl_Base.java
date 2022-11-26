package classes.utils;

public class WGDGUrl_Base {
    private String hostname;
    private int port;
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public WGDGUrl_Base(String hostname, int port) {
        this.setHostname(hostname);
        this.setPort(port);
    }
    
}
