package reweda.damian.quiz.Models.Details;

/**
 * Created by Damian on 15/05/16.
 */
public class Image {
    private String author;

    private Integer width;

    private Integer mediaId;

    private String source;

    private String url;

    private Integer height;

    public Image(String author, Integer width, Integer mediaId, String source, String url, Integer height) {
        this.author = author;
        this.width = width;
        this.mediaId = mediaId;
        this.source = source;
        this.url = url;
        this.height = height;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
