package reweda.damian.quiz.Models.Main;

/**
 * Created by Damian on 15/05/16.
 */
public class QuizCategory {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuizCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
