package reweda.damian.quiz.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import reweda.damian.quiz.Fragment.QuizDetailFragment;
import reweda.damian.quiz.Fragment.QuizEndFragment;
import reweda.damian.quiz.Fragment.QuizListFragment;
import reweda.damian.quiz.R;
import reweda.damian.quiz.SingleFragmentActivity;

public class QuizListActivity extends SingleFragmentActivity
        implements QuizListFragment.Callbacks, QuizDetailFragment.Callbacks, QuizEndFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new QuizListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onQuizSelected(long id) {
        if (isSmartphone()) {
            openDetailsSmartphoneView(id);
        } else {
            openDetailTabletView(id);
        }
        updateQuizListData();
    }

    private void updateQuizListData() {
        QuizListFragment listFragment = (QuizListFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    private void openDetailTabletView(long id) {
        Fragment newDetail = QuizDetailFragment.newInstance(id);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail)
                .commit();
    }

    private void openDetailsSmartphoneView(long id) {
        Intent intent = QuizDetailActivity.newIntent(this, id);
        startActivity(intent);
    }

    private boolean isSmartphone() {
        return findViewById(R.id.detail_fragment_container) == null;
    }

    @Override
    public void onQuizFinished(int count_score, long quizId, int size) {

        if (isSmartphone()) {
            Intent intent = QuizEndActivity.newIntent(this, count_score, quizId, size, size);
            startActivity(intent);
        } else {
            Fragment newDetail = QuizEndFragment.newInstance(count_score, quizId, size, size);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }

    }

    @Override
    public void onQuizReplay(long quizId) {
        if (isSmartphone()) {
            openDetailsSmartphoneView(quizId);
        } else {
            openDetailTabletView(quizId);
        }
    }

    @Override
    public void onQuizUpdate() {
        updateQuizListData();
    }

}
