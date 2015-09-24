package tk.jviewer.info.model;

import java.io.Serializable;

/**
 * Model of news
 */
public class News implements Serializable {

    private static final long serialVersionUID = 2491387418422869005L;

    private Integer id;
    private String topic;
    private String text;
    private String date;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "News{" +
                "topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
