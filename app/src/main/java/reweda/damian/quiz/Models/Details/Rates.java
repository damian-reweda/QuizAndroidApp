package reweda.damian.quiz.Models.Details;

/**
 * Created by Damian on 15/05/16.
 */
public class Rates {

    private Integer from;

    private Integer to;

    private String content;

    public Rates(String content) {
        this.content = content;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
