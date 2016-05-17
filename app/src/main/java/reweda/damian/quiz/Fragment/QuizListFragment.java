package reweda.damian.quiz.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import reweda.damian.quiz.API.QuizFetchr;
import reweda.damian.quiz.Models.Score;
import reweda.damian.quiz.Models.Main.QuizItem;
import reweda.damian.quiz.QuizProvider;
import reweda.damian.quiz.R;
import reweda.damian.quiz.QuizApplication;

/**
 * Fragment hosted by QuizListlActivity, displays the Quiz List
 */
public class QuizListFragment extends Fragment {

    private static final String TAG = "QuizListFragment";

    private RecyclerView quizRecyclerView;
    private List<QuizItem> quizList = new ArrayList<>();
    private HashMap<Long, Score> userScoreList = new HashMap<>();
    private Callbacks mCallbacks;

    private QuizListAdapter mAdapter;
    private DisplayImageOptions options;

    public interface Callbacks {
        void onQuizSelected(long quizId);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        QuizApplication.initImageLoader(getContext());

        new FetchItemsTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        setupViews(view);
        setupDisplayImageOptions();

        return view;
    }

    private void setupViews(View view) {
        quizRecyclerView = (RecyclerView) view
                .findViewById(R.id.fragment_photo_gallery_recycler_view);
        quizRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setupDisplayImageOptions() {
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.logo_wp)
                .showImageOnFail(R.drawable.logo_wp)
                .showImageOnLoading(R.drawable.logo_wp).build();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        getListScoresFromDatabase();

        if (shoudInitializeAdapter())
            initializeAdapter();
        else
            updateData();
    }

    private void getListScoresFromDatabase() {
        QuizProvider quizProvider = QuizProvider.get(getActivity());
        userScoreList = quizProvider.getScores();
    }

    private boolean shoudInitializeAdapter() {
        return mAdapter == null || isAdded();
    }

    private void initializeAdapter() {
        mAdapter = new QuizListAdapter(quizList);
        quizRecyclerView.setAdapter(mAdapter);
    }

    private void updateData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private class QuizListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static final String EMPTY_STRING = "";
        private TextView quizTitle;
        private TextView scoreInformation;
        private ImageView quizImage;
        private ImageLoader imageLoader;

        private QuizItem quizItem;

        public QuizListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            quizTitle = (TextView) itemView.findViewById(R.id.list_item_quiz_title_text_view);
            scoreInformation = (TextView) itemView.findViewById(R.id.list_item_quiz_score_text_view);
            quizImage = (ImageView) itemView.findViewById(R.id.list_item_quiz_image_view);

            imageLoader = ImageLoader.getInstance();
        }

        @Override
        public void onClick(View v) {
            mCallbacks.onQuizSelected(quizItem.getId());
        }

        public void bindGalleryItem(QuizItem item) {
            quizItem = item;
            quizTitle.setText(item.getTitle());

            imageLoader.displayImage(quizItem.getMainPhoto().getUrl(), quizImage, options);

            if (isQuestionAnswered(item)) {
                if (isQuizSolved(item)) {
                    scoreInformation.setText(displayLastScore(item));
                } else {
                    scoreInformation.setText(displayPercentageSolvedQuestion(item));
                }

            } else {
                scoreInformation.setText(EMPTY_STRING);
            }
        }

        @NonNull
        private String displayPercentageSolvedQuestion(QuizItem item) {
            return getString(R.string.solved) + " " + (userScoreList.get(item.getId()).getSolved() - 1) * 100 / userScoreList.get(item.getId()).getQuestions() + " %";
        }

        @NonNull
        private String displayLastScore(QuizItem item) {
            return getString(R.string.last_score) + " " + String.valueOf(userScoreList.get(item.getId()).getScore()) + " / " +
                    String.valueOf(userScoreList.get(item.getId()).getQuestions());
        }

        private boolean isQuizSolved(QuizItem item) {
            return userScoreList.get(item.getId()).getQuestions() == userScoreList.get(item.getId()).getSolved();
        }

        private boolean isQuestionAnswered(QuizItem item) {
            return userScoreList.containsKey(item.getId());
        }
    }

    private class QuizListAdapter extends RecyclerView.Adapter<QuizListHolder> {
        private List<QuizItem> quizList;

        public QuizListAdapter(List<QuizItem> galleryItems) {
            quizList = galleryItems;
        }

        @Override
        public QuizListHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_quiz, viewGroup, false);

            return new QuizListHolder(view);
        }

        @Override
        public void onBindViewHolder(QuizListHolder quizListHolder, int position) {
            QuizItem galleryItem = quizList.get(position);
            quizListHolder.bindGalleryItem(galleryItem);
        }


        @Override
        public int getItemCount() {
            return quizList.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<QuizItem>> {

        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setTitle("Please wait");
            dialog.setMessage("Loading.........");
            dialog.show();
        }

        @Override
        protected List<QuizItem> doInBackground(Void... params) {
            return new QuizFetchr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<QuizItem> items) {
            quizList = items;
            updateUI();
            dialog.dismiss();

        }

    }
}
