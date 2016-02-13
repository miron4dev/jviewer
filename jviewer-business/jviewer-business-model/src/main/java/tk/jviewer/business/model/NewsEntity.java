package tk.jviewer.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * Default constructor. It is mandatory for the Hibernate.
     */
    public NewsEntity() {
    }

    public NewsEntity(Integer id, String topic, String text, String author) {
        this.id = id;
        this.topic = topic;
        this.text = text;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.author = author;
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
