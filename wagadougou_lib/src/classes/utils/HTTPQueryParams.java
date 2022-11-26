package classes.utils;

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
}
