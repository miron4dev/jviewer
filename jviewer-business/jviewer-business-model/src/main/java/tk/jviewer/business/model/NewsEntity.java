package tk.jviewer.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;

/**
 * A news entity
 */
@Entity
@Table(name = "news", schema = "jviewer_main", catalog = "jviewer")
public class NewsEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "author", nullable = false)
    private String author;

    public NewsEntity() {
    }

    public NewsEntity(String topic, String text, String author) {
        this.topic = topic;
        this.text = text;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
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
