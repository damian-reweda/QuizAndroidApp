package reweda.damian.quiz.Models.Details;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 15/05/16.
 */
public class QuizDetailResponse {

    private Celebrity celebrity;

    private List<Rates> rates = new ArrayList<>();

    private List<Questions> questions = new ArrayList<>();

    public QuizDetailResponse(Celebrity celebrity, List<Rates> rates, List<Questions> questions) {
        this.celebrity = celebrity;
        this.rates = rates;
        this.questions = questions;
    }

    public Celebrity getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(Celebrity celebrity) {
        this.celebrity = celebrity;
    }

    public List<Rates> getRates() {
        return rates;
    }

    public void setRates(List<Rates> rates) {
        this.rates = rates;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }
}
