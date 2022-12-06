package classes.utils;

public class WGDGUrl_Base {
    private String hostname;
    private int port;
    public String getHostname() {
        return hostname;
    }
    public WGDGUrl_Base setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }
    public int getPort() {
        return port;
    }
    public WGDGUrl_Base setPort(int port) {
        this.port = port;
        return this;
    }
    public WGDGUrl_Base(String hostname, int port) {
        this.setHostname(hostname);
        this.setPort(port);
    }
    
}
