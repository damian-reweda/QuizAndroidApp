package reweda.damian.quiz.Models.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian on 15/05/16.
 */
public class QuizResponse  {

    private long count;

    private List<QuizItem> items = new ArrayList<>() ;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<QuizItem> getItems() {
        return items;
    }

    public void setItems(List<QuizItem> items) {
        this.items = items;
    }

    public QuizResponse(long count, List<QuizItem> mItems) {
        this.count = count;
        this.items = mItems;
    }
}
