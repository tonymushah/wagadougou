package mg.wagadougou.lib.classes.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class HTTPQueryParam implements Serializable{
    private String name;
    private String value;
    public String getName() {
        return name;
    }
    public HTTPQueryParam setName(String name) {
        this.name = name;
        return this;
    }
    public String getValue() throws UnsupportedEncodingException {
        return URLEncoder.encode(this.value, Charset.availableCharsets().firstKey());
    }
    public HTTPQueryParam setValue(String value) {
        this.value = value;
        return this;
    }
    public HTTPQueryParam() {
    }
    public HTTPQueryParam(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }
    public static HTTPQueryParam valueOf(String to_use){
        String[] getted = to_use.split("=");
        if(getted.length != 2){
            throw new ArrayIndexOutOfBoundsException("Parsing the queryParam should be \"name=value\" like");
        }
        return new HTTPQueryParam(getted[0], getted[1]);
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
