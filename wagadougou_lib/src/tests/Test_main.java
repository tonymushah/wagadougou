package tests;

import classes.utils.HTTPQueryParam;
import classes.utils.HTTPQueryParams;

public class Test_main {
    public static void main(String[] args) {
        HTTPQueryParams queryParams = new HTTPQueryParams();
        queryParams.add(new HTTPQueryParam("name", "Tony()sdsdad"));
        queryParams.add(new HTTPQueryParam("lol", "Tony()sdsdad"));
        System.out.println(queryParams);
    }
}
