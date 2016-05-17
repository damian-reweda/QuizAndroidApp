package reweda.damian.quiz.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import reweda.damian.quiz.Activity.QuizListActivity;
import reweda.damian.quiz.Models.Score;
import reweda.damian.quiz.QuizProvider;
import reweda.damian.quiz.R;

public class QuizEndFragment extends Fragment {

    private static final String ARG_QUIZ_SCORE = "quiz_score";
    private static final String ARG_QUIZ_ID_DETAIL = "quiz_id";
    private static final String ARG_QUIZ_QUESTIONS = "quiz_questions";
    private static final String ARG_QUIZ_SOLVED = "quiz_solved";

    private int userScore;
    private long quizId;
    private int numberOfQuestions;
    private int numberOfSolvedQuestions;

    private Button returnQuizList;
    private Button solveOneMoreTime;
    private Score scoreInstance;
    private TextView finalScoreText;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onQuizReplay(long quizId);

        void onQuizUpdate();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    public static QuizEndFragment newInstance(int score, long quizId, int questions, int solved) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_QUIZ_SCORE, score);
        args.putSerializable(ARG_QUIZ_ID_DETAIL, quizId);
        args.putSerializable(ARG_QUIZ_QUESTIONS, questions);
        args.putSerializable(ARG_QUIZ_SOLVED, solved);
        QuizEndFragment fragment = new QuizEndFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userScore = (int) getArguments().getSerializable(ARG_QUIZ_SCORE);
        quizId = (long) getArguments().getSerializable(ARG_QUIZ_ID_DETAIL);
        numberOfQuestions = (int) getArguments().getSerializable(ARG_QUIZ_QUESTIONS);
        numberOfSolvedQuestions = (int) getArguments().getSerializable(ARG_QUIZ_SOLVED);

        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quiz_end, container, false);

        finalScoreText = (TextView) v.findViewById(R.id.quiz_end_score);
        finalScoreText.setText(String.valueOf(getPercent(userScore, numberOfQuestions)) + " %");

        saveScoreInDatabase();

        returnQuizList = (Button) v.findViewById(R.id.quiz_go_quiz_list);
        returnQuizList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizListActivity.class);
                startActivity(intent);
            }
        });

        solveOneMoreTime = (Button) v.findViewById(R.id.quiz_play_again);
        solveOneMoreTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbacks.onQuizReplay(quizId);
            }
        });

        mCallbacks.onQuizUpdate();

        return v;
    }

    private void saveScoreInDatabase() {
        scoreInstance = new Score();

        scoreInstance.setIdDetail(quizId);
        scoreInstance.setScore(userScore);
        scoreInstance.setQuestions(numberOfQuestions);
        scoreInstance.setSolved(numberOfSolvedQuestions);

        QuizProvider.get(getActivity()).addScore(scoreInstance);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private int getPercent(int score, int questions) {
        return (score * 100) / questions;
    }

}
