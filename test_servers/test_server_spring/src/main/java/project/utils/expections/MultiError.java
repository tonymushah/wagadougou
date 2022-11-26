package project.utils.expections;

public class MultiError extends Exception{
    String[] messages;

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public MultiError() {
    }
    public MultiError(String ...message){
        this.setMessages(message);
    }
}
