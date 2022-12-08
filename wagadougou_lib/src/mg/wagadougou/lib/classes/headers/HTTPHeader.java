package mg.wagadougou.lib.classes.headers;

public class HTTPHeader {
    private String context;
    private String value;
    public String getContext() {
        return context;
    }
    public HTTPHeader setContext(String context) {
        this.context = context;
        return this;
    }
    public String getValue() {
        return value;
    }
    public HTTPHeader setValue(String value) {
        this.value = value;
        return this;
    }
    
    public HTTPHeader(String context, String value) {
        this.setContext(context);
        this.setValue(value);
    }
    public HTTPHeader() {
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getContext() + ": " + this.getValue();
    }
    public static HTTPHeader valueOf(String input){
        String[] to_uses = input.split(":", 2);
        return new HTTPHeader(to_uses[0], to_uses[1].replaceFirst("\s", ""));
    }
}
