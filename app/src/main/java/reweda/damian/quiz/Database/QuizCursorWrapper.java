package reweda.damian.quiz.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import reweda.damian.quiz.Models.Score;

public class QuizCursorWrapper extends CursorWrapper{

    public QuizCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Score getScore() {
        long id = getLong(getColumnIndex(QuizDbSchema.QuizTable.Cols.ID));
        int score = getInt(getColumnIndex(QuizDbSchema.QuizTable.Cols.SCORE));
        int questions = getInt(getColumnIndex(QuizDbSchema.QuizTable.Cols.QUESTIONS));
        int solved = getInt(getColumnIndex(QuizDbSchema.QuizTable.Cols.SOLVED));

        Score mScore = new Score();
        mScore.setIdDetail(id);
        mScore.setScore(score);
        mScore.setQuestions(questions);
        mScore.setSolved(solved);

        return mScore;
    }
}
