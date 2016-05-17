package reweda.damian.quiz.Models.Main;

import java.util.List;

/**
 * Created by Damian on 15/05/16.
 */
public class QuizItem {

    private String shareTitle;

    private int questions;

    private boolean sponsored;

    private long id;

    private List<Category> categories;

    private String type;

    private String title;

    private String content;

    private MainPhoto mainPhoto;

    private QuizCategory quizCategory;

    private int score;

    private int score_temp;

    private int question_temp;

    private int solved_temp;

    public int getSolved_temp() {
        return solved_temp;
    }

    public void setSolved_temp(int solved_temp) {
        this.solved_temp = solved_temp;
    }

    public int getScore_temp() {
        return score_temp;
    }

    public void setScore_temp(int score_temp) {
        this.score_temp = score_temp;
    }

    public int getQuestion_temp() {
        return question_temp;
    }

    public void setQuestion_temp(int question_temp) {
        this.question_temp = question_temp;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public boolean isSponsored() {
        return sponsored;
    }

    public void setSponsored(boolean sponsored) {
        this.sponsored = sponsored;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MainPhoto getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(MainPhoto mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public QuizCategory getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(QuizCategory quizCategory) {
        this.quizCategory = quizCategory;
    }

    public QuizItem(String shareTitle, int questions, boolean sponsored, long id, List<Category> categories, String type, String title, String content, MainPhoto mainPhoto, QuizCategory quizCategory) {
        this.shareTitle = shareTitle;
        this.questions = questions;
        this.sponsored = sponsored;
        this.id = id;
        this.categories = categories;
        this.type = type;
        this.title = title;
        this.content = content;
        this.mainPhoto = mainPhoto;
        this.quizCategory = quizCategory;
    }
}
