package reweda.damian.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;

import reweda.damian.quiz.Database.QuizBaseHelper;
import reweda.damian.quiz.Database.QuizCursorWrapper;
import reweda.damian.quiz.Database.QuizDbSchema;
import reweda.damian.quiz.Models.Score;

/**
 * Singleton class to have single and context access to database
 */
public class QuizProvider {

    private static QuizProvider sQuizProvider;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static QuizProvider get(Context context) {
        if (sQuizProvider == null) {
            sQuizProvider = new QuizProvider(context);
        }
        return sQuizProvider;
    }

    private QuizProvider(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new QuizBaseHelper(mContext)
                .getWritableDatabase();

    }

    private static ContentValues getContentValues(Score score) {
        ContentValues values = new ContentValues();
        values.put(QuizDbSchema.QuizTable.Cols.ID, score.getIdDetail());
        values.put(QuizDbSchema.QuizTable.Cols.SCORE, score.getScore());
        values.put(QuizDbSchema.QuizTable.Cols.QUESTIONS, score.getQuestions());
        values.put(QuizDbSchema.QuizTable.Cols.SOLVED, score.getSolved());

        return values;
    }

    private QuizCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                QuizDbSchema.QuizTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new QuizCursorWrapper(cursor);
    }

    public void addScore(Score c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(QuizDbSchema.QuizTable.NAME, null, values);
    }

    public HashMap<Long, Score> getScores() {
        HashMap<Long, Score> scores = new HashMap<>();
        QuizCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                scores.put(cursor.getScore().getIdDetail(), cursor.getScore());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return scores;
    }
}
