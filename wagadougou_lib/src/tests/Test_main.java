package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import classes.utils.HTTPQueryParam;
import classes.utils.HTTPQueryParams;

public class Test_main {
    public static void main(String[] args) throws IOException {
        URL uri = new URL("https://api.mangadex.org/manga");
        URLConnection connection = uri.openConnection();
        for (List<String> string : connection.getHeaderFields().values()) {
            for (String string2 : string) {
                System.out.println(string2);
            }
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        for (String string : in.lines().toList()) {
            System.out.println(string);
        }
    }
}
