package reweda.damian.quiz.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import reweda.damian.quiz.Database.QuizDbSchema.QuizTable;

public class QuizBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE_STATEMENT = "create table " + QuizTable.NAME + "(" +
            " _id integer primary key autoincrement, " +
            QuizTable.Cols.ID + ", " +
            QuizTable.Cols.SCORE + ", " +
            QuizTable.Cols.QUESTIONS + ", " +
            QuizTable.Cols.SOLVED +
            ")";

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "quizBase.db";

    public QuizBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATEMENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

}
