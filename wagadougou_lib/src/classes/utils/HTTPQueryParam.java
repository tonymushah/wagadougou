package classes.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class HTTPQueryParam {
    private String name;
    private String value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() throws UnsupportedEncodingException {
        return URLEncoder.encode(this.value, Charset.availableCharsets().firstKey());
    }
    public void setValue(String value) {
        this.value = value;
    }
    public HTTPQueryParam() {
    }
    public HTTPQueryParam(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        try {
            return this.getName() + "=" + this.getValue();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
