package reweda.damian.quiz.Models.Details;

/**
 * Created by Damian on 15/05/16.
 */
public class Answers {

    private Integer order;

    private String text;

    private Integer isCorrect;

    public Answers(Integer order, String text, Integer isCorrect) {
        this.order = order;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect != null;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }
}
