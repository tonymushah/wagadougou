package enums;

public enum HTTPVersion {
    @Deprecated
    HTTP0_9,
    HTTP1_1;
    public static HTTPVersion valueOF(String input) throws Exception{
        switch (input) {
            case "HTTP/1.1":
                return HTTP1_1;
            
            case "HTTP/0.9":
                return HTTP0_9;

            default:
                throw new Exception("Invalid Input");
        }
    }
}