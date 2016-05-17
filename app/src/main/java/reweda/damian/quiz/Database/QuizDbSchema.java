package reweda.damian.quiz.Database;

public class QuizDbSchema {
    public static final class QuizTable {
        public static final String NAME = "quiz";

        public static final class Cols {
            public static final String ID = "id";
            public static final String SCORE = "score";
            public static final String QUESTIONS = "questions";
            public static final String SOLVED = "solved";
        }
    }
}
