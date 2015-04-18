package tk.jviewer.info.model;

import java.io.Serializable;

/**
 * Model of news
 */
public class NewsModel implements Serializable {

    private String topic;
    private String text;
    private String date;
    private String author;

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
        return "NewsModel{" +
                "topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
