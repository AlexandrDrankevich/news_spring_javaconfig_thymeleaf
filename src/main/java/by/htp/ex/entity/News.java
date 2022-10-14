package by.htp.ex.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idNews;
    @Column(name = "title")
    private String title;
    @Column(name = "brief")
    private String briefNews;
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    private java.sql.Date newsDate;

    public News() {
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefNews() {
        return briefNews;
    }

    public void setBriefNews(String briefNews) {
        this.briefNews = briefNews;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.sql.Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(java.sql.Date newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(briefNews, content, idNews, newsDate, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        News other = (News) obj;
        return Objects.equals(briefNews, other.briefNews) && Objects.equals(content, other.content)
                && idNews == other.idNews && Objects.equals(newsDate, other.newsDate)
                && Objects.equals(title, other.title);
    }

    @Override
    public String toString() {
        return "News [idNews=" + idNews + ", title=" + title + ", briefNews=" + briefNews + ", content=" + content
                + ", newsDate=" + newsDate + "]";
    }
}
