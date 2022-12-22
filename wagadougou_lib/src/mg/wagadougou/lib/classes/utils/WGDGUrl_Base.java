package mg.wagadougou.lib.classes.utils;

import java.io.Serializable;

public class WGDGUrl_Base implements Serializable{
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
    public static WGDGUrl_Base valueOf(String to_use){
        String[] getted = to_use.split(":");
        return new WGDGUrl_Base(getted[0], Integer.valueOf(getted[1]).intValue());
    }
}
