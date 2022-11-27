package enums;

public enum HTTPVersion {
    @Deprecated
    HTTP0_9(){
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "HTTP/0.9";
        }
    },
    HTTP1_1(){
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "HTTP/1.1";
        }
    },
    HTTP2(){
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "HTTP/2";
        }
    };
    public static HTTPVersion valueOF(String input) throws Exception{
        switch (input) {
            case "HTTP/1.1":
                return HTTP1_1;
            
            case "HTTP/0.9":
                return HTTP0_9;

            case "HTTP/2": 
                return HTTP2;
            default:
                throw new Exception("Invalid Input");
        }
    }
}
