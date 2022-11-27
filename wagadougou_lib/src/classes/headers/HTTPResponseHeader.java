package classes.headers;

import enums.HTTPVersion;

public class HTTPResponseHeader {
    private HTTPVersion version;
    private int status_code;
    private String result;
    public HTTPVersion getVersion() {
        return version;
    }
    public void setVersion(HTTPVersion version) {
        this.version = version;
    }
    public int getStatus_code() {
        return status_code;
    }
    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public HTTPResponseHeader(){

    }
    public HTTPResponseHeader(HTTPVersion version, int status_code, String message){
        this.setResult(message);
        this.setStatus_code(status_code);
        this.setVersion(version);
    }
    public HTTPResponseHeader(String input) throws Exception{
        String[] splited = input.split("\s", 3);
        this.setVersion(HTTPVersion.valueOF(splited[0]));
        this.setStatus_code(Integer.parseInt(splited[1]));
        this.setResult(splited[2]);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getVersion() + " " + this.getStatus_code() + " " + this.getResult();
    }
    
}
