package reweda.damian.quiz.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import reweda.damian.quiz.API.QuizFetchr;
import reweda.damian.quiz.Models.Score;
import reweda.damian.quiz.Models.Details.Questions;
import reweda.damian.quiz.QuizProvider;
import reweda.damian.quiz.R;

/**
 * Fragment hosted by QuizDetailActivity, displays while user solve the particular Quiz
 */
public class QuizDetailFragment extends Fragment {

    private static final String ARG_QUIZ_ID = "quiz_id";
    public static final int ANSWER_INDEX_0 = 0;

    private TextView questionTitle;
    private Button[] possibleAnswers = new Button[4];
    private int currentIndex = 0;
    private long quizId;
    private List<Questions> questionList = new ArrayList<>();
    private ProgressBar progressBar;

    private HashMap<Long, Score> userScoreList = new HashMap<>();
    private Score userScore;
    private int countScore = 0;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onQuizFinished(int count_score, long quizId, int size);
        void onQuizUpdate();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks)activity;
    }


    public static QuizDetailFragment newInstance(long quizId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUIZ_ID, quizId);
        QuizDetailFragment fragment = new QuizDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizId = (long) getArguments().getSerializable(ARG_QUIZ_ID);
        setRetainInstance(true);

        getUserScoreList();
        getLastShowedQuestionIndex();

        new FetchQuizQuestionsListTask().execute();
    }

    private void getUserScoreList() {
        QuizProvider quizProvider = QuizProvider.get(getActivity());
        userScoreList = quizProvider.getScores();
    }

    private void getLastShowedQuestionIndex() {
        if(userScoreList.containsKey(quizId)){
            if(userScoreList.get(quizId).getSolved() != userScoreList.get(quizId).getQuestions()){
                currentIndex = userScoreList.get(quizId).getSolved() - 1;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_detail, container, false);
        setupViews(v);
        return v;
    }

    private void setupViews(View v) {
        questionTitle = (TextView) v.findViewById(R.id.quiz_detail_id);

        progressBar = (ProgressBar) v.findViewById(R.id.ventilator_progress);

        possibleAnswers[0] = (Button) v.findViewById(R.id.quiz_detail_button1);
        possibleAnswers[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerFor(ANSWER_INDEX_0);
            }
        });

        possibleAnswers[1] = (Button) v.findViewById(R.id.quiz_detail_button2);
        possibleAnswers[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerFor(1);
            }
        });

        possibleAnswers[2] = (Button) v.findViewById(R.id.quiz_detail_button3);
        possibleAnswers[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerFor(2);
            }
        });

        possibleAnswers[3] = (Button) v.findViewById(R.id.quiz_detail_button4);
        possibleAnswers[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerFor(3);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        saveUserQuizState();
    }

    private void saveUserQuizState() {
        userScore = new Score();
        userScore.setIdDetail(quizId);
        userScore.setScore(countScore);
        userScore.setQuestions(questionList.size());
        userScore.setSolved(currentIndex + 1);

        QuizProvider.get(getActivity()).addScore(userScore);

        mCallbacks.onQuizUpdate();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void displayQuestionDetails(){

        String title = questionList.get(currentIndex).getText();
        questionTitle.setText(title);

        progressBar.setProgress(getPercentegeState());

        hideAllbuttons();
        showAnswers();
    }

    private void showAnswers() {
        for(int i = 0; i< questionList.get(currentIndex).getAnswers().size() ; i++) {
            possibleAnswers[i].setText(questionList.get(currentIndex).getAnswers().get(i).getText());
            possibleAnswers[i].setVisibility(View.VISIBLE);
        }
    }

    private int getPercentegeState() {
        return currentIndex * 100/ questionList.size();
    }

    private void checkAnswerFor(int answerChosen){
        if(questionList.get(currentIndex).getAnswers().get(answerChosen).isCorrect())
            countScore += 1;

        checkLastQuestion(currentIndex);
    }

    private void checkLastQuestion(int mCurrentIndex) {
        if (isLastQuestion(mCurrentIndex)) {
            mCallbacks.onQuizFinished(countScore, quizId, questionList.size());
        } else {
            this.currentIndex += 1;
            displayQuestionDetails();
        }
    }

    private boolean isLastQuestion(int mCurrentIndex) {
        return mCurrentIndex == questionList.size() - 1;
    }

    private void hideAllbuttons(){
         for(int i = 0; i < 4 ; i++) {
            possibleAnswers[i].setVisibility(View.INVISIBLE);
        }
    }


    private class FetchQuizQuestionsListTask extends AsyncTask<Void, Void, List<Questions>> {

        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Please wait");
            dialog.setMessage("Loading.........");
            dialog.show();
        }

        @Override
        protected List<Questions> doInBackground(Void... params) {
            return new QuizFetchr().fetchQuizDetailsFor(quizId);
        }

        @Override
        protected void onPostExecute(List<Questions> items) {

            questionList = items;

            displayQuestionDetails();
            dialog.dismiss();

        }
    }

}
