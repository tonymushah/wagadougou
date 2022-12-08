package mg.wagadougou.lib.classes.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class HTTPQueryParams extends ArrayList<HTTPQueryParam>{
    
    public HTTPQueryParams() {
        super();
    }

    @Override
    public String toString() {
        String to_return = "";
        Iterator<HTTPQueryParam> iterator = this.iterator();
        while(iterator.hasNext()){
            to_return = to_return + iterator.next().toString();
            if(iterator.hasNext() == true){
                to_return = to_return + "&";
            }
        }
        return to_return;
    }
    public static HTTPQueryParams valueOf(String to_use){
        String[] getted = to_use.split("&");
        HTTPQueryParams queryParams = new HTTPQueryParams();
        for (String to_parse : getted) {
            queryParams.add(HTTPQueryParam.valueOf(to_parse));
        }
        return queryParams;
    }
}
