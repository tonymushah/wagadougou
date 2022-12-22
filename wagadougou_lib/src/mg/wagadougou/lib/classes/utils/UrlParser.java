package mg.wagadougou.lib.classes.utils;

import java.io.Serializable;

import mg.wagadougou.lib.classes.clients.AbstractClient;
import mg.wagadougou.lib.classes.clients.HTTPClient;
import mg.wagadougou.lib.classes.clients.HTTPSClient;

public class UrlParser implements Serializable{
    private AbstractClient client;
    private WGDGUrl_Base url_Base;
    private String path;
    private HTTPQueryParams queryParams;
    public AbstractClient getClient() {
        return client;
    }
    public UrlParser setClient(AbstractClient client) {
        this.client = client;
        return this;
    }
    public WGDGUrl_Base getUrl_Base() {
        return url_Base;
    }
    public UrlParser setUrl_Base(WGDGUrl_Base url_Base) {
        this.url_Base = url_Base;
        return this;
    }
    public String getPath() {
        return path;
    }
    public UrlParser setPath(String path) {
        this.path = path;
        return this;
    }
    public HTTPQueryParams getQueryParams() {
        return queryParams;
    }
    public UrlParser setQueryParams(HTTPQueryParams queryParams) {
        this.queryParams = queryParams;
        return this;
    }
    public UrlParser(String to_parse){
        // getting the client
        String[] client_part_parse = to_parse.split("://");
        String[] path_parse = client_part_parse[1].split("/", 2);
        this.setUrl_Base(WGDGUrl_Base.valueOf(path_parse[0]));
        String[] path_parse2 = path_parse[1].split("\\?");
        if(path_parse[1].contains("\\?")){
            this.setQueryParams(HTTPQueryParams.valueOf(path_parse2[1]));
        }
        this.setPath(path_parse2[0]);
        if(client_part_parse[0].compareTo("http") == 0){
            this.setClient(new HTTPClient(this.getUrl_Base()));
        }else if(client_part_parse[0].compareTo("https") == 0){
            this.setClient(new HTTPSClient(this.getUrl_Base()));
        }
    }
}
