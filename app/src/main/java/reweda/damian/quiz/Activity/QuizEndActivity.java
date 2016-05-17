package reweda.damian.quiz.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import reweda.damian.quiz.Fragment.QuizDetailFragment;
import reweda.damian.quiz.Fragment.QuizEndFragment;
import reweda.damian.quiz.Fragment.QuizListFragment;
import reweda.damian.quiz.R;
import reweda.damian.quiz.SingleFragmentActivity;

public class QuizEndActivity extends SingleFragmentActivity implements QuizEndFragment.Callbacks {

    public static final String EXTRA_SCORE =
            "reweda.damian.quiz.score";

    public static final String EXTRA_ID =
            "reweda.damian.quiz.id";

    public static final String EXTRA_QUESTIONS =
            "reweda.damian.quiz.questions";

    public static final String EXTRA_SOLVED =
            "reweda.damian.quiz.solved";

    public static Intent newIntent(Context packageContext, int score, long id, int questions, int solved) {
        Intent intent = new Intent(packageContext, QuizEndActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_QUESTIONS, questions);
        intent.putExtra(EXTRA_SOLVED, solved);

        return intent;
    }

    @Override
    protected Fragment createFragment() {

        int score = (int) getIntent()
                .getSerializableExtra(QuizEndActivity.EXTRA_SCORE);
        long quizId = (long) getIntent()
                .getSerializableExtra(QuizEndActivity.EXTRA_ID);
        int questions = (int) getIntent()
                .getSerializableExtra(QuizEndActivity.EXTRA_QUESTIONS);
        int solved = (int) getIntent()
                .getSerializableExtra(QuizEndActivity.EXTRA_SOLVED);

        return QuizEndFragment.newInstance(score, quizId, questions, solved);

    }

    @Override
    public void onQuizReplay(long quizId) {
        if (isSmartphone()) {
            openSmartphoneView(quizId);
        } else {
            openTabletView(quizId);
        }
    }

    private void openTabletView(long quizId) {
        Fragment newDetail = QuizDetailFragment.newInstance(quizId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail)
                .commit();
    }

    private void openSmartphoneView(long quizId) {
        Intent intent = QuizDetailActivity.newIntent(this, quizId);
        startActivity(intent);
    }

    private boolean isSmartphone() {
        return findViewById(R.id.detail_fragment_container) == null;
    }

    @Override
    public void onQuizUpdate() {
        if (!isSmartphone()) {
            QuizListFragment fragmentQuizList = (QuizListFragment)
                    getSupportFragmentManager()
                            .findFragmentById(R.id.fragment_container);

            fragmentQuizList.updateUI();
        }
    }
}
