package by_.dsavitski.news.json;

/**
 * Simple wrapper for JSON response.
 */
public class Message {
    private Object body;

    public Message(Object body) {
        this.body = body;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
