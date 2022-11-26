package project.utils.classes;

public class ResultType<T> {
    private String result;
    private String type;
    private T attributes;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public T getAttributes() {
        return attributes;
    }
    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }
    public ResultType() {
    }
    public ResultType(String result, String type, T attributes) {
        this.setAttributes(attributes);
        this.setResult(result);
        this.setType(type);
    }
}
