package reweda.damian.quiz.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import reweda.damian.quiz.Fragment.QuizDetailFragment;
import reweda.damian.quiz.Fragment.QuizEndFragment;
import reweda.damian.quiz.Fragment.QuizListFragment;
import reweda.damian.quiz.R;
import reweda.damian.quiz.SingleFragmentActivity;

public class QuizDetailActivity extends SingleFragmentActivity implements QuizDetailFragment.Callbacks {

    public static final String EXTRA_QUIZ_ID =
            "reweda.damian.quiz.id";

    public static Intent newIntent(Context packageContext, long quizId) {
        Intent intent = new Intent(packageContext, QuizDetailActivity.class);
        intent.putExtra(EXTRA_QUIZ_ID, quizId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        long quizId = (long) getIntent()
                .getSerializableExtra(QuizDetailActivity.EXTRA_QUIZ_ID);

        return QuizDetailFragment.newInstance(quizId);

    }

    @Override
    public void onQuizFinished(int countScore, long quizId, int size) {
        if (isSmartphone()) {
            openSmarphoneView(countScore, quizId, size);
        } else {
            openTabletView(countScore, quizId, size);
        }
    }

    private void openTabletView(int countScore, long quizId, int size) {
        Fragment newDetail = QuizEndFragment.newInstance(countScore, quizId, size, size);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail)
                .commit();
    }

    private void openSmarphoneView(int countScore, long quizId, int size) {
        Intent intent = QuizEndActivity.newIntent(this, countScore, quizId, size, size);
        startActivity(intent);
    }

    private boolean isSmartphone() {
        return findViewById(R.id.detail_fragment_container) == null;
    }

    @Override
    public void onQuizUpdate() {
        if (!isSmartphone()) {
            updateQuizListInMainView();
        }
    }

    private void updateQuizListInMainView() {
        QuizListFragment listFragment = (QuizListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }
}
