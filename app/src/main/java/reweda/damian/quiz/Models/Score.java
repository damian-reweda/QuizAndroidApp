package reweda.damian.quiz.Models;

/**
 * Application keep last scores in database
 */
public class Score {

    private long id;

    private int score;

    private int questions;

    private int solved;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getIdDetail() {
        return id;
    }

    public void setIdDetail(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }
}
