package layouts.android.umlv.fr.shareview;

import java.util.logging.Logger;

/**
 * Created by Heretic on 22/05/2016.
 */
public class HttpBundle {
    private final Logger logger = Logger.getLogger(HttpBundle.class.getName());

    private final String address = "http://127.0.0.1:7777/";
    private final int TIMEOUT = 1_000_000;

    private String author;
    private String message;
    private String queueName;
    private String id;

    private String method;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQueueName(){
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTIMEOUT() {
        return TIMEOUT;
    }

    public String getFullRequest() {
        StringBuilder fullRequest = new StringBuilder(address);
        if (getMethod() == "GET" && queueName == null) {
            return fullRequest.toString();
        }
        if (author == null || message == null) {
            logger.warning("");
            throw new IllegalStateException();
        }
        fullRequest.append(queueName);
        if (id != null && method == "GET") {
            fullRequest.append("/");
            fullRequest.append(id);
            fullRequest.append("?timeout");
            fullRequest.append(getTIMEOUT());
            return fullRequest.toString();
        } else if (method == "POST") {
            fullRequest.append("?author=");
            fullRequest.append(author);
            fullRequest.append("&message=");
            fullRequest.append(message);
        }
        return fullRequest.toString();
    }
}
