package reweda.damian.quiz.Models.Details;

/**
 * Created by Damian on 15/05/16.
 */
public class Celebrity {

    private String result;

    private String imageAuthor;

    private String imageHeight;

    private String imageUrl;

    private String name;

    private String show;

    private String imageTitle;

    private String imageWidth;

    private String content;

    private String imageSource;

    public Celebrity(String result, String imageAuthor, String imageHeight, String imageUrl, String name, String show, String imageTitle, String imageWidth, String content, String imageSource) {
        this.result = result;
        this.imageAuthor = imageAuthor;
        this.imageHeight = imageHeight;
        this.imageUrl = imageUrl;
        this.name = name;
        this.show = show;
        this.imageTitle = imageTitle;
        this.imageWidth = imageWidth;
        this.content = content;
        this.imageSource = imageSource;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImageAuthor() {
        return imageAuthor;
    }

    public void setImageAuthor(String imageAuthor) {
        this.imageAuthor = imageAuthor;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
}
