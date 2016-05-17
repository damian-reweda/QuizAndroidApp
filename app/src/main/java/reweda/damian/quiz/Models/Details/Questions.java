package reweda.damian.quiz.Models.Details;

import java.util.List;

/**
 * Created by Damian on 15/05/16.
 */
public class Questions {

    //private List<Image> image;

    private List<Answers> answers;

    private String text;

    private String answer;

    private String type;

    private Integer order;

    public Questions(List<Answers> answers, String text, String answer, String type, Integer order) {
       // this.image = image;
        this.answers = answers;
        this.text = text;
        this.answer = answer;
        this.type = type;
        this.order = order;
    }
/*
    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }
*/
    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
