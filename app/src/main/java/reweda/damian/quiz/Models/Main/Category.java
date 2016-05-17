package reweda.damian.quiz.Models.Main;

/**
 * Created by Damian on 15/05/16.
 */
public class Category {

    private long uid;

    private String name;

    private String title;


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category(long uid, String name, String title) {
        this.uid = uid;
        this.name = name;
        this.title = title;
    }
}
