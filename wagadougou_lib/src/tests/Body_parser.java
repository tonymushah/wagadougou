package tests;

import java.util.HexFormat;

public class Body_parser {
    public static void main(String[] args) {
        byte[] bytes = "{\"result\":\"ok\",\"type\":\"message\",\"attributes\":\"dasdkhasidsabdka\"}\"".getBytes();
        System.out.println(bytes.length);
        System.out.println(HexFormat.fromHexDigits("40"));
    }
}
