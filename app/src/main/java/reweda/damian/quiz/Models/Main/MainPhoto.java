package reweda.damian.quiz.Models.Main;

/**
 * Created by Damian on 15/05/16.
 */
public class MainPhoto {

    private String author;

    private int width;

    private String source;

    private String title;

    private String url;

    private int height;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MainPhoto(String author, int width, String source, String title, String url, int height) {
        this.author = author;
        this.width = width;
        this.source = source;
        this.title = title;
        this.url = url;
        this.height = height;
    }
}
